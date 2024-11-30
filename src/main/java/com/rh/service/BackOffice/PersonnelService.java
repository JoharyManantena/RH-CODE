package com.rh.service.BackOffice;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.Personnel;
import com.rh.repository.PersonnelRepository;

@Service
public class PersonnelService {
    private final PersonnelRepository personnelRepository;
    
    public PersonnelService(PersonnelRepository pr){
        this.personnelRepository = pr;
    }

    public Personnel getPersonnelById(int id){
        return this.personnelRepository.findById(id).orElse(null);
    }
}
