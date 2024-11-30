package com.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.FrontOffice.Candidat;


public interface CandidatRepository  extends JpaRepository<Candidat , Integer>{
     Candidat findByEmail(String email); 
}
