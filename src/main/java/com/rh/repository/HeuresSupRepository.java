package com.rh.repository;

import com.rh.model.HeuresSup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface HeuresSupRepository extends JpaRepository<HeuresSup, Integer> {
    // Récupérer les heures supplémentaires pour un personnel donné entre deux dates
    List<HeuresSup> findByPersonnel_IdPersonnelAndDateHeureSupBetween(Integer idPersonnel, Date startDate, Date endDate);
}
