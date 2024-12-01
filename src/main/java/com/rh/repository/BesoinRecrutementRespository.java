package com.rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.BesoinRecrutement;

public interface BesoinRecrutementRespository extends JpaRepository<BesoinRecrutement, Integer>{
    List<BesoinRecrutement> findByFaitFalse();
}
