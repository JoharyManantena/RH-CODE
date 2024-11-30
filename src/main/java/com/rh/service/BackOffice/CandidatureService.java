package com.rh.service.BackOffice;

import java.util.Arrays;
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

    public Candidature getById(int idCandidature) {
        return this.candidatureRepository.findById(idCandidature).orElse(null);
    }

    public Candidature enregistrerCandidature(Candidature candidature) {
        return this.candidatureRepository.save(candidature);
    }

    public List<Candidature> getListeCandidatureArejeter() {
        List<Integer> statutList = Arrays.asList(1, 2);
        return candidatureRepository.findByStatutCandidature_IdStatutIn(statutList);
    }
}
