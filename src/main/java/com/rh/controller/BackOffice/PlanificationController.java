package com.rh.controller.BackOffice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rh.model.BackOffice.Candidature;
import com.rh.model.BackOffice.PlanificationEntretien;
import com.rh.model.BackOffice.StatutCandidature;
import com.rh.repository.StatutCandidatureRepository;
import com.rh.service.BackOffice.CandidatureService;
import com.rh.service.BackOffice.PlanificationService;

@Controller
public class PlanificationController {
    private final PlanificationService planificationService;
    private final CandidatureService candidatureService;
    private final StatutCandidatureRepository statutCandidatureRepository;

    public PlanificationController(PlanificationService ps, CandidatureService cs, StatutCandidatureRepository scr) {
        this.planificationService = ps;
        this.candidatureService = cs;
        this.statutCandidatureRepository = scr;
    }

    @GetMapping("/planifierEntretien")
    public @ResponseBody Map<String, Object> planifierEntretien(
            @RequestParam("dateEntretien") String dateEntretien,
            @RequestParam("heureEntretien") String heureEntretien,
            @RequestParam("idCandidature") String id) {

        Map<String, Object> response = new HashMap<>();

        // Convertir les chaînes en LocalDate et LocalTime
        LocalDate date = LocalDate.parse(dateEntretien); // Conversion de la date
        LocalTime time = LocalTime.parse(heureEntretien); // Conversion de l'heure
        int idCandidature = Integer.parseInt(id);

        LocalDateTime dateTimeEntretien = date.atTime(time);
        Candidature candidature = this.candidatureService.getById(idCandidature);
        
        //recuperer le statut candidature "en cours"
        StatutCandidature sc = this.statutCandidatureRepository.findById(2).get();
        candidature.setStatutCandidature(sc);

        PlanificationEntretien planification = new PlanificationEntretien(candidature, dateTimeEntretien);
        try {
            this.planificationService.enregistrerPlanification(planification);
            this.candidatureService.enregistrerCandidature(candidature);
            response.put("status", "success");
            response.put("message", "Planification terminé");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            e.printStackTrace();
        }

        return response; // Rediriger vers une autre page
    }

}
