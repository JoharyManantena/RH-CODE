package com.rh.controller.BackOffice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rh.model.BackOffice.BesoinRecrutement;
import com.rh.model.BackOffice.Candidat;
import com.rh.model.BackOffice.Candidature;
import com.rh.model.BackOffice.Evaluation;
import com.rh.model.BackOffice.Notification;
import com.rh.model.BackOffice.OffreEmploi;
import com.rh.model.BackOffice.StatutCandidature;
import com.rh.model.BackOffice.StatutNotification;
import com.rh.model.conge.Personnel;
import com.rh.repository.BesoinRecrutementRespository;
import com.rh.repository.DiplomeRepository;
import com.rh.repository.StatutCandidatureRepository;
import com.rh.repository.conge.PersonnelRepository;
import com.rh.service.BackOffice.CandidatureService;
import com.rh.service.BackOffice.EvaluationService;
import com.rh.service.BackOffice.NotificationService;
import com.rh.service.BackOffice.OffreEmploiService;

@Controller
public class EvaluationController {

    private final EvaluationService evaluationService;
    private final CandidatureService candidatureService;
    private final StatutCandidatureRepository statutCandidatureRepository;
    private final OffreEmploiService offreEmploiService;
    private final BesoinRecrutementRespository besoinRecrutementRespository;
    private final NotificationService notificationService;

    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private DiplomeRepository diplomeRepository;

    public EvaluationController(EvaluationService es, CandidatureService cs, StatutCandidatureRepository scr,
            OffreEmploiService oes, BesoinRecrutementRespository brr, NotificationService ns) {
        this.evaluationService = es;
        this.candidatureService = cs;
        this.statutCandidatureRepository = scr;
        this.offreEmploiService = oes;
        this.besoinRecrutementRespository = brr;
        this.notificationService = ns;
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

        // Changer le statut de la candidature à "Acceptée"
        StatutCandidature sc = this.statutCandidatureRepository.findById(3).get(); // Statut "Acceptée"
        candidature.setStatutCandidature(sc);
        this.candidatureService.enregistrerCandidature(candidature);

        // Mettre à jour le besoin de recrutement
        BesoinRecrutement besoinRecrutement = candidature.getOffreEmploi().getBesoinRecrutement();
        besoinRecrutement.setNombreBesoin(besoinRecrutement.getNombreBesoin() - 1);
        this.besoinRecrutementRespository.save(besoinRecrutement);

        this.sendNotif("acceptation", candidature);

        // Rendre l'offre d'emploi indisponible si le nombre de besoins est 0
        if (besoinRecrutement.getNombreBesoin() == 0) {
            OffreEmploi oe = candidature.getOffreEmploi();
            oe.setDisponible(false);
            this.offreEmploiService.enregistrerOffre(oe);

            // Rejeter toutes les autres candidatures pour cette offre (statut = 1 ou 2)
            List<Candidature> listeArejeter = this.candidatureService.getListeCandidatureArejeter();
            StatutCandidature newStatutCandidature = this.statutCandidatureRepository.findById(4).get(); // Statut
                                                                                                         // "Rejeté"
            for (Candidature candidature2 : listeArejeter) {
                if (candidature2.getOffreEmploi().getIdOffreEmploi() == candidature.getOffreEmploi()
                        .getIdOffreEmploi()) {
                    candidature2.setStatutCandidature(newStatutCandidature);
                    this.candidatureService.enregistrerCandidature(candidature2);
                    this.sendNotif("rejet", candidature2);
                }
            }
        }


        // Enregistrer le candidat

        // creer l'objet personnel:
        Personnel personnel = new Personnel();
        personnel.setNom(candidature.getCv().getNom());
        personnel.setPrenom(candidature.getCv().getPrenom());
        personnel.setDateNaissance(candidature.getCv().getDateNaissance());
        personnel.setAdresse(candidature.getCv().getAdresse());
        personnel.setDateEmbauche(LocalDate.now());
        personnel.setSalaire(null);
        personnel.setDepartement(candidature.getOffreEmploi().getBesoinRecrutement().getDepartement());
        personnel.setCategorie(null);
        personnel.setPoste(null);

        this.personnelRepository.save(personnel);
        

        return "redirect:/candidatures";
    }

    @GetMapping("/rejeter")
    public String rejeterCandidature(@RequestParam("idCandidature") int id) {
        System.out.println("IdCandidature = " + id);
        Candidature candidature = this.candidatureService.getById(id);
        StatutCandidature sc = this.statutCandidatureRepository.findById(4).get();
        candidature.setStatutCandidature(sc);
        this.candidatureService.enregistrerCandidature(candidature);

        this.sendNotif("rejet", candidature);
        return "redirect:/candidatures";
    }

    public void sendNotif(String typeNotif, Candidature candidature) {
        if (typeNotif.equalsIgnoreCase("acceptation")) {
            // Créer et envoyer une notification d'acceptation
            String message = "Félicitations, vous avez été accepté pour ce poste.";
            String typeNotification = "Acceptation"; // Type de notification
            Candidat candidat = candidature.getCandidat();
            Notification notification = new Notification();
            notification.setMessage(message);
            notification.setTypeNotification(typeNotification);
            notification.setCandidat(candidat);
            notification.setDateCreation(LocalDate.now());
            notification.setStatut(StatutNotification.NON_LUE); // Statut initial de la notification (Non lue)

            // Sauvegarder la notification dans la base de données
            this.notificationService.envoyerNotification(notification);
        } else if (typeNotif.equalsIgnoreCase("rejet")) {
            // Créer et envoyer une notification d'acceptation
            String message = "Désolé, vous n'etes pas qualifié pour le job";
            String typeNotification = "Rejet"; // Type de notification
            Candidat candidat = candidature.getCandidat();
            Notification notification = new Notification();
            notification.setMessage(message);
            notification.setTypeNotification(typeNotification);
            notification.setCandidat(candidat);
            notification.setDateCreation(LocalDate.now());
            notification.setStatut(StatutNotification.NON_LUE); // Statut initial de la notification (Non lue)

            // Sauvegarder la notification dans la base de données
            this.notificationService.envoyerNotification(notification);
        }
    }

    // @PostMapping("/auto")
    // public ResponseEntity<String> evaluerAutomatiquement(@RequestParam int idCv, @RequestParam int idBesoin) {
    //     Cv cv = cvService.getById(idCv);
    //     BesoinRecrutement besoin = besoinService.getById(idBesoin);

    //     if (cv == null || besoin == null) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CV ou Besoin introuvable");
    //     }

    //     Evaluation evaluation = evaluationService.evaluerAutomatiquement(cv, besoin);

    //     return ResponseEntity
    //             .ok("Évaluation automatique réussie avec une note totale de : " + evaluation.getNoteTotale());
    // }

}
