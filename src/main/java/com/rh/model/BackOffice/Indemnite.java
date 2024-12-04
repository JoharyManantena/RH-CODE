package com.rh.model.BackOffice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="indemnite")
public class Indemnite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_indemnite" )
    private Integer idIndemnite;

    @ManyToOne
    @JoinColumn(name = "id_rupture_contrat", nullable = false)
    private RuptureContrat ruptureContrat;

    @Column(name = "compensatoire")
    private Double compensatoire;

    @Column(name = "conge_non_paye")
    private Double congeNonPaye;
    
    @Column(name = "licencement")
    private Double licencement;

    @Column( name = "specifique")
    private Double specifique;

    @Column( name = "retraite")
    private Double retraite;

    
    public Double getRetraite() {
        return retraite;
    }

    public void setRetraite(Double retraite) {
        this.retraite = retraite;
    }

    public Double getSpecifique() {
        return specifique;
    }

    public void setSpecifique(Double specifique) {
        this.specifique = specifique;
    }

    public Double getLicencement() {
        return licencement;
    }

    public void setLicencement(Double licencement) {
        this.licencement = licencement;
    }

    public Double getCongeNonPaye() {
        return congeNonPaye;
    }

    public void setCongeNonPaye(Double congeNonPaye) {
        this.congeNonPaye = congeNonPaye;
    }

    public Double getCompensatoire() {
        return compensatoire;
    }

    public void setCompensatoire(Double compensatoire) {
        this.compensatoire = compensatoire;
    }

    public RuptureContrat getRuptureContrat() {
        return ruptureContrat;
    }

    public void setRuptureContrat(RuptureContrat ruptureContrat) {
        this.ruptureContrat = ruptureContrat;
    }

    public Integer getIdIndemnite() {
        return idIndemnite;
    }

    public void setIdIndemnite(Integer idIndemnite) {
        this.idIndemnite = idIndemnite;
    }
}
