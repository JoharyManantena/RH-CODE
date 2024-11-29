package com.rh.model.BackOffice;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "rupture_contrat")
public class RuptureContrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rupture_contrat")
    private Integer idRuptureContrat;

    @Column(name = "date_notification", nullable = false)
    private Date dateNotification;

    @Column(name = "date_homologation")
    private Date dateHomologation;

    @Column(name = "date_entretient")
    private Date dateEntretient;

    @Lob
    @Column(name = "fichier", columnDefinition = "LONGBLOB")
    private byte[] fichier;

    @Column(name = "indemnites", precision = 15, scale = 2)
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
    @JoinColumn(name = "id_peersonel", nullable = false)
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

    public Date getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(Date dateNotification) {
        this.dateNotification = dateNotification;
    }

    public Date getDateHomologation() {
        return dateHomologation;
    }

    public void setDateHomologation(Date dateHomologation) {
        this.dateHomologation = dateHomologation;
    }

    public Date getDateEntretient() {
        return dateEntretient;
    }

    public void setDateEntretient(Date dateEntretient) {
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

