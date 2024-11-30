package com.rh.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DemandeConge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDemande;

    @ManyToOne
    @JoinColumn(name = "idPersonnel", nullable = false)
    private Personnel personnel;

    @ManyToOne
    @JoinColumn(name = "idTypeConge", nullable = false)
    private TypeConge typeConge;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDemande;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;

    private String statut = "En attente";

    private Double dureeConge;

    public DemandeConge() {
        this.dateDemande = new Date();
    }

    public Double getDureeConge() {
        return dureeConge;
    }

    public void setDureeConge(Double dureeConge) {
        if (dureeConge <= 0) {
            throw new IllegalArgumentException("La durée du congé doit être supérieure à 0.");
        }
        if (dureeConge > 30) {
            throw new IllegalArgumentException("La durée du congé ne peut pas dépasser 30 jours.");
        }
        this.dureeConge = dureeConge;
    }


    public Integer getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Integer idDemande) {
        this.idDemande = idDemande;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public TypeConge getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(TypeConge typeConge) {
        this.typeConge = typeConge;
    }
}
