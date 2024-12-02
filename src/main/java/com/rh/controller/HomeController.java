package com.rh.controller;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rh.model.EtatPaie;
import com.rh.model.HeuresSup;
import com.rh.model.Personnel;
import com.rh.model.PlanningAbsence;
import com.rh.repository.PersonnelRepository;
import com.rh.service.HeuresSupService;
import com.rh.service.PlanningAbsenceService;

@Controller
public class HomeController {
    private static double smig = 25000;
    

    public static double getSmig() {
        return smig;
    }

    public static void setSmig(double smig) {
        HomeController.smig = smig;
    }

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private PlanningAbsenceService planningAbsenceService;

    @Autowired
    private HeuresSupService heuresSupService;

    @PostMapping("/etat")
    public String calculePaie(@RequestParam("mois") String mois,Model model) {

        String[] parts = mois.split("-");
        int annee = Integer.parseInt(parts[0]);
        int moisPart = Integer.parseInt(parts[1]);
        List<Personnel> personnels = personnelRepository.findAll();
        List<EtatPaie> listeEtat = new ArrayList<EtatPaie>();
        for (int i = 0; i < personnels.size(); i++) {
            EtatPaie temp = new EtatPaie();

            temp.setNumMatricule(personnels.get(i).getNumMatricule());
            temp.setNumCNaPS(personnels.get(i).getNumCNaPS());
            temp.setNom(personnels.get(i).getNom());
            temp.setPrenom(personnels.get(i).getPrenom());
            temp.setDateEmbauche(personnels.get(i).getDateEmbauche());
            temp.setFonction(personnels.get(i).getPoste());
            temp.setSalaireBase((personnels.get(i).getSalaire()).doubleValue());
            temp.setCategorie(personnels.get(i).getCategorie());

            //Absence du mois
            List<PlanningAbsence> pl = planningAbsenceService.findByPersonnelAndMonth(personnels.get(i).getIdPersonnel(),annee,moisPart);
            int durree = 0;
            for (int j = 0; j < pl.size(); j++) {
                durree += pl.get(j).getDurree();
            }
            temp.setAbsenceMois(durree);

            //Calcule des taux
            temp.setTauxJournalier(temp.getSalaireBase() / 30);
            // 1 = Secteur Agricole
            if (personnels.get(i).getId_secteur() == 1) {
                temp.setTauxHoraires(temp.getSalaireBase() / 200);
            }else{
                temp.setTauxHoraires(temp.getSalaireBase() / 173.33);
            }

            //retenu sur absence
            temp.setRetenuSurAbsence(temp.getAbsenceMois()*temp.getTauxHoraires());

            //salaire base de mois
            temp.setSalaireBaseMois(temp.getSalaireBase() - temp.getRetenuSurAbsence());

            //Les Heures superieure
            List<HeuresSup> sup = heuresSupService.getHeuresSupForPersonnelAndMonth(personnels.get(i).getIdPersonnel(),annee,moisPart);
            temp.setHeureSuplementaire8H(0);
            temp.setHeureSuplementaire12H(0);
            temp.setHeureSuplementaireDim(0);
            temp.setHeureSuplementaireFerie(0);
            temp.setHeureSuplementaireMinuit(0);
            for (int j = 0; j < sup.size(); j++) {
                if (sup.get(j).getDescription().equals("8H")) {
                    temp.setHeureSuplementaire8H((temp.getTauxHoraires() * sup.get(j).getNombreHeures().doubleValue() * 1.3));
                }else if (sup.get(j).getDescription().equals("12H")) {
                    temp.setHeureSuplementaire12H((temp.getTauxHoraires() * sup.get(j).getNombreHeures().doubleValue() * 1.5));
                }else if (sup.get(j).getDescription().equals("DIM")) {
                    temp.setHeureSuplementaireDim((temp.getTauxHoraires() * sup.get(j).getNombreHeures().doubleValue() * 1.4));
                }else if (sup.get(j).getDescription().equals("FERIE")) {
                    temp.setHeureSuplementaireFerie((temp.getTauxHoraires() * sup.get(j).getNombreHeures().doubleValue() * 2));
                }else{
                    temp.setHeureSuplementaireMinuit((temp.getTauxHoraires() * sup.get(j).getNombreHeures().doubleValue() * 1.3));
                }
            }
            temp.setHeureSuplementaireTotal(temp.getHeureSuplementaire8H()+temp.getHeureSuplementaire12H()+temp.getHeureSuplementaireDim()+temp.getHeureSuplementaireFerie()+temp.getHeureSuplementaireMinuit());


            //Salaire brute
            temp.setSalaireBrute(temp.getSalaireBaseMois() - temp.getHeureSuplementaireTotal());

            //Retenue cnaps 1% et sanitaire
            temp.setRetenuCNaPS1(getSmig() * 8 * 0.01);
            temp.setRetenuSanitaire(temp.getSalaireBrute() * 0.01);
            if (temp.getRetenuCNaPS1() > temp.getRetenuSanitaire() ) {
                temp.setRetenuCNaPS1(temp.getRetenuSanitaire());
            }

            //Retenue cnaps 8% et sanitaire
            temp.setRetenuCNaPS8(temp.getRetenuCNaPS1() * 8);
            temp.setRetenuSanitaire8(temp.getRetenuSanitaire()*5);

            //Montant imposable
            temp.setMontantImposable(temp.getSalaireBrute() - temp.getRetenuCNaPS1() - temp.getRetenuSanitaire());


            //IRSA
            temp.setIrsaInf350(0);
            temp.setIrsaEntre350et400(50000 * 0.05);
            temp.setIrsaEntre400et50(100000 * 0.1);
            temp.setIrsaEntre500et600(100000 * 0.15);
            temp.setIrsaSup600(Math.abs(temp.getMontantImposable() - 600000) * 0.2);
            temp.setTotalIrsa(temp.getIrsaInf350() + temp.getIrsaEntre350et400() + temp.getIrsaEntre400et50() + temp.getIrsaEntre500et600() + temp.getIrsaSup600());


            //Total retenue
            temp.setTotalRetenue(temp.getTotalIrsa() + temp.getRetenuCNaPS1() + temp.getRetenuSanitaire());

            //Net a payer
            temp.setIndemnite(0);
            temp.setNetApayer(temp.getSalaireBrute() - temp.getTotalRetenue() + temp.getIndemnite());


            listeEtat.add(temp);
        }
        
        model.addAttribute("liste", listeEtat);
        String moisTexte = Month.of(moisPart).getDisplayName(java.time.format.TextStyle.FULL, Locale.FRANCE);
        model.addAttribute("mois", moisTexte);
        model.addAttribute("moisInt", moisPart);
        model.addAttribute("annee", annee);
        return "etat_paie";
    }

