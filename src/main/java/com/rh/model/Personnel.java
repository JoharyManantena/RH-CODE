package com.rh.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonnel;

    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adresse;
    private Date dateEmbauche;
    private BigDecimal salaire;
    private Integer cumulMois; // Nouvelle colonne
    private String poste;

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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public BigDecimal getSalaire() {
        return salaire;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
    }

    public Integer getCumulMois() {
        if (cumulMois < 12) {
            System.out.println("Attention : Vous ne pouvez pas prendre de congé, votre cumul de mois est insuffisant.");
        }
        return cumulMois;
    }
    

    public void setCumulMois(Integer cumulMois) {
        if (cumulMois < 12) {
            System.out.println("Vous ne pouvez pas prendre de congé, votre cumul de mois est insuffisant.");
        }
        this.cumulMois = cumulMois;
    }
    

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
}
