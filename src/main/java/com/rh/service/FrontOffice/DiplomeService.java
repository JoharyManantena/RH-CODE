package com.rh.service.FrontOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rh.model.FrontOffice.Diplome;
import com.rh.repository.DiplomeRepository;

@Service
public class DiplomeService {

    private final DiplomeRepository diplomeRepository;

    public DiplomeService(DiplomeRepository dr){
        this.diplomeRepository = dr;
    }

    public List<Diplome> getAll(){
        return this.diplomeRepository.findAll();
    }
}
