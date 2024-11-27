package com.rh.model.BackOffice;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "candidature")
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidature")
    private Integer idCandidature;

    @Column(name = "date_postulation", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datePostulation;

    @ManyToOne
    @JoinColumn(name = "id_cv", nullable = false) // Clé étrangère vers CV
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "id_candidat", nullable = false) // Clé étrangère vers Candidat
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "id_offre_emploi", nullable = false) // Clé étrangère vers OffreEmploi
    private OffreEmploi offreEmploi;

    @ManyToOne
    @JoinColumn(name = "id_statut_candidature", nullable = false) // Clé étrangère vers StatutCandidature
    private StatutCandidature statutCandidature;

    // Getters et Setters

    public Integer getIdCandidature() {
        return idCandidature;
    }

    public void setIdCandidature(Integer idCandidature) {
        this.idCandidature = idCandidature;
    }

    public Date getDatePostulation() {
        return datePostulation;
    }

    public void setDatePostulation(Date datePostulation) {
        this.datePostulation = datePostulation;
    }

    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public OffreEmploi getOffreEmploi() {
        return offreEmploi;
    }

    public void setOffreEmploi(OffreEmploi offreEmploi) {
        this.offreEmploi = offreEmploi;
    }

    public StatutCandidature getStatutCandidature() {
        return statutCandidature;
    }

    public void setStatutCandidature(StatutCandidature statutCandidature) {
        this.statutCandidature = statutCandidature;
    }
}
