package com.rh.model.BackOffice.Conge;

package com.rh.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonnel;

    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adresse;
    private Date dateEmbauche;
    private BigDecimal salaire;
    private Integer cumulMois;
    private String poste;

    @OneToMany(mappedBy = "personnel", cascade = CascadeType.ALL)
    private List<DemandeConge> demandesConge;

    public Integer getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Integer idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public BigDecimal getSalaire() {
        return salaire;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
    }

    public Integer getCumulMois() {
        int cumul = calculerCumulMois();
        if (cumul < 13) {
            System.out.println("Attention : Vous ne pouvez pas prendre de congé, votre cumul de mois est insuffisant.");
        }
        return cumulMois;
    }

    public void setCumulMois(Integer cumulMois) {
        int cumul = calculerCumulMois();
        if (cumul < 13) {
            System.out.println("Vous ne pouvez pas prendre de congé, votre cumul de mois est insuffisant.");
        }
        this.cumulMois = cumulMois;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public List<DemandeConge> getDemandesConge() {
        return demandesConge;
    }

    public void setDemandesConge(List<DemandeConge> demandesConge) {
        this.demandesConge = demandesConge;
    }

    public double calculDroitsConges() {
        LocalDate dateEmbaucheLocalDate = this.dateEmbauche.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        int moisService = (int) ChronoUnit.MONTHS.between(dateEmbaucheLocalDate, LocalDate.now());
        double joursParMois = 2.5;
        int moisCumulable = Math.min(moisService, 12);

        return moisCumulable * joursParMois;
    }

    public double calculCongesPris() {
        double congesPris = 0;
        if (this.demandesConge != null) {
            for (DemandeConge demande : this.demandesConge) {
                if ("Validée".equals(demande.getStatut())) {
                    congesPris += demande.getDureeConge();
                }
            }
        }
        return congesPris;
    }

    // Conges restant
    public double calculCongesRestants() {
        double droitsConges = calculDroitsConges();
        double congesPris = calculCongesPris();
        return droitsConges - congesPris;
    }

    // public double calculerCongesSpeciaux(String typeConge, double joursPris) {
    // if ("Congés Spéciaux".equals(typeConge)) {
    // double joursGratuits = 10; // 10 jours gratuits pour congé spécial
    // if (joursPris > joursGratuits) {
    // // Déduire les jours supplémentaires pris au-delà de 10 jours gratuits
    // double joursDeduits = joursPris - joursGratuits;
    // System.out.println("Jours de congé spéciaux déduits du solde : " +
    // joursDeduits);
    // return joursDeduits;
    // }
    // }
    // return 0; // Si aucun congé spécial n'est pris ou si moins de 10 jours pris
    // }

    public int calculerCumulMois() {
        if (this.dateEmbauche != null) {
            LocalDate dateEmbaucheLocalDate = this.dateEmbauche.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            long moisService = ChronoUnit.MONTHS.between(dateEmbaucheLocalDate, LocalDate.now());
            this.cumulMois = (int) moisService;
        }
        return cumulMois;
    }

}
