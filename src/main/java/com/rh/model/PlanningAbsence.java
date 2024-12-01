package com.rh.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PlanningAbsence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_personnel", referencedColumnName = "idPersonnel", nullable = false)
    private Personnel personnel;

    @Column(nullable = false)
    private Date dateDebut;

    @Column(nullable = false)
    private Date dateFin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        if (dateDebut == null) {
            throw new IllegalArgumentException("La date de début est obligatoire.");
        }
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        if (dateFin != null && dateFin.before(dateDebut)) {
            throw new IllegalArgumentException("La date de fin ne peut pas être avant la date de début.");
        }
        this.dateFin = dateFin;
    }

    /**
     * Vérifie si une absence chevauche une période donnée.
     * 
     * @param autreDebut La date de début de l'autre période.
     * @param autreFin La date de fin de l'autre période.
     * @return True si les périodes se chevauchent, False sinon.
     */

    public boolean chevauche(Date autreDebut, Date autreFin) {
        if (autreDebut == null || autreFin == null) {
            throw new IllegalArgumentException("Les dates de comparaison ne peuvent pas être nulles.");
        }
        return !(dateFin.before(autreDebut) || dateDebut.after(autreFin));
    }

    // public boolean absenceChevauche(PlanningAbsence autre) {
    //     return !(dateFin.before(autre.dateDebut) || dateDebut.after(autre.dateFin));
    // }
    
}
