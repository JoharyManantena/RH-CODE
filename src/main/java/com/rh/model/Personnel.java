package com.rh.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import java.util.List;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


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
    private Integer cumulMois;
    private String poste;


    @OneToMany(mappedBy = "personnel", cascade = CascadeType.ALL)
    private List<DemandeConge> demandesConge;



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

    public List<DemandeConge> getDemandesConge() {
        return demandesConge;
    }

    public void setDemandesConge(List<DemandeConge> demandesConge) {
        this.demandesConge = demandesConge;
    }

    public int calculDroitsConges() {
        LocalDate dateEmbaucheLocalDate = this.dateEmbauche.toInstant()
                                                            .atZone(ZoneId.systemDefault())
                                                            .toLocalDate();
    
        int moisService = (int) ChronoUnit.MONTHS.between(dateEmbaucheLocalDate, LocalDate.now());
        int droitsConges = 0;
    
        // Calcul des droits en fonction de l'ancienneté
        if (moisService >= 36) { // 3 ans de service
            droitsConges = moisService * (5/2) / 12; // 2.5 jours/mois en moyenne 
        }
    
        return droitsConges;
    }


    public double calculCongesPris() {
        double congesPris = 0;
        if (this.demandesConge != null) {
            for (DemandeConge demande : this.demandesConge) {
                if ("Validée".equals(demande.getStatut())) {
                    congesPris += demande.getDureeConge();
                }
            }
        }
        return congesPris;
    }

    // Conges restant
    public double calculCongesRestants() {
        double droitsConges = calculDroitsConges();
        double congesPris = calculCongesPris();
        return droitsConges - congesPris;
    }

    
    
    
}
