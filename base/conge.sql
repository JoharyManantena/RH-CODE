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
   cumul_mois INT,
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

ALTER TABLE demande_conge ADD COLUMN duree_conge DECIMAL(15,2);


CREATE TABLE solde_conge (
   id_personnel INT NOT NULL,
   solde_initial DECIMAL(15,2),
   solde_restant DECIMAL(15,2), 
   PRIMARY KEY(id_personnel),
   FOREIGN KEY(id_personnel) REFERENCES personnel(id_personnel)
);

-- ########################################
CREATE TABLE planning_absence (
   id_personnel INT NOT NULL,
   date_debut DATE NOT NULL,
   date_fin DATE NOT NULL,
   PRIMARY KEY(id_personnel, date_debut),
   FOREIGN KEY(id_personnel) REFERENCES personnel(id_personnel)
);



-- DELIMITER //

-- CREATE PROCEDURE CalculerDroitsConges()
-- BEGIN
--     DECLARE v_id_personnel INT;
--     DECLARE v_anciennete INT;  -- Durée de service en mois
--     DECLARE v_droits_par_mois DECIMAL(5,2);  -- Droits mensuels en jours
--     DECLARE v_solde_initial DECIMAL(15,2);  -- Solde initial de congé
--     DECLARE v_temps_partiel DECIMAL(5,2);  -- Facteur temps partiel (0 = temps plein, 1 = 100% du temps)

--     -- Curseur pour parcourir tous les employés
--     DECLARE cur CURSOR FOR 
--         SELECT id_personnel, date_embauche, poste
--         FROM personnel;
    
--     OPEN cur;
--     read_loop: LOOP
--         FETCH cur INTO v_id_personnel, v_anciennete, v_temps_partiel;
--         IF done THEN
--             LEAVE read_loop;
--         END IF;
        
--         -- Calcul de l'ancienneté en mois
--         SET v_anciennete = TIMESTAMPDIFF(MONTH, (SELECT date_embauche FROM personnel WHERE id_personnel = v_id_personnel), CURRENT_DATE);
        
--         -- Calcul des droits (2,5 jours/mois pendant 36 mois max)
--         SET v_droits_par_mois = 2.5;  -- Droits de 2,5 jours par mois
--         SET v_solde_initial = LEAST(v_anciennete * v_droits_par_mois, 90);  -- Limiter à 90 jours (36 mois max)
        
--         -- Ajuster pour les employés à temps partiel
--         IF v_temps_partiel < 1 THEN
--             SET v_solde_initial = v_solde_initial * v_temps_partiel;  -- Réduction en fonction du temps partiel
--         END IF;

--         -- Mettre à jour le solde des congés dans la table solde_conge
--         INSERT INTO solde_conge (id_personnel, solde_initial, solde_restant)
--         VALUES (v_id_personnel, v_solde_initial, v_solde_initial)
--         ON DUPLICATE KEY UPDATE solde_initial = v_solde_initial, solde_restant = v_solde_initial;

--     END LOOP;

--     CLOSE cur;
-- END;
-- //

-- DELIMITER ;






-- DELIMITER //

-- CREATE PROCEDURE ValiderDemandeConge(
--     IN id_demande INT
-- )
-- BEGIN
--     DECLARE v_duree_conge DECIMAL(15,2);
--     DECLARE v_id_personnel INT;
--     DECLARE v_date_debut DATE;
--     DECLARE v_date_fin DATE;
--     DECLARE v_solde_restant DECIMAL(15,2);
    
--     -- Récupérer la durée de congé, l'employé associé, et les dates
--     SELECT duree_conge, id_personnel, date_debut, date_fin
--     INTO v_duree_conge, v_id_personnel, v_date_debut, v_date_fin
--     FROM demande_conge
--     WHERE id_demande = id_demande;
    
--     -- Vérifier si l'employé a un solde de congé
--     SELECT solde_restant INTO v_solde_restant
--     FROM solde_conge
--     WHERE id_personnel = v_id_personnel;
    
--     IF v_solde_restant IS NULL THEN
--         SIGNAL SQLSTATE '45000'
--         SET MESSAGE_TEXT = 'Aucun solde de congé trouvé pour cet employé';
--     ELSE
--         -- Vérifier si le solde est suffisant
--         IF v_solde_restant >= v_duree_conge THEN
--             -- Mettre à jour le solde de congé restant
--             UPDATE solde_conge
--             SET solde_restant = solde_restant - v_duree_conge
--             WHERE id_personnel = v_id_personnel;

--             -- Mettre à jour le statut de la demande de congé en "Validée"
--             UPDATE demande_conge
--             SET statut = 'Validée'
--             WHERE id_demande = id_demande;

--             -- Ajouter l'absence au planning
--             INSERT INTO planning_absence (id_personnel, date_debut, date_fin)
--             VALUES (v_id_personnel, v_date_debut, v_date_fin);
            
--         ELSE
--             -- Mettre à jour le statut de la demande de congé en "Rejetée" en cas de solde insuffisant
--             UPDATE demande_conge
--             SET statut = 'Rejetée'
--             WHERE id_demande = id_demande;
            
--             -- Lancer une erreur si le solde est insuffisant
--             SIGNAL SQLSTATE '45000'
--             SET MESSAGE_TEXT = 'Solde insuffisant pour valider la demande de congé';
--         END IF;
--     END IF;
    
-- END;
-- //

-- DELIMITER ;

