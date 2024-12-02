package com.rh.controller.BackOffice;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rh.model.BackOffice.BesoinRecrutement;
import com.rh.model.BackOffice.OffreEmploi;
import com.rh.model.BackOffice.TypeContrat;
import com.rh.repository.TypeContratRepository;
import com.rh.service.BackOffice.BesoinRecrutementService;
import com.rh.service.BackOffice.OffreEmploiService;

@Controller
public class OffreController {

    private final OffreEmploiService offreEmploiService;
    private final BesoinRecrutementService besoinRecrutementService;

    @Autowired
    private TypeContratRepository typeContratRepository;

    public OffreController(OffreEmploiService oes, BesoinRecrutementService brs) {
        this.offreEmploiService = oes;
        this.besoinRecrutementService = brs;
    }

    @GetMapping("/creerOffre")
    public String getMethodName(@RequestParam("titrePoste") String titrePoste,
            @RequestParam("descriptionPoste") String descriptionPoste,
            @RequestParam("dateOffre") LocalDate dateOffre,
            @RequestParam("responsabilite") String responsabilite,
            @RequestParam("dateLimite") LocalDate dateLimite,
            @RequestParam("idDemandeBesoin") int idDemandeBesoin,
            @RequestParam("typeContrat") int idTypeContrat) {

        TypeContrat typeContrat = this.typeContratRepository.findById(idTypeContrat).get();
        OffreEmploi oe = new OffreEmploi(titrePoste, descriptionPoste, dateOffre, responsabilite, dateLimite,
                typeContrat);
        BesoinRecrutement br = this.besoinRecrutementService.getById(idDemandeBesoin);
        if (br == null) {
            System.out.println(idDemandeBesoin + 1
                    + "   null(**********************************************************************************************)");
        } else {
            System.out.println("Not null ****************************************************************************");
            System.out.println("Annee d'experience = " + br.getAnneesExperience());
        }
        oe.setBesoinRecrutement(br);
        br.setFait(true);
        this.offreEmploiService.enregistrerOffre(oe);
        this.besoinRecrutementService.enregistrerBesoinRecrutement(br);
        return "redirect:/besoinRecrutement";
    }

}
