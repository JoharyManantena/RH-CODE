drop database if EXISTS codegestion;

create database codegestion;
use codegestion;

CREATE TABLE admin (
   id_admin INT AUTO_INCREMENT PRIMARY KEY,
   username VARCHAR(255) UNIQUE,
   password VARCHAR(255)
);

CREATE TABLE diplome(
   id_diplome INT AUTO_INCREMENT,
   libelle VARCHAR(50)  NOT NULL,
   niveau INT NOT NULL,
   PRIMARY KEY(id_diplome)
);

CREATE TABLE departement (
    id_departement INT AUTO_INCREMENT,
    nom_departement VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_departement)
);

CREATE TABLE Candidat(
   id_candidat INT,
   email VARCHAR(50)  NOT NULL,
   password VARCHAR(50) ,
   PRIMARY KEY(id_candidat)
);

CREATE TABLE besoin_recrutement(
   id_besoin_recrutement INT AUTO_INCREMENT,
   id_departement INT NOT NULL,
   date_demande DATE NOT NULL,
   annees_experience INT,
   id_diplome INT NOT NULL, -- NIVEAU
   nombre_besoin INT NOT NULL,
   est_fait BOOLEAN DEFAULT FALSE,
   PRIMARY KEY(id_besoin_recrutement),
   FOREIGN KEY(id_diplome) REFERENCES diplome(id_diplome)
);

CREATE TABLE offre_emploi(
   id_offre_emploi INT AUTO_INCREMENT,
   titre_poste VARCHAR(255),
   description_poste TEXT,
   responsabilite_principale VARCHAR(255),
   fourchette_salaire VARCHAR(255),
   date_publication DATE NOT NULL,
   id_besoin_recrutement INT NOT NULL,
   date_limite_candidature DATE NOT NULL,
   disponible BOOLEAN DEFAULT TRUE,
   PRIMARY KEY(id_offre_emploi),
   UNIQUE(id_besoin_recrutement),
   FOREIGN KEY(id_besoin_recrutement) REFERENCES besoin_recrutement(id_besoin_recrutement)
);
alter table offre_emploi add column id_type_contrat INT REFERENCES type_contrat(id_type);

CREATE TABLE cv(
   id_cv INT AUTO_INCREMENT,
   nom VARCHAR(255) ,
   prenom VARCHAR(255) ,
   date_naissance DATE,
   adresse VARCHAR(255) ,
   annees_experience INT,
   id_candidat INT NOT NULL,
   id_diplome INT NOT NULL,
   libelle_diplome VARCHAR(255),
   competence TEXT,
   PRIMARY KEY(id_cv),
   FOREIGN KEY(id_candidat) REFERENCES Candidat(id_candidat),
   FOREIGN KEY(id_diplome) REFERENCES diplome(id_diplome)
);
ALTER TABLE cv 
ADD COLUMN objectifs_professionnels TEXT, -- Objectifs et ambitions professionnelles
ADD COLUMN competences_cles TEXT, -- Compétences clés directement liées au poste
ADD COLUMN projets_realises TEXT, -- Description des projets réalisés
ADD COLUMN hobbies_interessants VARCHAR(255), -- Hobbies pertinents pour le poste (exemple : "Développement d'applications")
ADD COLUMN references_professionnelles TEXT, -- Références professionnelles (noms et coordonnées)
ADD COLUMN disponibilite ENUM('Immédiate', '1 mois', '3 mois', 'Non disponible') DEFAULT 'Immédiate', -- Disponibilité pour le poste
ADD COLUMN formations_complementaires TEXT, -- Formations ou cours supplémentaires suivis
ADD COLUMN distinction_reconnaissances TEXT, -- Récompenses ou distinctions reçues
ADD COLUMN realisations TEXT, -- Réalisations majeures au cours de la carrière
ADD COLUMN permis_conduire ENUM('Oui', 'Non') DEFAULT 'Non'; -- Indique si le candidat possède un permis de conduire


CREATE TABLE statut_candidature(
    id_statut INT NOT NULL,
    libelle VARCHAR(255),
    PRIMARY KEY(id_statut)
);

CREATE TABLE evaluation(
    id_evaluation INT AUTO_INCREMENT,
    note_experience NUMERIC(4,2) DEFAULT 0,
    note_competence NUMERIC(4,2) DEFAULT 0,
    note_adequation NUMERIC(4,2) DEFAULT 0,
    note_entretien NUMERIC(4,2) DEFAULT 0,
    note_totale NUMERIC(4, 2) DEFAULT 0,
    PRIMARY KEY(id_evaluation)
);

