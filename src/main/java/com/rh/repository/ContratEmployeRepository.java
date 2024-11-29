package com.rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.ContratEmploye;

public interface ContratEmployeRepository extends JpaRepository <ContratEmploye, Integer> {
    List<ContratEmploye> findByIdPersonnel(Integer idPersonnel);   
}
