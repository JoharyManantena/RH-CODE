package com.rh.service.BackOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.Candidature;
import com.rh.repository.CandidatureRepository;

@Service
public class CandidatureService {
    private final CandidatureRepository candidatureRepository;

    public CandidatureService(CandidatureRepository cr) {
        this.candidatureRepository = cr;
    }

    public List<Candidature> getCandidatureActif(Integer idStatut) {
        return this.candidatureRepository.findByStatutCandidature_IdStatut(idStatut);
    }

    public List<Candidature> getCandidatures() {
        return this.candidatureRepository.findAll();
    }
}
