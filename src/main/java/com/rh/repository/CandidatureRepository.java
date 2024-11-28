package com.rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.Candidature;

public interface CandidatureRepository extends JpaRepository<Candidature, Integer>{
    
    List<Candidature> findByStatutCandidature_IdStatut(Integer idStatut);
}
