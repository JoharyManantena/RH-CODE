package com.rh.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "heures_sup")
public class HeuresSup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_heure_sup")
    private Integer idHeureSup;

    @ManyToOne
    @JoinColumn(name = "id_personnel", referencedColumnName = "idPersonnel")
    private Personnel personnel;  // Relation avec Personnel

    @Column(name = "date_heure_sup", nullable = false)
    private LocalDate dateHeureSup;

    @Column(name = "nombre_heures", nullable = false)
    private BigDecimal nombreHeures;

    @Column(name = "taux_horaire", nullable = false)
    private BigDecimal tauxHoraire;

    @Column(name = "montant_total", nullable = false)
    private BigDecimal montantTotal;


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

    public LocalDate getDateHeureSup() {
        return dateHeureSup;
    }

    public void setDateHeureSup(LocalDate dateHeureSup) {
        this.dateHeureSup = dateHeureSup;
    }

    public BigDecimal getNombreHeures() {
        return nombreHeures;
    }

    public void setNombreHeures(BigDecimal nombreHeures) {
        this.nombreHeures = nombreHeures;
    }

    public BigDecimal getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(BigDecimal tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }
}