CREATE TABLE candidature(
    id_candidature INT AUTO_INCREMENT,
    date_postulation DATE NOT NULL,
    id_cv INT NOT NULL,
    id_candidat INT NOT NULL,
    id_offre_emploi INT NOT NULL,
    id_statut_candidature INT NOT NULL,
    id_evaluation INT,
    PRIMARY KEY(id_candidature),
    FOREIGN KEY(id_cv) REFERENCES cv(id_cv),
    FOREIGN KEY(id_candidat) REFERENCES Candidat(id_candidat),
    FOREIGN KEY(id_offre_emploi) REFERENCES offre_emploi(id_offre_emploi),
    FOREIGN KEY(id_statut_candidature) REFERENCES statut_candidature(id_statut),
    FOREIGN KEY(id_evaluation) REFERENCES evaluation(id_evaluation)
);


CREATE TABLE planification_entretien(
   id_planification INT AUTO_INCREMENT,
   id_candidature INT REFERENCES candidature(id_candidature),
   date_entretien DATETIME,
   PRIMARY KEY(id_planification)

);

CREATE TABLE notification (
    id_notification INT AUTO_INCREMENT PRIMARY KEY,  -- Identifiant unique de notification
    message TEXT NOT NULL,  -- Contenu du message de la notification
    type_notification VARCHAR(50) NOT NULL,  -- Type de notification (par exemple : 'Acceptation', 'Rejet')
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Date de création de la notification
    id_candidat INT,  -- L'ID de l'utilisateur (candidat) qui reçoit la notification
    statut ENUM('Non_lue', 'Lue') DEFAULT 'Non_lue',  -- Statut de la notification
    FOREIGN KEY (id_candidat) REFERENCES candidat(id_candidat)  -- Référence à la table des utilisateurs
);




-- **********************************************************************************************************

DROP TABLE IF EXISTS categorie_personnel;
-- part 3
CREATE TABLE categorie_personnel (
   id_categorie INT AUTO_INCREMENT,
   nom_categorie VARCHAR(50) NOT NULL, -- ouvrier , cadre , ...
   niveau_hierarchique INT NOT NULL,
   PRIMARY KEY(id_categorie)
);


-- apres embauche 
CREATE TABLE personnel (
   id_personnel INT AUTO_INCREMENT,
   nom VARCHAR(255),
   prenom VARCHAR(255),
   date_naissance DATE,
   adresse VARCHAR(255),
   date_embauche DATE,
   salaire DECIMAL(15,2),
   id_departement INT,
   id_categorie INT,
   poste VARCHAR(50),
   id_cv INT NOT NULL,
   PRIMARY KEY(id_personnel),
   UNIQUE(id_cv),
   FOREIGN KEY(id_cv) REFERENCES cv(id_cv),
   FOREIGN KEY(id_categorie) REFERENCES categorie_personnel(id_categorie)
);





CREATE TABLE test(
   id_test INT AUTO_INCREMENT,
   date_test DATE,
   id_cv INT,
   PRIMARY KEY(id_test),
   UNIQUE(id_cv),
   FOREIGN KEY(id_cv) REFERENCES cv(id_cv)
);

CREATE TABLE resulat_test(
   id_resultat_test INT AUTO_INCREMENT,
   points DECIMAL(5,2)  ,
   id_test INT NOT NULL,
   PRIMARY KEY(id_resultat_test),
   UNIQUE(id_test),
   FOREIGN KEY(id_test) REFERENCES test(id_test)
);

CREATE TABLE entretien(
   id_entretien INT AUTO_INCREMENT,
   date_entretien DATETIME,
   id_candidature INT NOT NULL,
   PRIMARY KEY(id_entretien),
   FOREIGN KEY(id_candidature) REFERENCES candidature(id_candidature)
);


CREATE TABLE type_contrat (
   id_type INT AUTO_INCREMENT,
   nom VARCHAR(100) NOT NULL,
   abreviation VARCHAR(50),
   PRIMARY KEY(id_type)
);

CREATE TABLE contrat_employe (
   id_contrat_employe INT AUTO_INCREMENT,
   date_debut DATE,
   date_fin DATE,
   id_personnel INT NOT NULL,
   id_type INT NOT NULL,
   PRIMARY KEY(id_contrat_employe),
   FOREIGN KEY (id_personnel) REFERENCES personnel(id_personnel),
   FOREIGN KEY (id_type) REFERENCES type_contrat(id_type)
);




----    Partie 2  --------------------------------------------
--------------------------------------------------------------


