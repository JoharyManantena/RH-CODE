package com.rh.repository.conge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rh.model.conge.HeuresSup;

@Repository
public interface HeuresSupRepository extends JpaRepository<HeuresSup, Integer> {

    // You can define custom queries if needed (e.g., find by personnel)
}

