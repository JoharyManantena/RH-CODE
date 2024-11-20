package com.rh.service;

import com.rh.model.BesoinRecrutement;
import com.rh.repository.BesoinRecrutementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BesoinRecrutementService {

    @Autowired
    private BesoinRecrutementRepository besoinRecrutementRepository;

    public BesoinRecrutement createBesoinRecrutement(BesoinRecrutement besoinRecrutement) {
        return besoinRecrutementRepository.save(besoinRecrutement);
    }
}

