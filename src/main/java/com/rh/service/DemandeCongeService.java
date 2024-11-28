package com.rh.service;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rh.model.DemandeConge;
import com.rh.repository.DemandeCongeRepository;

@Service
public class DemandeCongeService {

    @Autowired
    private DemandeCongeRepository demandeCongeRepository;

    public List<DemandeConge> getAllDemandes() {
        return demandeCongeRepository.findAll();
    }

    public DemandeConge saveDemande(DemandeConge demandeConge) {
        return demandeCongeRepository.save(demandeConge);
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
