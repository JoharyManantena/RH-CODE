Cotée Rupture de Contrat:

1-type de rupture:
-Employé:
demision
Cas particulier(prise d'acte de rupture)
-Employeur:
licenciement
retraite
Retraite
-Accord Commun:
rupture convetionelle

2-Demarche d'un Rupture de Contrat:
-Formalisation(notification): RH vers chef
lettre , type de rupture, motif ou cause, le cs échéant.
-Preavis: chef vers employé
date fin contrat , exception s' il sagit d'un faute grave ou demision immédiate.
+ date d'Entretient ou Homologation.

- Documents fin de contrat:
Certificat de travail(fichier pdf).
Solde de tout Compte (Fiche de paye).
Attestaion pour Assurance Chômage(fichier pdf).

A faire:
-Conception BD:
1-Formalisation:
Rupture_contrat:
-fichier (lettre)
-date notification
-etat(0 RH  vers admin,1 admin vers personel)
-date Fin(preavis)
-type_rupture
-date Entretient(update Admin)
-date Homolagtion(update Admin)
-idemnites(update Admin)
-idPersonel
-id_contrat_employe

2-Document:
-id_rupture
-type_document(certificat,fiche de paye)
-date_emission

model:
*-personnel
*-rupture_contrat
*-envoye_document
*-contrat_employe
*-cv
*-categorie
*-candidat
*-diplome
*-departement
*-type_contrat
*-Admin

repository:
*-rupture_contrat:finByIdPersonnel
*-documentFin: *findByidRupture
*-contrat_employe: findByIdPersonnel,

service:
*-rupture_contrat: RupturePersonnel, *getByIdRuptureContratEtat(notification), Update(etat,datehomologation,date entretient,Indemnitée), save
*-documentFin: *EnvoyerDocument, *voirDocumentbyIdRupture

Controller:
-RuptureController(côté RH): InsertionRupture. 
-ValidationRuptueController(côté Admin): NotificationRuptureRH, ModificationRupture, VoirFicheDePaye, VoirCongée, VoirContrat.
-VoirRupture(côté Client): NotificationRuptureAdmin

front:
-Rh: formulaire Rupture Contrat
-Admin: notificationRuptureRh(etat = 0), formulaire Update Rupture Contrat(avec bouttons,VoirFicheDePaye, VoirCongée, VoirContrat ).
-Client: notificationRuptureAdmin(etat = 1).

*Contrat :
+ mois preavis,

*Personnel:
+ date d'embauche, 

Ancienté:
date d'embauche - date du jour,

Indemnité:
type rupture:
demission: -general(corespond avec le preavis): indemnté de congée non payé = indemnité.
demision: -immediat(ne correspond pas avec le preavis): indemnité = indemnité compensatoire: preavis * salaire brute.

licenciement: -general(corespond avec le preavis): indemnité = indemnité licenciement: Ancienté < 1an: 1/4 salaire brute, Ancienté> 1an: 1/4 salaire brute + 1/3 salaireBrute * ancienté - 1an.
-Immediat(ne correspond pas avec le preavis): indemnité = indemnité compensatoire: indemnité licencement + indemnité congés payé.