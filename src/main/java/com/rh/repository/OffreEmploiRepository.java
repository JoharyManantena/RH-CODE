package com.rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.FrontOffice.OffreEmploi;

public interface OffreEmploiRepository extends JpaRepository<OffreEmploi, Integer>{
    List<OffreEmploi> findAllByOrderByIdOffreEmploiAsc();
}
