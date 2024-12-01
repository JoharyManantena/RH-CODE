package com.rh.model.BackOffice;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "besoin_recrutement")
public class BesoinRecrutement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_besoin_recrutement")
    private Integer idBesoinRecrutement;

    @ManyToOne
    @JoinColumn(name = "id_departement", nullable = false)
    private Departement departement;

    @Column(name = "date_demande", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dateDemande;

    @Column(name = "annees_experience")
    private Integer anneesExperience;

    @ManyToOne
    @JoinColumn(name = "id_diplome", nullable = false) // Clé étrangère vers Diplome
    private Diplome diplome;

    @Column(name = "nombre_besoin")
    private Integer nombreBesoin;

    @Column(name = "est_fait")
    private boolean fait;

    // Getters et Setters

    public Integer getIdBesoinRecrutement() {
        return idBesoinRecrutement;
    }

    public void setIdBesoinRecrutement(Integer idBesoinRecrutement) {
        this.idBesoinRecrutement = idBesoinRecrutement;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public LocalDate getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Integer getAnneesExperience() {
        return anneesExperience;
    }

    public void setAnneesExperience(Integer anneesExperience) {
        this.anneesExperience = anneesExperience;
    }

    public Diplome getDiplome() {
        return diplome;
    }

    public void setDiplome(Diplome diplome) {
        this.diplome = diplome;
    }

    public Integer getNombreBesoin() {
        return nombreBesoin;
    }

    public void setNombreBesoin(Integer nombreBesoin) {
        this.nombreBesoin = nombreBesoin;
    }

    public void setFait(boolean fait) {
        this.fait = fait;
    }   

    public boolean isFait() {
        return fait;
    }
}
