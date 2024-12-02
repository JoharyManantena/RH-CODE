package com.rh.model.BackOffice;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.rh.controller.BackOffice.CategoriePersonnel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "personnel")
public class Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personnel")
    private Integer idPersonnel;

    @Column(name = "nom", length = 255, nullable = false)
    private String nom;

    @Column(name = "prenom", length = 255, nullable = false)
    private String prenom;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "adresse", length = 255)
    private String adresse;

    @Column(name = "date_embauche")
    private LocalDate dateEmbauche;

    @Column(name = "salaire", precision = 15, scale = 2)
    private BigDecimal salaire;

    @ManyToOne
    @JoinColumn(name = "id_departement")
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private CategoriePersonnel categorie;

    @Column(name = "poste", length = 50)
    private String poste;

    @OneToOne
    @JoinColumn(name = "id_cv", nullable = false, unique = true)
    private Cv cv;

    // Getters et Setters
    public Integer getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Integer idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public BigDecimal getSalaire() {
        return salaire;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public CategoriePersonnel getCategorie() {
        return categorie;
    }

    public void setCategorie(CategoriePersonnel categorie) {
        this.categorie = categorie;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }
}
