package com.rh.repository.conge;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.conge.TypeConge;

public interface TypeCongeRepository extends JpaRepository<TypeConge, Integer> {
    TypeConge findByNomConge(String nomConge);
}

