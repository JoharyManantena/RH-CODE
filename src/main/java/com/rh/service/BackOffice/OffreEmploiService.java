package com.rh.service.BackOffice;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.OffreEmploi;
import com.rh.repository.OffreEmploiRepository;

@Service
public class OffreEmploiService {
    private final OffreEmploiRepository offreEmploiRepository;

    public OffreEmploiService(OffreEmploiRepository oer) {
        this.offreEmploiRepository = oer;
    }

    public void enregistrerOffre(OffreEmploi offreEmploi) {
        this.offreEmploiRepository.save(offreEmploi);
    }
}
