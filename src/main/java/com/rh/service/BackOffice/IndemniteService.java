package com.rh.service.BackOffice;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.Indemnite;
import com.rh.model.BackOffice.RuptureContrat;
import com.rh.repository.IndemniteRepository;

@Service
public class IndemniteService {
    
    private final IndemniteRepository indemniteRepository;

    public IndemniteService(IndemniteRepository ir){
        this.indemniteRepository = ir;
    }

    public Indemnite getByRupture(RuptureContrat ruptureContrat){
        return this.indemniteRepository.findByRuptureContrat(ruptureContrat);
    }

    public Indemnite enregitrerIndemnite(Indemnite indemnite){
        return this.indemniteRepository.save(indemnite);
    }
}
