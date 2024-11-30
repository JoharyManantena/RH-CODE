package com.rh.model.FrontOffice;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "postule")
public class Postule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_postule")
    private Integer idPostule;

  
    @ManyToOne
    @JoinColumn(name = "id_candidat", nullable = false )
    private Candidat candidat;


    @ManyToOne
    @JoinColumn(name = "id_offre_emploi", nullable = false )
    private OffreEmploi offreEmploi;


    @Column(name = "nom , length = 50" , nullable = false)
    private String nom;
    

    @Column(name = "prenom , length = 50" , nullable = false)
    private String prenom;

    @Column(name = "date_naissance" , nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "date_postule" , nullable = false)
    private LocalDate datePostule;



    public LocalDate getDatePostule() {
        return datePostule;
    }

    public void setDatePostule(LocalDate datePostule) {
        this.datePostule = datePostule;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Lob
    @Column(name = "cv", columnDefinition = "LONGBLOB")
    private byte[] cv;

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }


    public Integer getIdPostule() {
        return idPostule;
    }

    public void setIdPostule(Integer idPostule) {
        this.idPostule = idPostule;
    }

    public OffreEmploi getOffreEmploi() {
        return offreEmploi;
    }

    public void setOffreEmploi(OffreEmploi offreEmploi) {
        this.offreEmploi = offreEmploi;
    }

    
    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

}
