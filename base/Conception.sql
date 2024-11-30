CREATE TABLE admin (
   id_admin INT AUTO_INCREMENT PRIMARY KEY,
   username VARCHAR(255) UNIQUE,
   password VARCHAR(255)
);

CREATE TABLE Candidat(
   id_candidat INT AUTO_INCREMENT PRIMARY KEY,
   email VARCHAR(50) NOT NULL,
   password VARCHAR(50)
);

CREATE TABLE categorie_personnel (
   id_categorie INT AUTO_INCREMENT,
   nom_categorie VARCHAR(50) NOT NULL, -- ouvrier , cadre , ...
   niveau_hierarchique INT NOT NULL,
   PRIMARY KEY(id_categorie)
);

CREATE TABLE diplome(
   id_diplome INT AUTO_INCREMENT,
   libelle VARCHAR(50) NOT NULL,
   niveau INT NOT NULL,
   PRIMARY KEY(id_diplome)
);

CREATE TABLE departement (
    id_departement INT AUTO_INCREMENT,
    nom_departement VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_departement)
);

CREATE TABLE cv(
   id_cv INT AUTO_INCREMENT,
   nom VARCHAR(255),
   prenom VARCHAR(255),
   date_naissance DATE,
   adresse VARCHAR(255),
   annees_experience INT,
   id_candidat INT NOT NULL,
   id_diplome INT NOT NULL,
   competence TEXT,
   PRIMARY KEY(id_cv),
   FOREIGN KEY(id_candidat) REFERENCES Candidat(id_candidat),
   FOREIGN KEY(id_diplome) REFERENCES diplome(id_diplome)
);

CREATE TABLE type_contrat (
   id_type INT AUTO_INCREMENT,
   nom VARCHAR(100) NOT NULL,
   abreviation VARCHAR(50),
   PRIMARY KEY(id_type)
);

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
   FOREIGN KEY(id_departement) REFERENCES departement(id_departement),
   FOREIGN KEY(id_cv) REFERENCES cv(id_cv),
   FOREIGN KEY(id_categorie) REFERENCES categorie_personnel(id_categorie)
);

CREATE TABLE contrat_employe (
   id_contrat_employe INT AUTO_INCREMENT,
   date_debut DATE,
   date_fin DATE,
   id_personnel INT NOT NULL,
   id_type INT NOT NULL,
   etat INT NOT NULL,
   PRIMARY KEY(id_contrat_employe),
   FOREIGN KEY(id_personnel) REFERENCES personnel(id_personnel),
   FOREIGN KEY(id_type) REFERENCES type_contrat(id_type)
);

CREATE TABLE rupture (
   id_rupture INT AUTO_INCREMENT,
   type_rupture VARCHAR(50), -- Renamed column
   PRIMARY KEY(id_rupture)
);

CREATE TABLE rupture_contrat (
   id_rupture_contrat INT AUTO_INCREMENT,
   id_contrat_employe INT NOT NULL,
   id_rupture INT NOT NULL, -- Démission, licenciement, etc.
   date_notification DATE NOT NULL,
   date_homologation DATE,
   date_entretient DATE,
   fichier LONGBLOB, -- Lettre de démission, lettre de licenciement, etc.
   indemnites DECIMAL(15,2),
   id_personnel INT NOT NULL,
   etat INT, -- 0 RH vers admin, 1 admin vers personnel
   PRIMARY KEY(id_rupture_contrat),
   FOREIGN KEY(id_personnel) REFERENCES personnel(id_personnel),
   FOREIGN KEY(id_contrat_employe) REFERENCES contrat_employe(id_contrat_employe),
   FOREIGN KEY(id_rupture) REFERENCES rupture(id_rupture)
);

CREATE TABLE document_fin (
   id_document INT AUTO_INCREMENT,
   id_rupture INT NOT NULL,
   type_document VARCHAR(50) NOT NULL, -- Certificat de travail, etc.
   date_emission DATE NOT NULL,
   fichier LONGBLOB, -- Fichier attaché au document
   PRIMARY KEY(id_document),
   FOREIGN KEY(id_rupture) REFERENCES rupture_contrat(id_rupture_contrat)
);
