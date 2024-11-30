package com.rh.service.BackOffice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.PlanificationEntretien;
import com.rh.repository.PlanificationRepository;

@Service
public class PlanificationService {
    private final PlanificationRepository planificationRepository;

    public PlanificationService(PlanificationRepository pr) {
        this.planificationRepository = pr;
    }

    public PlanificationEntretien enregistrerPlanification(PlanificationEntretien planification) throws Exception{
        if (!estDateDisponible(planification.getDateEntretien())) {
            throw new Exception("Date non disponible");
        }
        return this.planificationRepository.save(planification);
    }


    public boolean estDateDisponible(LocalDateTime dateEntretien) {
        List<PlanificationEntretien> entretiensExistants = planificationRepository.findAll();
    
        for (PlanificationEntretien entretien : entretiensExistants) {
            LocalDateTime debutEntretien = entretien.getDateEntretien();
            LocalDateTime finEntretien = debutEntretien.plusHours(1);
    
            if (!dateEntretien.isBefore(debutEntretien) && !dateEntretien.isAfter(finEntretien)) {
                return false; 
            }
        }
    
        return true; 
    }
}
