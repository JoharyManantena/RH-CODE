package com.rh.controller;


import com.rh.model.BesoinRecrutement;
import com.rh.service.BesoinRecrutementService;
import com.rh.model.Diplome;
import com.rh.service.DiplomeService;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BesoinRecrutementController {

    @Autowired
    private BesoinRecrutementService besoinRecrutementService;

    @Autowired
    private DiplomeService diplomeService;

    @GetMapping("/besoin-recrutement/ajouter")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("besoinRecrutement", new BesoinRecrutement());
        model.addAttribute("diplomes", diplomeService.getAllDiplomes());
        return "ajout-besoin";
    }


    @PostMapping("/besoin-recrutement/ajouter")
    public String ajouterBesoinRecrutement(BesoinRecrutement besoinRecrutement, @RequestParam("diplome.id") Integer diplomeId) {
        Diplome diplome = diplomeService.getDiplomeById(diplomeId);
        besoinRecrutement.setDiplome(diplome);
        besoinRecrutementService.createBesoinRecrutement(besoinRecrutement);
        return "redirect:/besoin-recrutement/success";
    }


    @GetMapping("/besoin-recrutement/success")
    public String afficherSucces() {
        return "success";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}


