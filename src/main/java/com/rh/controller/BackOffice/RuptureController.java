package com.rh.controller.BackOffice;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rh.model.BackOffice.RuptureContrat;
import com.rh.service.BackOffice.ContratService;
import com.rh.service.BackOffice.PersonnelService;
import com.rh.service.BackOffice.RuptureContratService;
import com.rh.service.BackOffice.TypeRuptureService;

@Controller
public class RuptureController {

    private final RuptureContratService ruptureContratService;
    private final TypeRuptureService typeRuptureService;
    private final ContratService contratService;
    private final PersonnelService personnelService;

    public RuptureController(RuptureContratService ruptureContratService, TypeRuptureService typeRuptureService, ContratService crs, PersonnelService ps) {
        this.ruptureContratService = ruptureContratService;
        this.typeRuptureService = typeRuptureService;
        this.contratService = crs;
        this.personnelService = ps;
    }

    // Afficher le formulaire de rupture
    @GetMapping("/rupture")
    public String showRuptureForm(Model model) {
        model.addAttribute("ruptureList", typeRuptureService.getAll());
        model.addAttribute("personnelList", personnelService.getAll());
        return "rupture";
    }

    // Gérer la soumission du formulaire
    @PostMapping("/rupture/save")
    public String ruptureContrat(
            @RequestParam("dateNotification") LocalDate dateNotification,
            @RequestParam("datePravis") LocalDate datePreavis,
            @RequestParam(value = "fichier", required = false) MultipartFile fichier,
            @RequestParam("idRupture") Integer idRupture,
            @RequestParam("idPersonnel") Integer idPersonnel
    ) {
        try {
            // Log des paramètres
            System.out.println("Date Preavis : " + datePreavis);
            System.out.println("Date Notification : " + dateNotification);
            System.out.println("Fichier : " + (fichier != null ? fichier.getOriginalFilename() : "Aucun fichier"));
            System.out.println("ID Rupture : " + idRupture);
            System.out.println("ID Personnel : " + idPersonnel);
    
            // Création de l'objet
            RuptureContrat ruptureContrat = new RuptureContrat();
            ruptureContrat.setDateNotification(dateNotification);
            ruptureContrat.setDatePreavis(datePreavis);
            ruptureContrat.setFichier(fichier != null && !fichier.isEmpty() ? fichier.getBytes() : null);
            ruptureContrat.setDateHomologation(null);
            ruptureContrat.setDateEntretient(null);
            ruptureContrat.setIndemnites(null);
            ruptureContrat.setEtat(0);
    
            // Associer les entités
            ruptureContrat.setRupture(typeRuptureService.getRuptureById(idRupture));
            System.out.println("Type Rupture : " + ruptureContrat.getRupture());
            ruptureContrat.setContratEmploye(contratService.getByIdPersonnel(idPersonnel));
            System.out.println("Contrat Employé : " + ruptureContrat.getContratEmploye());
            ruptureContrat.setPersonnel(personnelService.getPersonnelById(idPersonnel));
            System.out.println("Personnel : " + ruptureContrat.getPersonnel());
    
            // Sauvegarder
            ruptureContratService.enregistrerRupture(ruptureContrat);
            System.out.println("Rupture enregistrée avec succès !");
            return "redirect:/rupture?success=true";
    
        } catch (Exception e) {
            System.err.println("Erreur lors de l'enregistrement : " + e.getMessage());
            e.printStackTrace();
            return "redirect:/rupture?error=true";
        }
    }
    
    @GetMapping("/validation")
    public String showRuptureValidate(Model model) {
        model.addAttribute("ruptureContratList", ruptureContratService.getIdRuptureContratEtat(0));
        model.addAttribute("ruptureList", typeRuptureService.getAll());
        return "UpdateRupture";
    }

    @GetMapping("/ruptures/update/{id}")
public String updateRupture(@PathVariable("id") Integer id, Model model) {
    RuptureContrat ruptureContrat = ruptureContratService.getById(id);
    model.addAttribute("ruptureContrat", ruptureContrat);
    return "EditRupture";
}

    @PostMapping("/update")
    public String saveUpdatedRupture(@ModelAttribute RuptureContrat ruptureContrat) {
        ruptureContratService.enregistrerRupture(ruptureContrat);
        return "redirect:/validation";
    }


}
