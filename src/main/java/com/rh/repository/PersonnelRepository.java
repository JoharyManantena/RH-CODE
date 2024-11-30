package com.rh.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rh.model.Personnel;

public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {
    List<Personnel> findByNom(String nom);
}


