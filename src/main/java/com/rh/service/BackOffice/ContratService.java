package com.rh.service.BackOffice;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.ContratEmploye;
import com.rh.repository.ContratEmployeRepository;

@Service
public class ContratService {
    private final ContratEmployeRepository contratEmployeRepository;

    public ContratService(ContratEmployeRepository cer){
        this.contratEmployeRepository = cer;
    }

    public ContratEmploye getById(int id){
        return this.contratEmployeRepository.findById(id).orElse(null);
    }
}
