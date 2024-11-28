package com.rh.controller.BackOffice;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rh.model.BackOffice.Candidature;
import com.rh.model.BackOffice.Evaluation;
import com.rh.service.BackOffice.CandidatureService;
import com.rh.service.BackOffice.EvaluationService;

@Controller
public class EvaluationController {
    
    private final EvaluationService evaluationService;
    private final CandidatureService candidatureService;

    public EvaluationController( EvaluationService es, CandidatureService cs) {
        this.evaluationService = es;
        this.candidatureService = cs;
    }

    @PostMapping("/evaluer")
    public String evaluer(@RequestParam("noteExperience") Double noteExperience,
                        @RequestParam("noteCompetence") Double noteCompetence,
                        @RequestParam("noteAdequation") Double noteAdequation,
                        @RequestParam("noteEntretien") Double noteEntretien,
                        @RequestParam("idCandidature") int id,
                        RedirectAttributes redirectAttributes) {
        Optional<Evaluation> optEvaluation = this.evaluationService.getEvaluation(id);
        if (optEvaluation.isPresent()) {
            Evaluation e = optEvaluation.get();
            e.setNoteExperience(noteExperience);
            e.setNoteCompetence(noteCompetence);
            e.setNoteEntretien(noteEntretien);
            e.setNoteAdequation(noteAdequation);
            e.calculerNoteTotale();
            e = this.evaluationService.enregistrerEvaluation(e);
            return "redirect:/evaluation?idCandidature=" + id;
            
        }else {
            Candidature candidature = this.candidatureService.getById(id);
            Evaluation e = new Evaluation(candidature, noteExperience, noteCompetence, noteAdequation, noteEntretien);
            e.calculerNoteTotale();
            e = this.evaluationService.enregistrerEvaluation(e);
            return "redirect:/evaluation?idCandidature=" + id;

        }

    }

}
