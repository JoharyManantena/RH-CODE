package com.rh.model.BackOffice;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private Integer idNotification;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "type_notification", nullable = false)
    private String typeNotification;

    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    @ManyToOne
    @JoinColumn(name = "id_candidat", referencedColumnName = "id_candidat")
    private Candidat candidat; // Référence à la table "candidat"

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutNotification statut;

    // Getters et Setters
    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(String typeNotification) {
        this.typeNotification = typeNotification;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public StatutNotification getStatut() {
        return statut;
    }

    public void setStatut(StatutNotification statut) {
        this.statut = statut;
    }
}
