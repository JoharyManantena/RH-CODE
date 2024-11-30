package com.rh.model.FrontOffice;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "besoin_recrutement")
public class BesoinRecrutement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_besoin_recrutement")
    private Integer idBesoinRecrutement;

    @Column(name = "id_departement", nullable = false)
    private Integer idDepartement;

    @Column(name = "date_demande", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDemande;

    @Column(name = "annees_experience")
    private Integer anneesExperience;

    @ManyToOne
    @JoinColumn(name = "id_diplome", nullable = false) // Clé étrangère vers Diplome
    private Diplome diplome;

    @Column(name = "nombre_besoin")
    private Integer nombreBesoin;
    // Getters et Setters

    public Integer getNombreBesoin() {
        return nombreBesoin;
    }

    public void setNombreBesoin(Integer nombreBesoin) {
        this.nombreBesoin = nombreBesoin;
    }

    public Integer getIdBesoinRecrutement() {
        return idBesoinRecrutement;
    }

    public void setIdBesoinRecrutement(Integer idBesoinRecrutement) {
        this.idBesoinRecrutement = idBesoinRecrutement;
    }

    public Integer getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(Integer idDepartement) {
        this.idDepartement = idDepartement;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
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
}
