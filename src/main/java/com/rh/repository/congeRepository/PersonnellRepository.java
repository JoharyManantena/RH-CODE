package com.rh.repository.congeRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.Personnel;

public interface PersonnellRepository extends JpaRepository<Personnel, Integer> {
    List<Personnel> findByNom(String nom);
}


