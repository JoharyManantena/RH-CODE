package com.rh.controller.BackOffice;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rh.model.BackOffice.Indemnite;
import com.rh.model.BackOffice.RuptureContrat;
import com.rh.service.BackOffice.ContratService;
import com.rh.service.BackOffice.IndemniteService;
import com.rh.service.BackOffice.PersonnelService;
import com.rh.service.BackOffice.RuptureContratService;
import com.rh.service.BackOffice.TypeRuptureService;

@Controller
public class RuptureController {

    private final RuptureContratService ruptureContratService;
    private final TypeRuptureService typeRuptureService;
    private final ContratService contratService;
    private final PersonnelService personnelService;
    private final IndemniteService  indemniteService;

    public RuptureController(RuptureContratService ruptureContratService, TypeRuptureService typeRuptureService, ContratService crs, PersonnelService ps, IndemniteService is) {
        this.ruptureContratService = ruptureContratService;
        this.typeRuptureService = typeRuptureService;
        this.contratService = crs;
        this.personnelService = ps;
        this.indemniteService = is;
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
    
            // Création du rupture Contrat
            RuptureContrat ruptureContrat = new RuptureContrat();
            ruptureContrat.setDateNotification(dateNotification);
            ruptureContrat.setDatePreavis(datePreavis);
            ruptureContrat.setFichier(fichier != null && !fichier.isEmpty() ? fichier.getBytes() : null);
            ruptureContrat.setDateHomologation(null);
            ruptureContrat.setDateEntretient(null);
            ruptureContrat.setEtat(0);
            ruptureContrat.setEnvoyeDate(null);
    
            // Associer les entités
            ruptureContrat.setRupture(typeRuptureService.getRuptureById(idRupture));
            System.out.println("Type Rupture : " + ruptureContrat.getRupture().getTypes());
            ruptureContrat.setPersonnel(personnelService.getPersonnelById(idPersonnel));
            System.out.println("Personnel : " + ruptureContrat.getPersonnel().getNom());
            ruptureContrat.setContratEmploye(contratService.getPersonnel(ruptureContrat.getPersonnel()));
            System.out.println("Contrat Id : " + ruptureContrat.getContratEmploye().getIdContratEmploye());
           
            // Vérification du préavis
            Integer preavis = ruptureContrat.getContratEmploye().getMoisPreavis();
            System.out.println("Durée du préavis en mois : " + preavis);

            if (preavis != null) {
                Integer jourPreavis = preavis * 30; // Conversion en jours (approximation)
                System.out.println("Durée du préavis en jours : " + jourPreavis);

                // Calcul du décalage en jours entre datePreavis et dateNotification
                long decalage = ChronoUnit.DAYS.between(dateNotification, datePreavis);
                System.out.println("Décalage réel en jours : " + decalage);

                // Vérification du respect du préavis
                if (decalage >= jourPreavis) {
                    ruptureContrat.setPreavis(1); // Préavis respecté
                    System.out.println("Préavis respecté.");
                } else {
                    ruptureContrat.setPreavis(0); // Préavis non respecté
                    System.out.println("Préavis non respecté.");
                }
            } else {
                System.err.println("Erreur : La durée du préavis est nulle !");
            }



            // Creation Indemnite 
            Indemnite indemnite = new Indemnite();
            indemnite.setRuptureContrat(ruptureContrat);
            System.out.println("Type de Contrat" + ruptureContrat.getRupture().getTypes());
            Double salaireBrute = ruptureContrat.getPersonnel().getSalaire();
            System.out.println("Salaire Brute en mois" + salaireBrute);
            LocalDate dateDembauche = ruptureContrat.getPersonnel().getDateEmbauche();
            System.out.println("Date d'embauche : " + dateDembauche);
            // Calcul de l'ancienneté en années
            int anciennete = (int) ChronoUnit.YEARS.between(dateDembauche, datePreavis);
            System.out.println("Ancienneté en années : " + anciennete);

            
            // Demission
            if( ruptureContrat.getRupture().getIdRupture() == 1 ){
                if(ruptureContrat.getPreavis() == 1){
                    Double compensatoire = salaireBrute * preavis * 0;
                    indemnite.setCompensatoire(compensatoire);
                    System.out.println("Indemnite Compensatoire" + compensatoire);
                    indemnite.setCongeNonPaye(null);
                    indemnite.setLicencement(null);
                    indemnite.setSpecifique(null);
                    indemnite.setRetraite(null);
                    Double indemniteTotal = compensatoire;
                    ruptureContrat.setIndemnites(indemniteTotal);
                    System.out.println("total Indemnite : " + indemniteTotal);
                }
                else{
                    Double compensatoire = salaireBrute * preavis * -1;
                    indemnite.setCompensatoire(compensatoire);
                    System.out.println("Indemnite Compensatoire" + compensatoire);
                    indemnite.setCongeNonPaye(null);
                    indemnite.setLicencement(null);
                    indemnite.setSpecifique(null);
                    indemnite.setRetraite(null);
                    Double indemniteTotal = compensatoire;
                    ruptureContrat.setIndemnites(indemniteTotal);
                    System.out.println("total Indemnite : " + indemniteTotal);
                }
            }
            //licencement
            else if(ruptureContrat.getRupture().getIdRupture() == 2){
                if(ruptureContrat.getPreavis() == 1){
                    Double compensatoire = salaireBrute * preavis * 0;
                    indemnite.setCompensatoire(compensatoire);
                    System.out.println("Indemnite Compensatoire" + compensatoire);
                    indemnite.setCongeNonPaye(null);
                    // licencement
                    if( anciennete >10){
                        //1/4 salaire mensuel * 10 ans de travail
                        Double un = 10/4 * salaireBrute;
                        int ambony = anciennete - 10; 
                        //1/3 salaire mensuel * ambony 
                        Double deux = 1/3 * salaireBrute * ambony;
                        Double licenciement = un + deux;
                        indemnite.setLicencement(licenciement);
                        System.out.println("indemnité de licenciement =" + licenciement);  
                        Double indemniteTotal = compensatoire + licenciement;
                        ruptureContrat.setIndemnites(indemniteTotal); 
                        System.out.println("total Indemnite : " + indemniteTotal);
                    }
                    else{
                        //1/4 salaire mensuel * anciennete de travail
                        Double licenciement  = 1/4 * anciennete * salaireBrute;
                        indemnite.setLicencement(licenciement);
                        System.out.println("indemnité de licenciement =" + licenciement);
                        Double indemniteTotal = compensatoire + licenciement;
                        ruptureContrat.setIndemnites(indemniteTotal);
                        System.out.println("total Indemnite : " + indemniteTotal);
                    }
                    indemnite.setSpecifique(null);
                    indemnite.setRetraite(null);
                }
                else{
                    if(ruptureContrat.getPreavis() == 1){
                        Double compensatoire = salaireBrute * preavis;
                        indemnite.setCompensatoire(compensatoire);
                        System.out.println("Indemnite Compensatoire" + compensatoire);
                        indemnite.setCongeNonPaye(null);
                        // licencement
                        if( anciennete >10){
                            //1/4 salaire mensuel * 10 ans de travail
                            Double un = 10/4 * salaireBrute;
                            int ambony = anciennete - 10; 
                            //1/3 salaire mensuel * ambony 
                            Double deux = 1/3 * salaireBrute * ambony;
                            Double licenciement = un + deux;
                            indemnite.setLicencement(licenciement);
                            System.out.println("indemnité de licenciement =" + licenciement);
                            Double indemniteTotal = compensatoire + licenciement;
                            ruptureContrat.setIndemnites(indemniteTotal);   
                            System.out.println("total Indemnite : " + indemniteTotal);
                        }
                        else{
                            //1/4 salaire mensuel * anciennete de travail
                            Double licenciement  = 1/4 * anciennete * salaireBrute;
                            indemnite.setLicencement(licenciement);
                            System.out.println("indemnité de licenciement =" + licenciement);
                            Double indemniteTotal = compensatoire + licenciement;
                            ruptureContrat.setIndemnites(indemniteTotal);
                            System.out.println("total Indemnite : " + indemniteTotal);
                        }
                        indemnite.setSpecifique(null);
                        indemnite.setRetraite(null);
                    }
                }
             }
        
            //Retraite 
            else if( ruptureContrat.getRupture().getIdRupture() == 3 ){
                if(ruptureContrat.getPreavis() == 1){
                    Double compensatoire = salaireBrute * preavis * 0;
                    indemnite.setCompensatoire(compensatoire);
                    System.out.println("Indemnite Compensatoire" + compensatoire);
                    indemnite.setCongeNonPaye(null);
                    indemnite.setLicencement(null);
                    indemnite.setSpecifique(null);
                    if(10<anciennete && anciennete < 15){
                        Double retraite = salaireBrute;
                        indemnite.setRetraite(retraite);
                        System.out.println("indemnité de depart à la retraite :" + retraite);
                        Double indemniteTotal = compensatoire + retraite;
                        ruptureContrat.setIndemnites(indemniteTotal);
                        System.out.println("total Indemnite : " + indemniteTotal);
                    }
                    else if(15<= anciennete && anciennete < 20){
                        Double retraite = salaireBrute * 3/2;
                        indemnite.setRetraite(retraite);
                        System.out.println("indemnité de depart à la retraite :" + retraite);
                        Double indemniteTotal = compensatoire + retraite;
                        ruptureContrat.setIndemnites(indemniteTotal);
                        System.out.println("total Indemnite : " + indemniteTotal);
                    }
                    else if(20<= anciennete){
                        Double retraite = salaireBrute * 2;
                        indemnite.setRetraite(retraite);
                        System.out.println("indemnité de depart à la retraite :" + retraite);
                        Double indemniteTotal = compensatoire + retraite;
                        ruptureContrat.setIndemnites(indemniteTotal);
                        System.out.println("total Indemnite : " + indemniteTotal);
                    }
                    else{
                        indemnite.setRetraite(null);
                        ruptureContrat.setIndemnites(compensatoire);
                        System.out.println("total Indemnite : " + compensatoire);
                    }
                }
                else{
                    Double compensatoire = salaireBrute * preavis;
                    indemnite.setCompensatoire(compensatoire);
                    System.out.println("Indemnite Compensatoire" + compensatoire);
                    indemnite.setCongeNonPaye(null);
                    indemnite.setLicencement(null);
                    indemnite.setSpecifique(null);
                    if(10<anciennete && anciennete < 15){
                        Double retraite = salaireBrute;
                        indemnite.setRetraite(retraite);
                        System.out.println("indemnité de depart à la retraite :" + retraite);
                        Double indemniteTotal = compensatoire + retraite;
                        ruptureContrat.setIndemnites(indemniteTotal);
                        System.out.println("total Indemnite : " + indemniteTotal);

                    }
                    else if(15<= anciennete && anciennete < 20){
                        Double retraite = salaireBrute * 3/2;
                        indemnite.setRetraite(retraite);
                        System.out.println("indemnité de depart à la retraite :" + retraite);
                        Double indemniteTotal = compensatoire + retraite;
                        ruptureContrat.setIndemnites(indemniteTotal);
                        System.out.println("total Indemnite : " + indemniteTotal);
                    }
                    else if(20<= anciennete){
                        Double retraite = salaireBrute * 2;
                        indemnite.setRetraite(retraite);
                        System.out.println("indemnité de depart à la retraite :" + retraite);
                        Double indemniteTotal = compensatoire + retraite;
                        ruptureContrat.setIndemnites(indemniteTotal);                        
                        System.out.println("total Indemnite : " + indemniteTotal);
                    }
                    else{
                        indemnite.setRetraite(null);
                        ruptureContrat.setIndemnites(compensatoire);
                        System.out.println("total Indemnite : " + compensatoire);
                    }
                }
            }

            //Accord
            
            else if(ruptureContrat.getRupture().getIdRupture() == 4){
                if(ruptureContrat.getPreavis() == 1){
                    Double compensatoire = salaireBrute * preavis * 0;
                    indemnite.setCompensatoire(compensatoire);
                    System.out.println("Indemnite Compensatoire" + compensatoire);
                    indemnite.setCongeNonPaye(null);
                    // speifique = licencement
                    if( anciennete >10){
                        //1/4 salaire mensuel * 10 ans de travail
                        Double un = 10/4 * salaireBrute;
                        int ambony = anciennete - 10; 
                        //1/3 salaire mensuel * ambony 
                        Double deux = 1/3 * salaireBrute * ambony;
                        Double specifique = un + deux;
                        indemnite.setSpecifique(specifique);
                        System.out.println("indemnité specifique =" + specifique);   
                        Double indemniteTotal = compensatoire + specifique;
                        ruptureContrat.setIndemnites(indemniteTotal);                                                
                        System.out.println("total Indemnite : " + indemniteTotal);
                    }
                    else{
                        //1/4 salaire mensuel * anciennete de travail
                        Double specifique  = 1/4 * anciennete * salaireBrute;
                        indemnite.setSpecifique(specifique);
                        System.out.println("indemnité specifique =" + specifique);
                        Double indemniteTotal = compensatoire + specifique;
                        ruptureContrat.setIndemnites(indemniteTotal);
                        System.out.println("total Indemnite : " + indemniteTotal);
                    }
                    indemnite.setLicencement(null);
                    indemnite.setRetraite(null);
                }
                else{
                    if(ruptureContrat.getPreavis() == 1){
                        Double compensatoire = salaireBrute * preavis;
                        indemnite.setCompensatoire(compensatoire);
                        System.out.println("Indemnite Compensatoire" + compensatoire);
                        indemnite.setCongeNonPaye(null);
                        // licencement
                        if( anciennete >10){
                            //1/4 salaire mensuel * 10 ans de travail
                            Double un = 10/4 * salaireBrute;
                            int ambony = anciennete - 10; 
                            //1/3 salaire mensuel * ambony 
                            Double deux = 1/3 * salaireBrute * ambony;
                            Double specifique = un + deux;
                            indemnite.setLicencement(specifique);
                            System.out.println("indemnité de specifique =" + specifique);  
                            Double indemniteTotal = compensatoire + specifique;
                            ruptureContrat.setIndemnites(indemniteTotal);
                            System.out.println("total Indemnite : " + indemniteTotal); 
                        }
                        else{
                            //1/4 salaire mensuel * anciennete de travail
                            Double specifique  = 1/4 * anciennete * salaireBrute;
                            indemnite.setLicencement(specifique);
                            System.out.println("indemnité de specifique =" + specifique);
                            Double indemniteTotal = compensatoire + specifique;
                            ruptureContrat.setIndemnites(indemniteTotal);
                            System.out.println("total Indemnite : " + indemniteTotal);
                        }
                        indemnite.setLicencement(null);
                        indemnite.setRetraite(null);
                    }

                }
            }




            // Sauvegarder
            ruptureContratService.enregistrerRupture(ruptureContrat);
            System.out.println("Rupture enregistrée avec succès !");
            indemniteService.enregitrerIndemnite(indemnite);
            System.out.println("Indemnite enregistrée avec succès !");
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
        Indemnite indemnite = indemniteService.getByRupture(ruptureContrat);
        model.addAttribute("ruptureContrat", ruptureContrat);
        model.addAttribute("indemnite", indemnite);
        return "EditRupture";
    }

