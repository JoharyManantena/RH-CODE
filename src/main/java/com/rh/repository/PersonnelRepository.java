package com.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.Personnel;

public interface PersonnelRepository extends JpaRepository <Personnel, Integer>{
   
}
