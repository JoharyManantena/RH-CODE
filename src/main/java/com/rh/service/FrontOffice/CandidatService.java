package com.rh.service.FrontOffice;

import org.springframework.stereotype.Service;

import com.rh.model.FrontOffice.Candidat;
import com.rh.repository.CandidatRepository;

@Service
public class CandidatService {
    private final CandidatRepository candidatRepository;

    public CandidatService(CandidatRepository cr){
        this.candidatRepository = cr;
    }

    public Candidat getByEmail(String email){
        return this.candidatRepository.findByEmail(email);
    }

    public Candidat getById(Integer id){
        return this.candidatRepository.findById(id).orElse(null);
    }

    public Candidat authenticate(String email, String password) {
        Candidat candidat = this.candidatRepository.findByEmail(email);
        if (candidat != null && candidat.getPassword().equals(password)) {
            return candidat;
        }
        return null; // Retourne null si les informations d'identification sont incorrectes
    }
}
