package com.rh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rh.model.HeuresSup;
import com.rh.service.HeuresSupService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/heures-sup")
public class HeuresSupController {

    @Autowired
    private HeuresSupService heuresSupService;

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("heuresSup", new HeuresSup());
        return "heuresSupForm";
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
        return "heuresSupList";
    }
}
