package com.rh.service.BackOffice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.DocumentFin;
import com.rh.repository.DocumentFinRepository;

@Service
public class DocumentFinService {
    private final DocumentFinRepository documentFinRepository;

    public DocumentFinService(DocumentFinRepository df){
        this.documentFinRepository = df;
    }

    public List<DocumentFin> getDocumentFinPersonnel(Integer idRuptureContrat) {
        return this.documentFinRepository.findByIdRuptureContratOrderbydateEmissionAsc(idRuptureContrat);
    }

    public DocumentFin enregistrerDocument(DocumentFin documentFin) {
        return this.documentFinRepository.save(documentFin);        
    }
}
