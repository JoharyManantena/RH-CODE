package com.rh.controller.BackOffice;

import jakarta.persistence.*;

@Entity
@Table(name = "categorie_personnel")
public class CategoriePersonnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorie")
    private Integer idCategorie;

    @Column(name = "nom_categorie", length = 50, nullable = false)
    private String nomCategorie;

    @Column(name = "niveau_hierarchique", nullable = false)
    private Integer niveauHierarchique;

    // Getters et Setters
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
