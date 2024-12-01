package com.rh.model.FrontOffice;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "offre_emploi")
public class OffreEmploi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_offre_emploi")
    private Integer idOffreEmploi;

    @Column(name = "titre_poste", nullable = false, length = 255)
    private String titrePoste;

    @Column(name = "description_poste", columnDefinition = "TEXT")
    private String descriptionPoste;

    @Column(name = "responsabilite_principale", length = 255)
    private String responsabilitePrincipale;

    @Column(name = "fourchette_salaire", length = 255)
    private String fourchetteSalaire;

    @Column(name = "date_publication", nullable = false)
    private LocalDate datePublication;

    @Column(name = "date_limite_candidature", nullable = false)
    private LocalDate dateLimiteCandidature;

    @ManyToOne
    @JoinColumn(name = "id_besoin_recrutement", nullable = false)
    private BesoinRecrutement besoinRecrutement;

    @Column(name = "disponible", nullable = true)
    private Boolean disponible = true; // Valeur par défaut

    // Getters et Setters

    public Integer getIdOffreEmploi() {
        return idOffreEmploi;
    }

    public void setIdOffreEmploi(Integer idOffreEmploi) {
        this.idOffreEmploi = idOffreEmploi;
    }

    public String getTitrePoste() {
        return titrePoste;
    }

    public void setTitrePoste(String titrePoste) {
        this.titrePoste = titrePoste;
    }

    public String getDescriptionPoste() {
        return descriptionPoste;
    }

    public void setDescriptionPoste(String descriptionPoste) {
        this.descriptionPoste = descriptionPoste;
    }

    public String getResponsabilitePrincipale() {
        return responsabilitePrincipale;
    }

    public void setResponsabilitePrincipale(String responsabilitePrincipale) {
        this.responsabilitePrincipale = responsabilitePrincipale;
    }

    public String getFourchetteSalaire() {
        return fourchetteSalaire;
    }

    public void setFourchetteSalaire(String fourchetteSalaire) {
        this.fourchetteSalaire = fourchetteSalaire;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public LocalDate getDateLimiteCandidature() {
        return dateLimiteCandidature;
    }

    public void setDateLimiteCandidature(LocalDate dateLimiteCandidature) {
        this.dateLimiteCandidature = dateLimiteCandidature;
    }

    public BesoinRecrutement getBesoinRecrutement() {
        return besoinRecrutement;
    }

    public void setBesoinRecrutement(BesoinRecrutement besoinRecrutement) {
        this.besoinRecrutement = besoinRecrutement;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    // Constructeurs

    public OffreEmploi(Integer idOffreEmploi, String titre, String salaire, String description, LocalDate dateOffre, String responsabilite, LocalDate dateLimite, BesoinRecrutement besoin, Boolean disponible) {
        this.idOffreEmploi = idOffreEmploi;
        this.titrePoste = titre;
        this.descriptionPoste = description;
        this.datePublication = dateOffre;
        this.responsabilitePrincipale = responsabilite;
        this.dateLimiteCandidature = dateLimite;
        this.fourchetteSalaire = salaire;
        this.besoinRecrutement = besoin;
        this.disponible = disponible;
    }

    public OffreEmploi() {
    }
}
