package com.rh.controller.BackOffice;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    public RuptureController(RuptureContratService ruptureContratService, TypeRuptureService typeRuptureService, ContratService crs,PersonnelService ps) {
        this.ruptureContratService = ruptureContratService;
        this.typeRuptureService = typeRuptureService;
        this.contratService = crs;
        this.personnelService = ps;
    }

    // Afficher le formulaire de rupture
    @GetMapping("/rupture")
    public String showRuptureForm(Model model) {
        model.addAttribute("ruptureList", typeRuptureService.getAll()); // Liste des types de rupture
        return "rupture";
    }

    // Gérer la soumission du formulaire
    @PostMapping("/rupture/save")
public String ruptureContrat(
        @RequestParam("dateNotification") LocalDate dateNotification,
        @RequestParam(value = "dateHomologation", required = false) LocalDate dateHomologation,
        @RequestParam(value = "dateEntretient", required = false) LocalDate dateEntretient,
        @RequestParam(value = "fichier", required = false) MultipartFile fichier,
        @RequestParam("indemnites") Double indemnites,
        @RequestParam("etat") Integer etat,
        @RequestParam("idRupture") Integer idRupture,
        @RequestParam("idContratEmploye") Integer idContratEmploye,
        @RequestParam("idPersonnel") Integer idPersonnel
) throws IOException {
    // Création d'une nouvelle rupture de contrat
    RuptureContrat ruptureContrat = new RuptureContrat();
    ruptureContrat.setDateNotification(dateNotification);
    ruptureContrat.setDateHomologation(dateHomologation);
    ruptureContrat.setDateEntretient(dateEntretient); // Si nécessaire, adaptez cette ligne.
    ruptureContrat.setFichier(fichier != null ? fichier.getBytes() : null);
    ruptureContrat.setIndemnites(indemnites);
    ruptureContrat.setEtat(etat);

    // Associer les entités liées
    ruptureContrat.setRupture(typeRuptureService.getRuptureById(idRupture));
    ruptureContrat.setContratEmploye(contratService.getContratEmployeById(idContratEmploye));
    ruptureContrat.setPersonnel(personnelService.getPersonnelById(idPersonnel));

    // Sauvegarder l'objet
    ruptureContratService.enregistrerRupture(ruptureContrat);

    // Redirection après traitement
    return "redirect:/rupture";
}

}
