package com.rh.controller.BackOffice;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rh.model.BackOffice.Candidature;
import com.rh.service.BackOffice.CandidatureService;

@Controller
public class CandidatureController {
    private final CandidatureService candidatureService;

    public CandidatureController(CandidatureService cs) {
        this.candidatureService = cs;
    }

    @GetMapping("/candidatures")
    public String getCandidatures(Model model) {
        List<Candidature> listeCandidature = this.candidatureService.getCandidatures();
        listeCandidature.sort((c1, c2) -> c2.getDatePostulation().compareTo(c1.getDatePostulation()));

        model.addAttribute("listeCandidature", listeCandidature);
        model.addAttribute("pageContent", "BackOffice/listeCandidature");
        return "layout";
    }
}
