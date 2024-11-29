package com.rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rh.model.BackOffice.Candidature;
import com.rh.model.BackOffice.StatutCandidature;

public interface CandidatureRepository extends JpaRepository<Candidature, Integer>{
    
    List<Candidature> findByStatutCandidature_IdStatut(Integer idStatut);
    
    @Modifying
    @Query("UPDATE Candidature c SET c.statutCandidature = :statut WHERE c.idCandidature = :id")
    int updateStatutById(@Param("id") Integer id, @Param("statut") StatutCandidature statut);
}
