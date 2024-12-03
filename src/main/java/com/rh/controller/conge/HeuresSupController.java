package com.rh.controller.conge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rh.model.conge.HeuresSup;
import com.rh.service.conge.HeuresSupService;

@Controller
@RequestMapping("/heures-sup")
public class HeuresSupController {

    @Autowired
    private HeuresSupService heuresSupService;

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("heuresSup", new HeuresSup());
        return "conge/heuresSupForm";
    }

    @PostMapping("/create")
    public String saveHeuresSup(@ModelAttribute HeuresSup heuresSup, Model model) {
        // Calcul du montant total (exemple simple : nombreHeures * tauxHoraire)
        heuresSup.setMontantTotal(heuresSup.getNombreHeures().multiply(heuresSup.getTauxHoraire()));
        heuresSupService.saveHeuresSup(heuresSup);
        model.addAttribute("message", "Heure Supplémentaire ajoutée avec succès !");
        return "redirect:/heures-sup/create";
    }

    // Afficher la liste des heures supplémentaires
    @GetMapping("/list")
    public String listHeuresSup(Model model) {
        List<HeuresSup> heuresSups = heuresSupService.getAllHeuresSup();
        model.addAttribute("heuresSups", heuresSups);
        return "conge/heuresSupList";
    }
}
