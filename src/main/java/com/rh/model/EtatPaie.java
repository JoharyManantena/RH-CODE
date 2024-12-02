package com.rh.model;

import java.text.DecimalFormat;
import java.util.Date;

public class EtatPaie {
    private Date datePaie;
    private String numMatricule; //
    private String numCNaPS;//
    private String nom;//
    private String prenom;//
    private Date dateEmbauche;//
    private int absenceMois;//
    private String categorie;//
    private String fonction;//
    private double salaireBase;//
    private double retenuSurAbsence;//
    private double salaireBaseMois;//
    private double heureSuplementaireTotal;//
    private double heureSuplementaire8H;//
    private double heureSuplementaire12H;//
    private double heureSuplementaireDim;//
    private double heureSuplementaireFerie;//
    private double heureSuplementaireMinuit;//
    private double tauxJournalier;//
    private double tauxHoraires;//
    private double salaireBrute;//
    private double droitConge;
    private double droitPreavis;
    private double indemnite;
    private double retenuCNaPS1;//
    private double retenuSanitaire;//
    private double retenuCNaPS8;//
    private double retenuSanitaire8;//
    private double irsaInf350;//
    private double irsaEntre350et400;//
    private double irsaEntre400et50;//
    private double irsaEntre500et600;//
    private double irsaSup600;//
    private double totalIrsa;//
    private double totalRetenue;//
    private double netApayer;//
    private double montantImposable;//
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public EtatPaie(){}


    public double getTotalRetenue() {
        return totalRetenue;
    }

    public void setTotalRetenue(double totalRetenue) {
        this.totalRetenue = totalRetenue;
    }

    public double getRetenuSanitaire8() {
        return retenuSanitaire8;
    }

    public void setRetenuSanitaire8(double retenuSanitaire8) {
        this.retenuSanitaire8 =  Math.round(retenuSanitaire8 * 100.0) / 100.0;
    }

    public double getRetenuCNaPS8() {
        return retenuCNaPS8;
    }

    public void setRetenuCNaPS8(double retenuCNaPS8) {
        this.retenuCNaPS8 = Math.round(retenuCNaPS8 * 100.0) / 100.0;
    }

    public double getSalaireBrute() {
        return salaireBrute;
    }

    public void setSalaireBrute(double salaireBrute) {
        this.salaireBrute = Math.round(salaireBrute * 100.0) / 100.0;
    }

