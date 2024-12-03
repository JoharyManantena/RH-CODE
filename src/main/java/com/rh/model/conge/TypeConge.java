package com.rh.model.conge;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TypeConge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeConge;

    private String nomConge;

    public Integer getIdTypeConge() {
        return idTypeConge;
    }
    
    public void setIdTypeConge(Integer idTypeConge) {
        this.idTypeConge = idTypeConge;
    }
    
    public String getNomConge() {
        return nomConge;
    }
    
    public void setNomConge(String nomConge) {
        this.nomConge = nomConge;
    }
    
}
