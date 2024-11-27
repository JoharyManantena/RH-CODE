package com.rh.model.BackOffice;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

import com.rh.repository.BesoinRecrutementRespository;

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
    @Temporal(TemporalType.DATE)
    private LocalDate datePublication;

    @Column(name = "date_limite_candidature", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dateLimiteCandidature;

    @ManyToOne
    @JoinColumn(name = "id_besoin_recrutement", nullable = false, unique = true) // Clé étrangère
    private BesoinRecrutement besoinRecrutement;

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



    public OffreEmploi(String titre, String description, LocalDate dateOffre, String responsabilite, LocalDate dateLimite) {
        setTitrePoste(titre);
        setDescriptionPoste(description);
        setDatePublication(dateOffre);
        setResponsabilitePrincipale(responsabilite);
        setDateLimiteCandidature(dateLimite);
    }

    public OffreEmploi() {
        
    }
}
