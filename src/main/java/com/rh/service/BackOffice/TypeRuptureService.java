package com.rh.service.BackOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.Rupture;
import com.rh.repository.RuptureRepository;

@Service
public class TypeRuptureService {
    private final RuptureRepository typeRuptureRepository;

    public TypeRuptureService(RuptureRepository tpr){
        this.typeRuptureRepository = tpr;
    }

    public List<Rupture> getAll(){
        return typeRuptureRepository.findAll();
    }

    public Rupture getRuptureById(int id){
        return this.typeRuptureRepository.findById(id).orElse(null);
    }
}
