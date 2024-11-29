package com.rh.model.BackOffice;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "planification_entretien")
public class PlanificationEntretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planification")
    private Integer idPlanification;

    @ManyToOne
    @JoinColumn(name = "id_candidature")
    private Candidature candidature;

    @Column(name = "date_entretien")
    private LocalDateTime dateEntretien;

    public Integer getIdPlanification() {
        return idPlanification;
    }

    public void setIdPlanification(Integer idPlanification) {
        this.idPlanification = idPlanification;
    }

    public Candidature getCandidature() {
        return candidature;
    }

    public void setCandidature(Candidature candidature) {
        this.candidature = candidature;
    }

    public LocalDateTime getDateEntretien() {
        return dateEntretien;
    }

    public void setDateEntretien(LocalDateTime dateEntretien) {
        this.dateEntretien = dateEntretien;
    }

    public PlanificationEntretien() {
        
    }
    public PlanificationEntretien(Candidature candidature, LocalDateTime dateEntretien) {
        this.setCandidature(candidature);
        this.setDateEntretien(dateEntretien);
    }


}
