package com.rh.service.BackOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.RuptureContrat;
import com.rh.repository.RuptureContratRepository;

@Service
public class RuptureContratService {

    private final RuptureContratRepository ruptureContratRepository;
    

    public RuptureContratService(RuptureContratRepository ruptureContratRepository) {
        this.ruptureContratRepository = ruptureContratRepository;
    }

    public RuptureContrat getById(int id){
        return this.ruptureContratRepository.findById(id).orElse(null);
    }

    // Récupérer une rupture de contrat par ID du personnel
    public RuptureContrat getByIdPersonnel(int idPersonnel) {
        return this.ruptureContratRepository.findByPersonnel_IdPersonnel(idPersonnel);
    }

    public List<RuptureContrat> getAll(){
        return this.ruptureContratRepository.findAll();
    }

    
    // Récupérer toutes les ruptures de contrat par état
    public List<RuptureContrat> getIdRuptureContratEtat(int etat) {
        return this.ruptureContratRepository.findByEtatOrderByIdRuptureContratDesc(etat);
    }

    // Enregistrer une nouvelle rupture de contrat
    public RuptureContrat enregistrerRupture(RuptureContrat ruptureContrat) {
        return this.ruptureContratRepository.save(ruptureContrat);
    }

    // Mettre à jour l'état d'une rupture de contrat
    public RuptureContrat updateRuptureEtat(Integer id, RuptureContrat ruptureContratDetails) {
        RuptureContrat existingRuptureContrat = ruptureContratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rupture contrat not found with id: " + id));

        existingRuptureContrat.setEtat(ruptureContratDetails.getEtat());
        existingRuptureContrat.setDateEntretient(ruptureContratDetails.getDateEntretient());
        existingRuptureContrat.setDateHomologation(ruptureContratDetails.getDateHomologation());
        existingRuptureContrat.setIndemnites(ruptureContratDetails.getIndemnites());

        return ruptureContratRepository.save(existingRuptureContrat);
    }

    // Méthodes spécifiques à implémenter dans les repositories associés
}
