package com.rh.model.BackOffice;

import jakarta.persistence.*;

@Entity
@Table(name = "type_contrat")
public class TypeContrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Integer idType;

    @Column(name = "nom", length = 100, nullable = false)
    private String nom;

    @Column(name = "abreviation", length = 50)
    private String abreviation;

    // Getters et Setters
    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }
}
