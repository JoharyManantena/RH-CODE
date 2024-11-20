package com.rh.service;

import com.rh.model.Diplome;
import com.rh.repository.DiplomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiplomeService {

    @Autowired
    private DiplomeRepository diplomeRepository;

    public List<Diplome> getAllDiplomes() {
        return diplomeRepository.findAll();
    }

    public Diplome getDiplomeById(Integer id) {
        Optional<Diplome> diplomeOptional = diplomeRepository.findById(id);
        return diplomeOptional.orElse(null);
    }
    
}

