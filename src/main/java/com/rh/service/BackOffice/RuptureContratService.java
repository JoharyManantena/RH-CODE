package com.rh.service.BackOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.RuptureContrat;
import com.rh.repository.RuptureContratRepository;

@Service
public class RuptureContratService {
    private final RuptureContratRepository ruptureContratRepository;

    public RuptureContratService(RuptureContratRepository rpc){
        this.ruptureContratRepository = rpc;
    }

    public RuptureContrat getByIdPersonnel(int idPersonnel){
        return this.ruptureContratRepository.findByIdPersonnel(idPersonnel);
    }

    public List<RuptureContrat> getIdRuptureContratEtat(int etat){
        return this.ruptureContratRepository.findByEtatOrderByidRuptureContratAsc(etat);
    }

    public RuptureContrat enregitrerRupture(RuptureContrat rpc){
        return this.ruptureContratRepository.save(rpc);
    }

    public RuptureContrat updateRuptureEtat(Integer id, RuptureContrat rpc){
        if (!ruptureContratRepository.existsById(id)) {
            throw new RuntimeException("Rupture contrat not found with id" + id);
        }

        RuptureContrat existRpc = ruptureContratRepository.findById(id).get();

        existRpc.setEtat(rpc.getEtat());
        existRpc.setDateEntretient(rpc.getDateEntretient());
        existRpc.setDateHomologation(rpc.getDateHomologation());
        existRpc.setIndemnites(rpc.getIndemnites());

        return ruptureContratRepository.save(existRpc);
    }

}