    @PostMapping("/update")
    public String saveUpdatedRupture(
        @RequestParam("idRuptureContrat") Integer idRuptureContrat,
        @RequestParam("dateEnvoye") LocalDate dateEnvoye,
        @RequestParam(value = "dateHomologation", required = false) LocalDate dateHomologation,
        @RequestParam(value = "dateEntretient", required = false) LocalDate dateEntretient,
        @RequestParam("etat") Integer etat
        ) {
        //Recuperer Le Rupture de contrat
        RuptureContrat ruptureContrat = ruptureContratService.getById(idRuptureContrat);
        if(ruptureContrat.getRupture().getIdRupture() == 2 ){
            ruptureContratService.updateRuptureEtat(idRuptureContrat,dateEnvoye,dateHomologation,dateEntretient,etat);
        }
        else{
            ruptureContratService.updateRuptureEtat(idRuptureContrat, dateEnvoye, null, null, etat);
        }
        return "redirect:/validation?success=true";
    }


    @GetMapping("/notification")
    public String NotificationClient(Model model) {
        model.addAttribute("ruptureContratList", ruptureContratService.getIdRuptureContratEtat(1));
        model.addAttribute("ruptureContratList2", ruptureContratService.getIdRuptureContratEtat(2));
        model.addAttribute("ruptureList", typeRuptureService.getAll());
        return "notificationClient";
    }
    @GetMapping("/ruptures/detail/{id}")
    public String Detail(@PathVariable("id") Integer id, Model model) {
        RuptureContrat ruptureContrat = ruptureContratService.getById(id);
        Indemnite indemnite = indemniteService.getByRupture(ruptureContrat);
        model.addAttribute("ruptureContrat", ruptureContrat);
        model.addAttribute("indemnite", indemnite);
        return "detail";
    }
}