    public Date getDatePaie() {
        return datePaie;
    }
    public void setDatePaie(Date datePaie) {
        this.datePaie = datePaie;
    }
    public String getNumMatricule() {
        return numMatricule;
    }
    public void setNumMatricule(String numMatricule) {
        this.numMatricule = numMatricule;
    }
    public String getNumCNaPS() {
        return numCNaPS;
    }
    public void setNumCNaPS(String numCNaPS) {
        this.numCNaPS = numCNaPS;
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
    public Date getDateEmbauche() {
        return dateEmbauche;
    }
    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
    public int getAbsenceMois() {
        return absenceMois;
    }
    public void setAbsenceMois(int absenceMois) {
        this.absenceMois = absenceMois;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public String getFonction() {
        return fonction;
    }
    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    public double getSalaireBase() {
        return salaireBase;
    }
    public void setSalaireBase(double salaireBase) {
        this.salaireBase = Math.round(salaireBase * 100.0) / 100.0;
    }
    public double getRetenuSurAbsence() {
        return retenuSurAbsence;
    }
    public void setRetenuSurAbsence(double retenuSurAbsence) {
        this.retenuSurAbsence = Math.round(retenuSurAbsence * 100.0) / 100.0;
    }
    public double getSalaireBaseMois() {
        return salaireBaseMois;
    }
    public void setSalaireBaseMois(double salaireBaseMois) {
        this.salaireBaseMois = Math.round(salaireBaseMois * 100.0) / 100.0;
    }
    public double getHeureSuplementaireTotal() {
        return heureSuplementaireTotal;
    }
    public void setHeureSuplementaireTotal(double heureSuplementaireTotal) {
        this.heureSuplementaireTotal = Math.round(heureSuplementaireTotal * 100.0) / 100.0;
    }
    public double getHeureSuplementaire8H() {
        return heureSuplementaire8H;
    }
    public void setHeureSuplementaire8H(double heureSuplementaire8H) {
        this.heureSuplementaire8H = Math.round(heureSuplementaire8H * 100.0) / 100.0;
    }
    public double getHeureSuplementaire12H() {
        return heureSuplementaire12H;
    }
    public void setHeureSuplementaire12H(double heureSuplementaire12H) {
        this.heureSuplementaire12H = Math.round(heureSuplementaire12H * 100.0) / 100.0;
    }
    public double getHeureSuplementaireDim() {
        return heureSuplementaireDim;
    }
    public void setHeureSuplementaireDim(double heureSuplementaireDim) {
        this.heureSuplementaireDim = Math.round(heureSuplementaireDim * 100.0) / 100.0;
    }
    public double getHeureSuplementaireFerie() {
        return heureSuplementaireFerie;
    }
    public void setHeureSuplementaireFerie(double heureSuplementaireFerie) {
        this.heureSuplementaireFerie = Math.round(heureSuplementaireFerie * 100.0) / 100.0;
    }
    public double getHeureSuplementaireMinuit() {
        return heureSuplementaireMinuit;
    }
    public void setHeureSuplementaireMinuit(double heureSuplementaireMinuit) {
        this.heureSuplementaireMinuit = Math.round(heureSuplementaireMinuit * 100.0) / 100.0;
    }
    public double getTauxJournalier() {
        return tauxJournalier;
    }
    public void setTauxJournalier(double tauxJournalier) {
        this.tauxJournalier = Math.round(tauxJournalier * 100.0) / 100.0;
    }
    public double getTauxHoraires() {
        return tauxHoraires;
    }
    public void setTauxHoraires(double tauxHoraires) {
        this.tauxHoraires = Math.round(tauxHoraires * 100.0) / 100.0;
    }
    public double getDroitConge() {
        return droitConge;
    }
    public void setDroitConge(double droitConge) {
        this.droitConge = droitConge;
    }
    public double getDroitPreavis() {
        return droitPreavis;
    }
    public void setDroitPreavis(double droitPreavis) {
        this.droitPreavis = droitPreavis;
    }
    public double getIndemnite() {
        return indemnite;
    }
    public void setIndemnite(double indemnite) {
        this.indemnite = indemnite;
    }
    public double getRetenuCNaPS1() {
        return retenuCNaPS1;
    }
    public void setRetenuCNaPS1(double retenuCNaPS1) {
        this.retenuCNaPS1 = Math.round(retenuCNaPS1 * 100.0) / 100.0;
    }
    public double getRetenuSanitaire() {
        return retenuSanitaire;
    }
    public void setRetenuSanitaire(double retenuSanitaire) {
        this.retenuSanitaire = Math.round(retenuSanitaire * 100.0) / 100.0;
    }
    public double getIrsaInf350() {
        return irsaInf350;
    }
    public void setIrsaInf350(double irsaInf350) {
        this.irsaInf350 = Math.round(irsaInf350 * 100.0) / 100.0;
    }
    public double getIrsaEntre350et400() {
        return irsaEntre350et400;
    }
    public void setIrsaEntre350et400(double irsaEntre350et400) {
        this.irsaEntre350et400 = Math.round(irsaEntre350et400 * 100.0) / 100.0;
    }
    public double getIrsaEntre400et50() {
        return irsaEntre400et50;
    }
    public void setIrsaEntre400et50(double irsaEntre400et50) {
        this.irsaEntre400et50 = Math.round(irsaEntre400et50 * 100.0) / 100.0;
    }
    public double getIrsaEntre500et600() {
        return irsaEntre500et600;
    }
    public void setIrsaEntre500et600(double irsaEntre500et600) {
        this.irsaEntre500et600 = Math.round(irsaEntre500et600 * 100.0) / 100.0;
    }
    public double getIrsaSup600() {
        return irsaSup600;
    }
    public void setIrsaSup600(double irsaSup600) {
        this.irsaSup600 = Math.round(irsaSup600 * 100.0) / 100.0;
    }
    public double getTotalIrsa() {
        return totalIrsa;
    }
    public void setTotalIrsa(double totalIrsa) {
        this.totalIrsa = Math.round(totalIrsa * 100.0) / 100.0;
    }
    public double getNetApayer() {
        return netApayer;
    }
    public void setNetApayer(double netApayer) {
        this.netApayer = Math.round(netApayer * 100.0) / 100.0;
    }
    public double getMontantImposable() {
        return montantImposable;
    }
    public void setMontantImposable(double montantImposable) {
        this.montantImposable = Math.round(montantImposable * 100.0) / 100.0;
    }
}
