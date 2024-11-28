package com.rh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer>{
    Optional<Evaluation> findByCandidature_IdCandidature(int idCandidature);
}
