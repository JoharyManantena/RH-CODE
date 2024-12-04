package com.rh.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.ContratEmploye;
import com.rh.model.BackOffice.Personnel;

public interface ContratEmployeRepository extends JpaRepository <ContratEmploye, Integer> {
    
    ContratEmploye findByPersonnel(Personnel personnel);
}
