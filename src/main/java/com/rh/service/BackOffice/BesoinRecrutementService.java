package com.rh.service.BackOffice;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.BesoinRecrutement;
import com.rh.repository.BesoinRecrutementRespository;

@Service
public class BesoinRecrutementService {
    
    private final BesoinRecrutementRespository besoinRecrutementRespository;

    public BesoinRecrutementService(BesoinRecrutementRespository br) {
        this.besoinRecrutementRespository = br;
    }

    public BesoinRecrutement getById(int id) {
        return besoinRecrutementRespository.findById(id).orElse(null);
    }
}
