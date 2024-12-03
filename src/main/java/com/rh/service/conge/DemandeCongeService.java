package com.rh.service.conge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.rh.model.conge.DemandeConge;
import com.rh.repository.conge.DemandeCongeRepository;

@Service
public class DemandeCongeService {

    @Autowired
    private DemandeCongeRepository demandeCongeRepository;

    public List<DemandeConge> getAllDemandes() {
        return demandeCongeRepository.findAll();
    }

    
    @Transactional
    public DemandeConge saveDemande(DemandeConge demandeConge) {
        // Sauvegarder la demande de congé sans validation immédiate
        DemandeConge savedDemande = demandeCongeRepository.save(demandeConge);
        // try {
        //     demandeCongeRepository.validerDemandeConge(savedDemande.getIdDemande());
        // } catch (Exception e) {
        //     throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de l'appel à la procédure stockée", e);
        // }
    
        return savedDemande;
    }
    

    public DemandeConge getDemandeById(Integer idDemande) {
        return demandeCongeRepository.findById(idDemande)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Demande de congé non trouvée"));
    }

    public List<DemandeConge> getDemandesByPersonnelId(Integer idPersonnel) {
        return demandeCongeRepository.findByPersonnelIdPersonnel(idPersonnel);
    }

    public List<DemandeConge> getDemandesByStatut(String statut) {
        return demandeCongeRepository.findByStatut(statut);
    }

    public DemandeConge updateDemande(DemandeConge demandeConge) {
        if (demandeCongeRepository.existsById(demandeConge.getIdDemande())) {
            return demandeCongeRepository.save(demandeConge);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Demande de congé non trouvée");
        }
    }

    public void deleteDemande(Integer idDemande) {
        if (demandeCongeRepository.existsById(idDemande)) {
            demandeCongeRepository.deleteById(idDemande);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Demande de congé non trouvée");
        }
    }
}
