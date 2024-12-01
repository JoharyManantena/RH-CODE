package com.rh.service.FrontOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rh.model.FrontOffice.OffreEmploi;
import com.rh.repository.OffreEmploiRepository;

@Service
public class OffreEmploiService {

    private final OffreEmploiRepository offreEmploiRepository;

    public OffreEmploiService(OffreEmploiRepository offreEmploiRepository) {
        this.offreEmploiRepository = offreEmploiRepository;
    }

    public List<OffreEmploi> getAll() {
        try {
            List<OffreEmploi> offres = offreEmploiRepository.findAll();
            if (offres.isEmpty()) {
                throw new RuntimeException("Aucune offre d'emploi trouvée.");
            }
            return offres;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des offres d'emploi", e);
        }
    }
}
