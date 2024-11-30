package com.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rh.model.BackOffice.Rupture;

public interface RuptureRepository extends JpaRepository<Rupture, Integer> {
}

