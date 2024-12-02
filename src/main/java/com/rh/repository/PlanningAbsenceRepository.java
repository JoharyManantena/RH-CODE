package com.rh.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rh.model.PlanningAbsence;

public interface PlanningAbsenceRepository extends JpaRepository<PlanningAbsence, Integer> {
    List<PlanningAbsence> findByPersonnel_IdPersonnelAndLedateBetween(Integer personnelId, Date startDate, Date endDate);
}
