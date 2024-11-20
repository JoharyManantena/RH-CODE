drop database if EXISTS codegestion;

create database codegestion;
use codegestion;

CREATE TABLE diplome(
   id_diplome INT AUTO_INCREMENT,
   libelle VARCHAR(50)  NOT NULL,
   niveau INT NOT NULL,
   PRIMARY KEY(id_diplome)
);

inset into diplome value

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
   idclient INT NOT NULL,
   id_offre_emploi INT NOT NULL,
   id_diplome INT NOT NULL,
   PRIMARY KEY(id_cv),
   FOREIGN KEY(idclient) REFERENCES Client(idclient),
   FOREIGN KEY(id_offre_emploi) REFERENCES offre_emploi(id_offre_emploi),
   FOREIGN KEY(id_diplome) REFERENCES diplome(id_diplome)
);


-- apres embauche 
CREATE TABLE personnel(
   id_personnel INT AUTO_INCREMENT,
   nom VARCHAR(255) ,
   prenom VARCHAR(255) ,
   date_naissance DATE,
   adresse VARCHAR(255) ,
   date_embauche DATE,
   salaire DECIMAL(15,2)  ,
   id_departement INT,
   poste VARCHAR(50) ,
   id_cv INT NOT NULL,
   PRIMARY KEY(id_personnel),
   UNIQUE(id_cv),
   FOREIGN KEY(id_cv) REFERENCES cv(id_cv)
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




CREATE OR REPLACE VIEW voir_filtrage AS
SELECT 
    cv.nom, 
    cv.prenom, 
    resulat_test.points AS resultat_test, 
    cv.note AS note_cv,
    cv.annees_experience, 
    cv.date_postulation, 
    (SELECT libelle FROM diplome WHERE diplome.id_diplome = cv.id_diplome) AS diplome
FROM 
    resulat_test
JOIN 
    test ON test.id_test = resulat_test.id_test
JOIN 
    cv ON cv.id_cv = test.id_cv
ORDER BY 
    resulat_test.points DESC;


select * from voir_filtrage ;