    @GetMapping("/formetat")
    public String showForm(Model model) {
        return "form_etat_paie";
    }

    @PostMapping("/fichepaie")
    public String showFiche(@RequestParam("mois") int mois,@RequestParam("annees") int annees,@RequestParam("numMatricule") String numMatricule,@RequestParam("numCNaPS") String numCNaPS,Model model) {
        List<Personnel> personnels = personnelRepository.findAll();
        EtatPaie temp = new EtatPaie();
        for (int i = 0; i < personnels.size(); i++) {
            if (personnels.get(i).getNumMatricule().equals(numMatricule) && personnels.get(i).getNumCNaPS().equals(numCNaPS)) {
                temp.setNumMatricule(personnels.get(i).getNumMatricule());
                temp.setNumCNaPS(personnels.get(i).getNumCNaPS());
                temp.setNom(personnels.get(i).getNom());
                temp.setPrenom(personnels.get(i).getPrenom());
                temp.setDateEmbauche(personnels.get(i).getDateEmbauche());
                temp.setFonction(personnels.get(i).getPoste());
                temp.setSalaireBase((personnels.get(i).getSalaire()).doubleValue());
                temp.setCategorie(personnels.get(i).getCategorie());

                //Absence du mois
                List<PlanningAbsence> pl = planningAbsenceService.findByPersonnelAndMonth(personnels.get(i).getIdPersonnel(),annees,mois);
                int durree = 0;
                for (int j = 0; j < pl.size(); j++) {
                    durree += pl.get(j).getDurree();
                }
                temp.setAbsenceMois(durree);

                //Calcule des taux
                temp.setTauxJournalier(temp.getSalaireBase() / 30);
                // 1 = Secteur Agricole
                if (personnels.get(i).getId_secteur() == 1) {
                    temp.setTauxHoraires(temp.getSalaireBase() / 200);
                }else{
                    temp.setTauxHoraires(temp.getSalaireBase() / 173.33);
                }

                //retenu sur absence
                temp.setRetenuSurAbsence(temp.getAbsenceMois()*temp.getTauxHoraires());

                //salaire base de mois
                temp.setSalaireBaseMois(temp.getSalaireBase() - temp.getRetenuSurAbsence());

                //Les Heures superieure
                List<HeuresSup> sup = heuresSupService.getHeuresSupForPersonnelAndMonth(personnels.get(i).getIdPersonnel(),annees,mois);
                temp.setHeureSuplementaire8H(0);
                temp.setHeureSuplementaire12H(0);
                temp.setHeureSuplementaireDim(0);
                temp.setHeureSuplementaireFerie(0);
                temp.setHeureSuplementaireMinuit(0);
                double taux8H = (temp.getTauxHoraires() * 1.3);
                double taux12H = (temp.getTauxHoraires() * 1.5);
                double tauxDim = (temp.getTauxHoraires() * 1.4);
                double tauxFerie = (temp.getTauxHoraires() * 2);
                double tauxMinuit = (temp.getTauxHoraires() * 1.3);
                model.addAttribute("taux8H", taux8H);
                model.addAttribute("taux12H", taux12H);
                model.addAttribute("tauxDim", tauxDim);
                model.addAttribute("tauxFerie", tauxFerie);
                model.addAttribute("tauxMinuit", tauxMinuit);
                for (int j = 0; j < sup.size(); j++) {
                    if (sup.get(j).getDescription().equals("8H")) {
                        model.addAttribute("nb8H", sup.get(j).getNombreHeures().doubleValue());
                        temp.setHeureSuplementaire8H((temp.getTauxHoraires() * sup.get(j).getNombreHeures().doubleValue() * 1.3));
                    }else if (sup.get(j).getDescription().equals("12H")) {
                        model.addAttribute("nb12H", sup.get(j).getNombreHeures().doubleValue());
                        temp.setHeureSuplementaire12H((temp.getTauxHoraires() * sup.get(j).getNombreHeures().doubleValue() * 1.5));
                    }else if (sup.get(j).getDescription().equals("DIM")) {
                        model.addAttribute("nbDim", sup.get(j).getNombreHeures().doubleValue());
                        temp.setHeureSuplementaireDim((temp.getTauxHoraires() * sup.get(j).getNombreHeures().doubleValue() * 1.4));
                    }else if (sup.get(j).getDescription().equals("FERIE")) {
                        model.addAttribute("nbFerie", sup.get(j).getNombreHeures().doubleValue());
                        temp.setHeureSuplementaireFerie((temp.getTauxHoraires() * sup.get(j).getNombreHeures().doubleValue() * 2));
                    }else{
                        model.addAttribute("nbMinuit", sup.get(j).getNombreHeures().doubleValue());
                        temp.setHeureSuplementaireMinuit((temp.getTauxHoraires() * sup.get(j).getNombreHeures().doubleValue() * 1.3));
                    }
                }
                temp.setHeureSuplementaireTotal(temp.getHeureSuplementaire8H()+temp.getHeureSuplementaire12H()+temp.getHeureSuplementaireDim()+temp.getHeureSuplementaireFerie()+temp.getHeureSuplementaireMinuit());


                //Salaire brute
                temp.setSalaireBrute(temp.getSalaireBaseMois() - temp.getHeureSuplementaireTotal());

                //Retenue cnaps 1% et sanitaire
                temp.setRetenuCNaPS1(getSmig() * 8 * 0.01);
                temp.setRetenuSanitaire(temp.getSalaireBrute() * 0.01);
                if (temp.getRetenuCNaPS1() > temp.getRetenuSanitaire() ) {
                    temp.setRetenuCNaPS1(temp.getRetenuSanitaire());
                }

                //Retenue cnaps 8% et sanitaire
                temp.setRetenuCNaPS8(temp.getRetenuCNaPS1() * 8);
                temp.setRetenuSanitaire8(temp.getRetenuSanitaire()*5);

                //Montant imposable
                temp.setMontantImposable(temp.getSalaireBrute() - temp.getRetenuCNaPS1() - temp.getRetenuSanitaire());


                //IRSA
                temp.setIrsaInf350(0);
                temp.setIrsaEntre350et400(50000 * 0.05);
                temp.setIrsaEntre400et50(100000 * 0.1);
                temp.setIrsaEntre500et600(100000 * 0.15);
                temp.setIrsaSup600(Math.abs(temp.getMontantImposable() - 600000) * 0.2);
                temp.setTotalIrsa(temp.getIrsaInf350() + temp.getIrsaEntre350et400() + temp.getIrsaEntre400et50() + temp.getIrsaEntre500et600() + temp.getIrsaSup600());


                //Total retenue
                temp.setTotalRetenue(temp.getTotalIrsa() + temp.getRetenuCNaPS1() + temp.getRetenuSanitaire());

                //Net a payer
                temp.setIndemnite(0);
                temp.setNetApayer(temp.getSalaireBrute() - temp.getTotalRetenue() + temp.getIndemnite());
                break;
            }
        }
        String moisTexte = Month.of(mois).getDisplayName(java.time.format.TextStyle.FULL, Locale.FRANCE);
        model.addAttribute("mois", moisTexte);
        model.addAttribute("info", temp);
        return "fiche_paie";
    }

}