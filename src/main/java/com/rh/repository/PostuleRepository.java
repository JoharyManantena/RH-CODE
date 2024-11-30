package com.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.FrontOffice.Postule;
import java.util.List;


public interface PostuleRepository extends JpaRepository<Postule, Integer>{
    List<Postule> findByCandidat_IdCandidat(Integer idCandidat);
}
