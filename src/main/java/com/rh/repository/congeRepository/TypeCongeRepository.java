package com.rh.repository.congeRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.Conge.TypeConge;

public interface TypeCongeRepository extends JpaRepository<TypeConge, Integer> {
    TypeConge findByNomConge(String nomConge);
}

