package com.rh.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personnel")
    private Integer idPersonnel;

    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adresse;
    private Date dateEmbauche;
    private BigDecimal salaire;
    private Integer cumulMois;
    private String poste;
    private String numMatricule;
    private String numCNaPS;


    private int id_secteur;
    private String categorie;


    @OneToMany(mappedBy = "personnel", cascade = CascadeType.ALL)
    private List<DemandeConge> demandesConge;

    @OneToOne(mappedBy = "personnel", cascade = CascadeType.ALL)
    private SoldeConge soldeConge;

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    public int getId_secteur() {
        return id_secteur;
    }

    public void setId_secteur(int id_secteur) {
        this.id_secteur = id_secteur;
    }

    public String getNumCNaPS() {
        return numCNaPS;
    }

    public void setNumCNaPS(String numCNaPS) {
        this.numCNaPS = numCNaPS;
    }

    public String getNumMatricule() {
        return numMatricule;
    }

    public void setNumMatricule(String numMatricule) {
        this.numMatricule = numMatricule;
    }

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
        if (cumulMois < 12) {
            System.out.println("Attention : Vous ne pouvez pas prendre de congé, votre cumul de mois est insuffisant.");
        }
        return cumulMois;
    }
    

    public void setCumulMois(Integer cumulMois) {
        if (cumulMois < 12) {
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

    public SoldeConge getSoldeConge() {
        return soldeConge;
    }

    public void setSoldeConge(SoldeConge soldeConge) {
        this.soldeConge = soldeConge;
    }


    public BigDecimal calculerDroitsConges() {
        long months = ChronoUnit.MONTHS.between(
            dateEmbauche.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
            LocalDate.now()
        );
        BigDecimal droitsCumules = BigDecimal.valueOf(months * 2.5);
        BigDecimal plafond = BigDecimal.valueOf(90); // 3 ans x 30 jours
        return droitsCumules.min(plafond); // Appliquer le plafond
    }

}
