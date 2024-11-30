package com.rh.model.BackOffice;



import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rupture_contrat")
public class RuptureContrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rupture_contrat")
    private Integer idRuptureContrat;

    @Column(name = "date_notification", nullable = false)
    private LocalDate dateNotification;

    @Column(name = "date_homologation")
    private LocalDate dateHomologation;

    @Column(name = "date_entretient")
    private LocalDate dateEntretient;

    @Lob
    @Column(name = "fichier", columnDefinition = "LONGBLOB")
    private byte[] fichier;

    @Column(name = "indemnites")
    private Double indemnites;


    @Column(name = "etat")
    private Integer etat;

    @ManyToOne
    @JoinColumn(name = "id_rupture", nullable = false)
    private Rupture rupture;

    @ManyToOne
    @JoinColumn(name = "id_contrat_employe", nullable = false)
    private ContratEmploye contratEmploye;

    @ManyToOne
    @JoinColumn(name = "id_personnel", nullable = false)
    private Personnel personnel;

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    // Getters et Setters
    public Integer getIdRuptureContrat() {
        return idRuptureContrat;
    }

    public void setIdRuptureContrat(Integer idRuptureContrat) {
        this.idRuptureContrat = idRuptureContrat;
    }

    public LocalDate getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(LocalDate dateNotification) {
        this.dateNotification = dateNotification;
    }

    public LocalDate getDateHomologation() {
        return dateHomologation;
    }

    public void setDateHomologation(LocalDate dateHomologation) {
        this.dateHomologation = dateHomologation;
    }

    public LocalDate getDateEntretient() {
        return dateEntretient;
    }

    public void setDateEntretient(LocalDate dateEntretient) {
        this.dateEntretient = dateEntretient;
    }

    public byte[] getFichier() {
        return fichier;
    }

    public void setFichier(byte[] fichier) {
        this.fichier = fichier;
    }

    public Double getIndemnites() {
        return indemnites;
    }

    public void setIndemnites(Double indemnites) {
        this.indemnites = indemnites;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Rupture getRupture() {
        return rupture;
    }

    public void setRupture(Rupture rupture) {
        this.rupture = rupture;
    }

    public ContratEmploye getContratEmploye() {
        return contratEmploye;
    }

    public void setContratEmploye(ContratEmploye contratEmploye) {
        this.contratEmploye = contratEmploye;
    }
}

