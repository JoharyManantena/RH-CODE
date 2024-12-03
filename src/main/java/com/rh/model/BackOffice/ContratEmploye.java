package com.rh.model.BackOffice;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contrat_employe")
public class ContratEmploye {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrat_employe")
    private Integer idContratEmploye;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "etat")
    private Integer Etat;

    @Column(name = "mois_preavis", nullable = false) // Respectez la casse exacte de la colonne
    private Integer MoisPreavis;


    @ManyToOne
    @JoinColumn(name = "id_personnel", nullable = false)
    private Personnel personnel;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private TypeContrat typeContrat;



    // Getters et Setters

    public Integer getMoisPreavis() {
        return MoisPreavis;
    }

    public void setMoisPreavis(Integer moisPreavi) {
        MoisPreavis = moisPreavi;
    }
    public Integer getIdContratEmploye() {
        return idContratEmploye;
    }

    public void setIdContratEmploye(Integer idContratEmploye) {
        this.idContratEmploye = idContratEmploye;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }
}

