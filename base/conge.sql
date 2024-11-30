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
   -- cumul_mois INT,
   poste VARCHAR(50),
   PRIMARY KEY(id_personnel),
   FOREIGN KEY(id_categorie) REFERENCES categorie_personnel(id_categorie)
);

INSERT INTO personnel (nom, prenom, date_naissance, adresse, date_embauche, salaire, id_departement, id_categorie, poste,cumul_mois)
VALUES
('Dupont', 'Jean', '1990-01-15', '10 rue des Lilas, Paris', '2020-05-10', 3000.00, 1, 3, 'Technicien',11),  -- Changement id_categorie à 3
('Durand', 'Marie', '1985-06-20', '20 avenue des Fleurs, Lyon', '2018-03-05', 4000.00, 2, 7, 'Manager',15),    -- Changement id_categorie à 7
('Martin', 'Alice', '1992-11-25', '15 boulevard Haussmann, Paris', '2021-09-01', 3500.00, 3, 3, 'Comptable',05); -- Changement id_categorie à 3



CREATE TABLE type_conge (
    id_type_conge INT AUTO_INCREMENT, 
    nom_conge VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_type_conge)  
);

INSERT INTO type_conge (nom_conge)
VALUES 
('Congés Payés'),
('Congés Sans Solde'),
('Congés Spéciaux'),
('Congés Maladie'),
('Congés Parentaux'),
('Congés Sabbatiques'),
('Congés Formation');

-- 1. Congés Payés
--  Temps de repos obligatoire pendant lequel l’employé continue de percevoir son
-- salaire.
--  Exemple : 4 semaines minimum de congés payés annuels dans de nombreux pays.

-- 2. Congés Sans Solde
--  Absence autorisée sans rémunération.
--  Motifs variés : projets personnels, études, voyages, etc.

-- 3. Congés Spéciaux ou Exceptionnels
--  Accordés pour des événements spécifiques :
-- o Mariage.
-- o Décès d’un proche.
-- o Naissance ou adoption d’un enfant.
--  Ces congés sont souvent réglementés par la loi.

-- 4. Congés Maladie
--  Absence liée à une incapacité médicale temporaire ou prolongée.
--  Nécessite généralement un certificat médical.

-- 5. Congé de Maternité, Paternité et Adoption
--  Protège les droits des parents à s’occuper de leurs enfants lors de naissances ou
-- d’adoptions.
--  Durées et rémunérations varient selon les pays et politiques d’entreprise.

-- 6. Congés Sabbatiques
--  Période prolongée d’absence pour poursuivre des projets personnels (formation,
-- voyage, etc.).
--  Souvent sans rémunération et soumis à certaines conditions (ancienneté, accord de
-- l'employeur).

-- 7. Congés Formation
--  Permettent à l'employé de suivre une formation pour développer ses compétences
-- professionnelles.
--  Généralement encadrés par des lois ou accords collectifs. 



CREATE TABLE demande_conge (
   id_demande INT AUTO_INCREMENT,
   id_personnel INTEGER NOT NULL,
   id_type_conge INTEGER NOT NULL,
   date_demande DATE NOT NULL DEFAULT CURRENT_DATE,
   date_debut DATE NOT NULL,
   date_fin DATE,
   statut VARCHAR(50) DEFAULT 'En attente',
   PRIMARY KEY(id_demande),
   FOREIGN KEY(id_personnel) REFERENCES personnel(id_personnel),
   FOREIGN KEY(id_type_conge) REFERENCES type_conge(id_type_conge)
);

-- Ajouter la colonne 'duree_conge' à la table demande_conge
ALTER TABLE demande_conge ADD COLUMN duree_conge DECIMAL(15,2);