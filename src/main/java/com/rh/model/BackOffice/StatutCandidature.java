package com.rh.model.BackOffice;

import jakarta.persistence.*;

@Entity
@Table(name = "statut_candidature")
public class StatutCandidature {

    @Id
    @Column(name = "id_statut")
    private Integer idStatut;

    @Column(name = "libelle", length = 255)
    private String libelle;

    // Getters et Setters

    public Integer getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(Integer idStatut) {
        this.idStatut = idStatut;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
