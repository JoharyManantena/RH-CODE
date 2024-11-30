package com.rh.controller.BackOffice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rh.model.BackOffice.BesoinRecrutement;
import com.rh.model.BackOffice.Candidature;
import com.rh.model.BackOffice.Evaluation;
import com.rh.model.BackOffice.OffreEmploi;
import com.rh.model.BackOffice.StatutCandidature;
import com.rh.repository.BesoinRecrutementRespository;
import com.rh.repository.StatutCandidatureRepository;
import com.rh.service.BackOffice.CandidatureService;
import com.rh.service.BackOffice.EvaluationService;
import com.rh.service.BackOffice.OffreEmploiService;

@Controller
public class EvaluationController {

    private final EvaluationService evaluationService;
    private final CandidatureService candidatureService;
    private final StatutCandidatureRepository statutCandidatureRepository;
    private final OffreEmploiService offreEmploiService;
    private final BesoinRecrutementRespository besoinRecrutementRespository;

    public EvaluationController(EvaluationService es, CandidatureService cs, StatutCandidatureRepository scr,
            OffreEmploiService oes, BesoinRecrutementRespository brr) {
        this.evaluationService = es;
        this.candidatureService = cs;
        this.statutCandidatureRepository = scr;
        this.offreEmploiService = oes;
        this.besoinRecrutementRespository = brr;
    }

    @PostMapping("/evaluer")
    public @ResponseBody Map<String, Object> evaluer(
            @RequestParam("noteExperience") Double noteExperience,
            @RequestParam("noteCompetence") Double noteCompetence,
            @RequestParam("noteAdequation") Double noteAdequation,
            @RequestParam("noteEntretien") Double noteEntretien,
            @RequestParam("idCandidature") int id) {

        Map<String, Object> response = new HashMap<>();

        try {
            Candidature candidature = this.candidatureService.getById(id);

            if (candidature.getEvaluation() != null) {
                // Met à jour une évaluation existante
                Evaluation e = new Evaluation(noteExperience, noteCompetence, noteAdequation, noteEntretien);
                e.calculerNoteTotale();
                candidature.setEvaluation(e);
                System.out.println("Note totale = " + candidature.getEvaluation().getNoteTotale());
                this.evaluationService.enregistrerEvaluation(candidature.getEvaluation());
            } else {
                // Crée une nouvelle évaluation
                Evaluation e = new Evaluation(noteExperience, noteCompetence, noteAdequation, noteEntretien);
                e.calculerNoteTotale();
                e = this.evaluationService.enregistrerEvaluation(e);
                candidature.setEvaluation(e);
                this.candidatureService.enregistrerCandidature(candidature);
            }

            // Prépare une réponse de succès
            response.put("status", "success");
            if (candidature.getStatutCandidature().getIdStatut() == 2) {
                // Tapitra ny entretien.
                response.put("estPlanifier", true);
                response.put("message", "Entretien terminé");
                response.put("entretienFini", true);

            } else {
                response.put("message", "Évaluation enregistrée avec succès");
                response.put("estPlanifier", false);
                response.put("entretienFini", false);
            }
            response.put("noteTotale", candidature.getEvaluation().getNoteTotale());
            response.put("idCandidature", candidature.getIdCandidature());
            response.put("noteTotale", candidature.getEvaluation().getNoteTotale());

        } catch (Exception e) {
            // Gère les erreurs et prépare une réponse d'échec
            response.put("status", "error");
            response.put("message", "Erreur lors de l'enregistrement de l'évaluation : " + e.getMessage());
            e.printStackTrace();
        }

        return response; // Retourne la réponse au format JSON
    }

    @GetMapping("/accepter")
    public String accepterCandidature(@RequestParam("idCandidature") int id) {
        System.out.println("IdCandidature = " + id);
        Candidature candidature = this.candidatureService.getById(id);
        StatutCandidature sc = this.statutCandidatureRepository.findById(3).get();
        candidature.setStatutCandidature(sc);
        this.candidatureService.enregistrerCandidature(candidature);

        // ovaina ny nombre_besoin ao amin'ny table besoin_recrutement
        BesoinRecrutement besoinRecrutement = candidature.getOffreEmploi().getBesoinRecrutement();
        besoinRecrutement.setNombreBesoin(besoinRecrutement.getNombreBesoin() - 1);
        this.besoinRecrutementRespository.save(besoinRecrutement);

        // rendre l'offre d'emploi ne plus disponible si besoinRecrutement = 0
        if (besoinRecrutement.getNombreBesoin() == 0) {
            OffreEmploi oe = candidature.getOffreEmploi();
            oe.setDisponible(false);
            this.offreEmploiService.enregistrerOffre(oe);

            // rejeter tous les autres candidatures venant de cette offre : idStatut = 1 et 2
            List<Candidature> listeArejeter = this.candidatureService.getListeCandidatureArejeter();
            StatutCandidature newStatutCandidature = this.statutCandidatureRepository.findById(4).get();
            for (Candidature candidature2 : listeArejeter) {
                if (candidature2.getOffreEmploi().getIdOffreEmploi() == candidature.getOffreEmploi().getIdOffreEmploi()) {
                    candidature2.setStatutCandidature(newStatutCandidature);
                    this.candidatureService.enregistrerCandidature(candidature2);
                }
            }

        }

        return "redirect:/candidatures";
    }

    @GetMapping("/rejeter")
    public String rejeterCandidature(@RequestParam("idCandidature") int id) {
        System.out.println("IdCandidature = " + id);
        Candidature candidature = this.candidatureService.getById(id);
        StatutCandidature sc = this.statutCandidatureRepository.findById(4).get();
        candidature.setStatutCandidature(sc);
        this.candidatureService.enregistrerCandidature(candidature);
        return "redirect:/candidatures";
    }

}
