package com.rh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rh.model.DemandeConge;
import com.rh.model.Personnel;
import com.rh.repository.DemandeCongeRepository;
import com.rh.repository.PersonnelRepository;
import com.rh.repository.TypeCongeRepository;
import com.rh.service.DemandeCongeService;

@Controller 
@RequestMapping("/demandes")
public class DemandeCongeController {

    @Autowired
    private DemandeCongeService demandeCongeService;

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private TypeCongeRepository typeCongeRepository;

    @Autowired
    private DemandeCongeRepository demandeCongeRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    @GetMapping
    public String afficherDemandes(Model model) {
        model.addAttribute("demandes", demandeCongeService.getAllDemandes());
        return "demandes_list";
    }

    @PostMapping
    public String createDemande(@ModelAttribute DemandeConge demandeConge, Model model) {
        Personnel personnel = personnelRepository.findById(demandeConge.getPersonnel().getIdPersonnel())
            .orElseThrow(() -> new IllegalArgumentException("Personnel non trouvé"));

        // Vérifier si le cumulMois est < 12
        if (personnel.getCumulMois() < 12) {
            model.addAttribute("errorMessage", "Vous ne pouvez pas prendre de congé, votre cumul de mois est insuffisant.");
            model.addAttribute("personnels", personnelRepository.findAll());
            model.addAttribute("typesConges", typeCongeRepository.findAll());
            return "demande_conge"; // Retourne au formulaire avec le message d'erreur
        }

        // Vérifier si la date de début est après la date de fin
        if (demandeConge.getDateDebut().after(demandeConge.getDateFin())) {
            model.addAttribute("errorMessage", "La date de début doit être antérieure à la date de fin.");
            model.addAttribute("personnels", personnelRepository.findAll());
            model.addAttribute("typesConges", typeCongeRepository.findAll());
            return "demande_conge";
        }

        // Vérifier les champs obligatoires
        if (demandeConge.getPersonnel() == null || demandeConge.getTypeConge() == null) {
            model.addAttribute("errorMessage", "Le personnel et le type de congé sont obligatoires.");
            model.addAttribute("personnels", personnelRepository.findAll());
            model.addAttribute("typesConges", typeCongeRepository.findAll());
            return "demande_conge";
        }

        demandeCongeService.saveDemande(demandeConge);
        return "redirect:/demandes";
    }



    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("demandeConge", new DemandeConge());
        model.addAttribute("personnels", personnelRepository.findAll());
        model.addAttribute("typesConges", typeCongeRepository.findAll());
        return "demande_conge";
    }

    @PostMapping("/valider/{id}")
    public String validerDemande(@PathVariable("id") Integer id) {
        DemandeConge demande = demandeCongeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Demande non trouvée"));

        demande.setStatut("Validée");
        demandeCongeRepository.save(demande);

        return "redirect:/demandes"; 
    }

    @PostMapping("/annuler/{id}")
    public String annulerDemande(@PathVariable("id") Integer id) {
        DemandeConge demande = demandeCongeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Demande non trouvée"));

        demande.setStatut("Refusée");
        demandeCongeRepository.save(demande);

        return "redirect:/demandes";
    }


}
