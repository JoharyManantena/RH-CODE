package com.rh.repository.conge;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.conge.Personnel;

public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {
    List<Personnel> findByNom(String nom);
}


