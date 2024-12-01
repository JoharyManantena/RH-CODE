package com.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rh.model.TypeConge;

public interface TypeCongeRepository extends JpaRepository<TypeConge, Integer> {
    TypeConge findByNomConge(String nomConge);
}

