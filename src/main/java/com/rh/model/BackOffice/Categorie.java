package com.rh.model.BackOffice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorie_personnel")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorie")
    private Integer idCategorie;

    @Column(name = "nom_categorie", length = 50, nullable = false)
    private String nomCategorie;

    @Column(name = "niveau_hierarchique", nullable = false)
    private Integer niveauHierarchique;

    // Getters et setters
    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public Integer getNiveauHierarchique() {
        return niveauHierarchique;
    }

    public void setNiveauHierarchique(Integer niveauHierarchique) {
        this.niveauHierarchique = niveauHierarchique;
    }
}
