package com.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.Indemnite;
import com.rh.model.BackOffice.RuptureContrat;


public interface IndemniteRepository extends JpaRepository<Indemnite, Integer>{
    Indemnite findByRuptureContrat(RuptureContrat ruptureContrat);
}
