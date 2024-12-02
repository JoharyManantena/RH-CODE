package com.rh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class HeuresSup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHeureSup;

    @ManyToOne
    @JoinColumn(name = "id_personnel", nullable = false)
    private Personnel personnel; // Relation ManyToOne avec Personnel

    @Column(name = "date_heure_sup", nullable = false)
    private Date dateHeureSup;

    @Column(name = "nombre_heures", nullable = false)
    private BigDecimal nombreHeures;

    @Column(name = "description", length = 100, nullable = false)
    private String description;


    // Getters et setters
    public Integer getIdHeureSup() {
        return idHeureSup;
    }

    public void setIdHeureSup(Integer idHeureSup) {
        this.idHeureSup = idHeureSup;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Date getDateHeureSup() {
        return dateHeureSup;
    }

    public void setDateHeureSup(Date dateHeureSup) {
        this.dateHeureSup = dateHeureSup;
    }

    public BigDecimal getNombreHeures() {
        return nombreHeures;
    }

    public void setNombreHeures(BigDecimal nombreHeures) {
        this.nombreHeures = nombreHeures;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
