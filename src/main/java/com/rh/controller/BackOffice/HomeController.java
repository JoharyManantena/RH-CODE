package com.rh.controller.BackOffice;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rh.controller.BaseController;
import com.rh.model.BackOffice.Candidature;
import com.rh.model.BackOffice.Evaluation;
import com.rh.service.BackOffice.CandidatureService;
import com.rh.service.BackOffice.EvaluationService;

@Controller
public class HomeController extends BaseController {

    private final CandidatureService candidatureService;
    private final EvaluationService evaluationService;

    public HomeController(CandidatureService cs, EvaluationService es) {
        this.candidatureService = cs;
        this.evaluationService = es;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageContent", "Backoffice/creationOffre");
        return "layout";
    }

    @GetMapping("/loginAdmin")
    public String login(Model model) {
        return "BackOffice/login";
    }

    @GetMapping("/listeCandidature")
    public String listeCandidature(Model model) {
        model.addAttribute("pageContent", "Backoffice/listeCandidature");
        System.out.println(model.getAttribute("message"));
        return "layout";
    }

    @GetMapping("/evaluation")
    public String evaluation(@RequestParam("idCandidature") String id, Model model) {
        int idCandidature = Integer.parseInt(id);
        Candidature candidature = this.candidatureService.getById(idCandidature);

        model.addAttribute("candidature", candidature);
        model.addAttribute("pageContent", "BackOffice/evaluationCandidature");
        return "BackOffice/evaluationCandidature";
    }
}
