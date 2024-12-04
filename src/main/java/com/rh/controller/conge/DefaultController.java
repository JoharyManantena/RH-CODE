package com.rh.controller.conge;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/accueil")
    public String home(Model model) {

        model.addAttribute("pageContent", "conge/accueil");
        return "home";
    }


}