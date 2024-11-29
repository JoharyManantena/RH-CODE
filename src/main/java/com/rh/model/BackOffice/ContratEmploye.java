package com.rh.model.BackOffice;


import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contrat_employe")
public class ContratEmploye {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrat_employe")
    private Integer idContratEmploye;

    @Column(name = "date_debut")
    private Date dateDebut;

    @Column(name = "date_fin")
    private Date dateFin;

    @ManyToOne
    @JoinColumn(name = "id_personnel", nullable = false)
    private Personnel personnel;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private TypeContrat typeContrat;

    // Getters et Setters
    public Integer getIdContratEmploye() {
        return idContratEmploye;
    }

    public void setIdContratEmploye(Integer idContratEmploye) {
        this.idContratEmploye = idContratEmploye;
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

