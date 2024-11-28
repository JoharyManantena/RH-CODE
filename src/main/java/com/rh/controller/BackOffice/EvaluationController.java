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
                candidature.getEvaluation().setNoteExperience(noteExperience);
                candidature.getEvaluation().setNoteCompetence(noteCompetence);
                candidature.getEvaluation().setNoteEntretien(noteEntretien);
                candidature.getEvaluation().setNoteAdequation(noteAdequation);
                candidature.getEvaluation().calculerNoteTotale();
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
        } catch (Exception e) {
            // Gère les erreurs et prépare une réponse d'échec
            response.put("status", "error");
            response.put("message", "Erreur lors de l'enregistrement de l'évaluation : " + e.getMessage());
        }

        return response; // Retourne la réponse au format JSON
    }

}