-- algorithme de triage lies a cv (filtrer les CV)
CREATE TABLE ats (
   id_ats INT AUTO_INCREMENT,
   id_cv INTEGER NOT NULL,
   statut VARCHAR(50) NOT NULL,
   date_statut DATE NOT NULL,
   notifications_sent BOOLEAN DEFAULT FALSE,
   PRIMARY KEY(id_ats),
   FOREIGN KEY(id_cv) REFERENCES cv(id_cv)
);
ALTER TABLE ats ADD COLUMN score INT;


CREATE TABLE evaluation_interactive (
   id_evaluation INT AUTO_INCREMENT,
   type_evaluation VARCHAR(50) NOT NULL,
   score NUMERIC(5,2),
   id_test INTEGER NOT NULL,
   PRIMARY KEY(id_evaluation),
   FOREIGN KEY(id_test) REFERENCES test(id_test)
);


-- b. Intelligence Artificielle et Machine Learning
--  Prédiction de la Réussite : Algorithmes analysant les données des candidats pour
-- prédire leur adéquation avec la culture d'entreprise et leur potentiel de performance.
--  Chatbots de Recrutement : Utilisation de chatbots pour interagir avec les candidats,
-- répondre à leurs questions et les guider tout au long du processus de recrutement. 



----    Partie 3  --------------------------------------------
--------------------------------------------------------------


--  lies a contrat employer 
CREATE TABLE rupture_contrat (
    id_rupture INT AUTO_INCREMENT,
    id_contrat_employe INT NOT NULL,
    type_rupture VARCHAR(50) NOT NULL, -- Démission, licenciement, etc.
    date_notification DATE NOT NULL,
    indemnites DECIMAL(15,2),
    PRIMARY KEY(id_rupture),
    FOREIGN KEY (id_contrat_employe) REFERENCES contrat_employe(id_contrat_employe)
);

CREATE TABLE document_fin (
    id_document INT AUTO_INCREMENT,
    id_rupture INT NOT NULL,
    type_document VARCHAR(50) NOT NULL, -- Certificat de travail, etc.
    date_emission DATE NOT NULL,
    PRIMARY KEY(id_document),
    FOREIGN KEY (id_rupture) REFERENCES rupture_contrat(id_rupture)
);








CREATE TABLE type_conge (
    id_type_conge INT AUTO_INCREMENT, -- Specify the data type before AUTO_INCREMENT
    nom_conge VARCHAR(50) NOT NULL,  -- Congé payé, maladie, etc.
    PRIMARY KEY (id_type_conge)      -- Specify the column for the primary key
);


CREATE TABLE demande_conge (
   id_demande INT AUTO_INCREMENT,
   id_personnel INTEGER NOT NULL,
   id_type_conge INTEGER NOT NULL,
   date_demande DATE NOT NULL,
   date_debut DATE NOT NULL,
   date_fin DATE,
   statut VARCHAR(50) DEFAULT 'En attente', -- Approuvé, rejeté, etc.
   PRIMARY KEY(id_demande),
   FOREIGN KEY(id_personnel) REFERENCES personnel(id_personnel),
   FOREIGN KEY(id_type_conge) REFERENCES type_conge(id_type_conge)
);



-- heure supplementaire    

CREATE TABLE heures_sup (
   id_heure_sup INT AUTO_INCREMENT PRIMARY KEY,
   id_personnel INTEGER NOT NULL,
   date_heure_sup DATE NOT NULL,
   nombre_heures NUMERIC(5, 2) NOT NULL,
   taux_horaire NUMERIC(10, 2) NOT NULL,
   montant_total NUMERIC(15, 2) NOT NULL,
   FOREIGN KEY (id_personnel) REFERENCES personnel(id_personnel)
);


-- etat de paie

-- raha ohatra ka hoe hanao avance , ...
-- ito azo lazaina par defaut car efa misy impot , ... 
-- Ankoatra ny avance () variable

CREATE TABLE etat_paie (
   id_paie INT AUTO_INCREMENT PRIMARY KEY,
   id_personnel INTEGER NOT NULL,
   mois_annee DATE NOT NULL,
   salaire_base NUMERIC(15, 2) NOT NULL,
   indemnites NUMERIC(15, 2),
   retenues NUMERIC(15, 2),
   salaire_net NUMERIC(15, 2) NOT NULL,
   FOREIGN KEY (id_personnel) REFERENCES personnel(id_personnel)
);



CREATE TABLE fiche_paie (
   id_fiche_paie INT AUTO_INCREMENT PRIMARY KEY,
   id_personnel INTEGER NOT NULL,
   mois_annee DATE NOT NULL,
   anciennete_jours INTEGER,
   net_a_payer NUMERIC(15, 2) NOT NULL,
   FOREIGN KEY (id_personnel) REFERENCES personnel(id_personnel)
);