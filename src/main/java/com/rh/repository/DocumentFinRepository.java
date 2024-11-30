package com.rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.DocumentFin;

public interface DocumentFinRepository extends JpaRepository <DocumentFin, Integer>{
    List<DocumentFin> findByRuptureContrat_IdRuptureContratOrderByIdDocumentAsc(Integer idRuptureContrat);  
}
