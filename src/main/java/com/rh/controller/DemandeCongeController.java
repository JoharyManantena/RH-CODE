package com.rh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.rh.model.DemandeConge;
import com.rh.model.Personnel;
import com.rh.model.TypeConge;
import com.rh.repository.PersonnelRepository;
import com.rh.repository.TypeCongeRepository;
import com.rh.service.DemandeCongeService;

@Controller  // Utilisez @Controller au lieu de @RestController
@RequestMapping("/demandes")
public class DemandeCongeController {

    @Autowired
    private DemandeCongeService demandeCongeService;

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private TypeCongeRepository typeCongeRepository;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }
    
    @GetMapping
    public String listDemandes(Model model) {
        List<DemandeConge> demandes = demandeCongeService.getAllDemandes();
        model.addAttribute("demandes", demandes);
        return "demandes_list"; // Nom de la vue à afficher
    }

    @PostMapping
    public String createDemande(@ModelAttribute DemandeConge demandeConge, Model model) {
        // Validation manuelle
        if (demandeConge.getDateDebut().after(demandeConge.getDateFin())) {
            model.addAttribute("errorMessage", "La date de début doit être antérieure à la date de fin.");
            return "demande_conge"; // Retourner au formulaire avec l'erreur
        }

        // Vérification si le personnel et le type de congé existent
        if (demandeConge.getPersonnel() == null || demandeConge.getTypeConge() == null) {
            model.addAttribute("errorMessage", "Le personnel et le type de congé sont obligatoires.");
            return "demande_conge"; // Retourner au formulaire avec l'erreur
        }

        demandeCongeService.saveDemande(demandeConge);
        return "redirect:/demandes"; // Rediriger vers la liste des demandes après succès
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("demandeConge", new DemandeConge());
        model.addAttribute("personnels", personnelRepository.findAll());
        model.addAttribute("typesConges", typeCongeRepository.findAll());
        return "demande_conge";  // Le formulaire Thymeleaf
    }
}
