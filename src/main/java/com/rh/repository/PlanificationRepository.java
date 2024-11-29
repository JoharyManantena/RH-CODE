package com.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.PlanificationEntretien;

public interface PlanificationRepository extends JpaRepository<PlanificationEntretien, Integer>{
    
}
