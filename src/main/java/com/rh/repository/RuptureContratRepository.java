package com.rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.Personnel;
import com.rh.model.BackOffice.RuptureContrat;

public interface RuptureContratRepository extends JpaRepository<RuptureContrat, Integer> {

    // Trouver une rupture de contrat par un personnel spécifique
    RuptureContrat findByPersonnel(Personnel personnel);

    // Trouver une rupture de contrat par l'ID du personnel
    RuptureContrat findByPersonnel_IdPersonnel(Integer idPersonnel);

    // Trouver les ruptures de contrat par état, triées par ID
    List<RuptureContrat> findByEtatOrderByIdRuptureContratDesc(Integer etat);
}
