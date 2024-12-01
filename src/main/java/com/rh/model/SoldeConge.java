package com.rh.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "solde_conge")
public class SoldeConge {

    @Id
    @Column(name = "id_personnel")
    private Integer idPersonnel;

    @OneToOne
    @JoinColumn(name = "id_personnel", referencedColumnName = "idPersonnel")
    private Personnel personnel;

    @Column(nullable = false)
    private BigDecimal soldeInitial;

    @Column(nullable = false)
    private BigDecimal soldeRestant;

    // Getters et setters
    public Integer getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Integer idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public BigDecimal getSoldeInitial() {
        return soldeInitial;
    }

    public void setSoldeInitial(BigDecimal soldeInitial) {
        if (soldeInitial.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le solde initial ne peut pas être négatif.");
        }
        this.soldeInitial = soldeInitial;
    }

    public BigDecimal getSoldeRestant() {
        return soldeRestant;
    }

    public void setSoldeRestant(BigDecimal soldeRestant) {
        if (soldeRestant.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le solde restant ne peut pas être négatif.");
        }
        this.soldeRestant = soldeRestant;
    }

    public void utiliserConge(BigDecimal joursUtilises) {
        if (joursUtilises.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le nombre de jours utilisés doit être supérieur à 0.");
        }
        if (soldeRestant.compareTo(joursUtilises) < 0) {
            throw new IllegalArgumentException("Le solde restant est insuffisant.");
        }
        this.soldeRestant = this.soldeRestant.subtract(joursUtilises);
    }

    public void reinitialiserSolde() {
        this.soldeRestant = this.soldeInitial;
    }

    public void decrementerSolde(BigDecimal joursUtilises) {
        if (soldeRestant.compareTo(joursUtilises) < 0) {
            throw new IllegalArgumentException("Solde insuffisant pour cette demande.");
        }
        soldeRestant = soldeRestant.subtract(joursUtilises);
    }
}
