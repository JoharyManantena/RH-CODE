package com.rh.service.FrontOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rh.model.FrontOffice.BesoinRecrutement;
import com.rh.repository.BesoinRecrutementRepository;

@Service
public class BesoinRecrutementService {
    private final BesoinRecrutementRepository besoinRecrutementRepository;

    public BesoinRecrutementService(BesoinRecrutementRepository brr){
        this.besoinRecrutementRepository = brr;
    }
    
    public List<BesoinRecrutement> getAll(){
        return this.besoinRecrutementRepository.findAll();
    }

    public BesoinRecrutement getById(Integer id){
        return this.besoinRecrutementRepository.findById(id).orElse(null);
    }


}
