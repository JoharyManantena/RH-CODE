package com.rh.controller.BackOffice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rh.model.BackOffice.Candidature;
import com.rh.model.BackOffice.Evaluation;
import com.rh.service.BackOffice.CandidatureService;
import com.rh.service.BackOffice.EvaluationService;

@Controller
public class EvaluationController {

    private final EvaluationService evaluationService;
    private final CandidatureService candidatureService;

    public EvaluationController(EvaluationService es, CandidatureService cs) {
        this.evaluationService = es;
        this.candidatureService = cs;
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
                System.out.println("Note totale = "  + candidature.getEvaluation().getNoteTotale());
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
            response.put("message", "Évaluation enregistrée avec succès");
            response.put("noteTotale", candidature.getEvaluation().getNoteTotale());
            response.put("idCandidature", candidature.getIdCandidature());
            if (candidature.getStatutCandidature().getIdStatut() == 2 || candidature.getStatutCandidature().getIdStatut() == 4) {
                response.put("estPlanifier", true);
            }else {
                response.put("estPlanifier", false);

            }
        } catch (Exception e) {
            // Gère les erreurs et prépare une réponse d'échec
            response.put("status", "error");
            response.put("message", "Erreur lors de l'enregistrement de l'évaluation : " + e.getMessage());
        }

        return response; // Retourne la réponse au format JSON
    }

}
