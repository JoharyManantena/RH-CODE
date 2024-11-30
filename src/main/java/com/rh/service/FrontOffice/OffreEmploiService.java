package com.rh.service.FrontOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rh.model.FrontOffice.OffreEmploi;
import com.rh.repository.OffreEmploiRepository;

@Service
public class OffreEmploiService {
    private OffreEmploiRepository offreEmploiRepository;
    public OffreEmploiService (OffreEmploiRepository oer){
        this.offreEmploiRepository = oer;
    }    

    public List<OffreEmploi> getAll(){
        return this.offreEmploiRepository.findAllByOrderByIdOffreEmploiAsc();
 
    }

    public OffreEmploi getById(Integer id){
        return this.offreEmploiRepository.findById(id).orElse(null);
    }
}
