drop database if EXISTS codegestion;

create database codegestion;
use codegestion;

CREATE TABLE diplome(
   id_diplome INT AUTO_INCREMENT,
   libelle VARCHAR(50)  NOT NULL,
   niveau INT NOT NULL,
   PRIMARY KEY(id_diplome)
);

CREATE TABLE Client(
   idclient INT,
   email VARCHAR(50)  NOT NULL,
   password VARCHAR(50) ,
   PRIMARY KEY(idclient)
);

CREATE TABLE besoin_recrutement(
   id_besoin_recrutement INT AUTO_INCREMENT,
   id_departement INT NOT NULL,
   date_demande DATE NOT NULL,
   annees_experience INT,
   id_diplome INT NOT NULL,
   PRIMARY KEY(id_besoin_recrutement),
   FOREIGN KEY(id_diplome) REFERENCES diplome(id_diplome)
);

CREATE TABLE offre_emploi(
   id_offre_emploi INT AUTO_INCREMENT,
   date_publication DATE NOT NULL,
   id_besoin_recrutement INT NOT NULL,
   PRIMARY KEY(id_offre_emploi),
   UNIQUE(id_besoin_recrutement),
   FOREIGN KEY(id_besoin_recrutement) REFERENCES besoin_recrutement(id_besoin_recrutement)
);

CREATE TABLE cv(
   id_cv INT AUTO_INCREMENT,
   date_postulation DATE NOT NULL,
   nom VARCHAR(255) ,
   prenom VARCHAR(255) ,
   date_naissance DATE,
   adresse VARCHAR(255) ,
   annees_experience INT,
   -- idclient INT NOT NULL,
   -- id_offre_emploi INT NOT NULL,
   -- id_diplome INT NOT NULL,
   PRIMARY KEY(id_cv)
   -- FOREIGN KEY(idclient) REFERENCES Client(idclient),
   -- FOREIGN KEY(id_offre_emploi) REFERENCES offre_emploi(id_offre_emploi),
   -- FOREIGN KEY(id_diplome) REFERENCES diplome(id_diplome)
);


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
   -- UNIQUE(id_cv),
   -- FOREIGN KEY(id_cv) REFERENCES cv(id_cv),
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
   date_entretien DATE,
   id_cv INT NOT NULL,
   PRIMARY KEY(id_entretien),
   UNIQUE(id_cv),
   FOREIGN KEY(id_cv) REFERENCES cv(id_cv)
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
    id_type_conge INT AUTO_INCREMENT, 
    nom_conge VARCHAR(50) NOT NULL,  -- Congé payé, maladie, etc.
    PRIMARY KEY (id_type_conge)  
);


CREATE TABLE demande_conge (
   id_demande INT AUTO_INCREMENT,
   id_personnel INTEGER NOT NULL,
   id_type_conge INTEGER NOT NULL,
   date_demande DATE NOT NULL,
   date_debut DATE NOT NULL,
   date_fin DATE,
   statut VARCHAR(50) DEFAULT 'En attente',
   PRIMARY KEY(id_demande),
   FOREIGN KEY(id_personnel) REFERENCES personnel(id_personnel),
   FOREIGN KEY(id_type_conge) REFERENCES type_conge(id_type_conge)
);

ALTER TABLE demande_conge MODIFY COLUMN date_demande DATE NOT NULL DEFAULT CURRENT_DATE;







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
