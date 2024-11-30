package com.rh.service.FrontOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rh.model.FrontOffice.Postule;
import com.rh.repository.PostuleRepository;

@Service
public class PostuleService {
    private PostuleRepository postuleRepository;
    public PostuleService(PostuleRepository pr){
        this.postuleRepository=pr;
    }

    public Postule getById(Integer id){
        return this.postuleRepository.findById(id).orElse(null);
    }

    public List<Postule> getByIdCandidat(Integer idCandidat){
        return this.postuleRepository.findByCandidat_IdCandidat(idCandidat);
    }

    public Postule enregistrerPostule(Postule p){
        return this.postuleRepository.save(p);
    }
}
