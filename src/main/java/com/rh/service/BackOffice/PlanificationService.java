package com.rh.service.BackOffice;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.PlanificationEntretien;
import com.rh.repository.PlanificationRepository;

@Service
public class PlanificationService {
    private final PlanificationRepository planificationRepository;

    public PlanificationService(PlanificationRepository pr) {
        this.planificationRepository = pr;
    }

    public PlanificationEntretien enregistrerPlanification(PlanificationEntretien planification) {
        return this.planificationRepository.save(planification);
    }
}
