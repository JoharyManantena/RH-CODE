package com.rh.service.congeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.Conge.HeuresSup;
import com.rh.repository.congeRepository.HeuresSupRepository;

@Service
public class HeuresSupService {

    @Autowired
    private HeuresSupRepository heuresSupRepository;

    public HeuresSup saveHeuresSup(HeuresSup heuresSup) {
        return heuresSupRepository.save(heuresSup);
    }

    public List<HeuresSup> getAllHeuresSup() {
        return heuresSupRepository.findAll();
    }

    public Optional<HeuresSup> getHeuresSupById(Integer id) {
        return heuresSupRepository.findById(id);
    }

    public void deleteHeuresSup(Integer id) {
        heuresSupRepository.deleteById(id);
    }
}

