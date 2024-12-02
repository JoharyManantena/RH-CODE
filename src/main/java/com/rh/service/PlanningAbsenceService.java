package com.rh.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rh.model.PlanningAbsence;
import com.rh.repository.PlanningAbsenceRepository;

@Service
public class PlanningAbsenceService {

    @Autowired
    private PlanningAbsenceRepository repository;

    public PlanningAbsence save(PlanningAbsence absence) {
        return repository.save(absence);
    }

    public List<PlanningAbsence> findAll() {
        return repository.findAll();
    }

    // Récupérer les absences d'un personnel pour un mois spécifique
    public List<PlanningAbsence> findByPersonnelAndMonth(Integer personnelId, int year, int month) {
        Calendar calendar = Calendar.getInstance();

        // Début du mois
        calendar.set(year, month - 1, 1, 0, 0, 0);
        Date startDate = calendar.getTime();

        // Fin du mois
        calendar.set(year, month - 1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        Date endDate = calendar.getTime();

        return repository.findByPersonnel_IdPersonnelAndLedateBetween(personnelId, startDate, endDate);
    }
}
