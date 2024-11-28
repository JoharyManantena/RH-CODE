

INSERT INTO cv (date_postulation, nom, prenom, date_naissance, adresse, annees_experience)
VALUES
('2020-04-01', 'Jean', 'Dupont', '1990-01-15', '10 rue des Lilas, Paris', 5),
('2017-02-25', 'Marie', 'Durand', '1985-06-20', '20 avenue des Fleurs, Lyon', 8),
('2021-08-15', 'Alice', 'Martin', '1992-11-25', '15 boulevard Haussmann, Paris', 3);



-- Insertion de catégories de personnel
INSERT INTO categorie_personnel (nom_categorie, niveau_hierarchique)
VALUES
('Ouvrier', 1),  -- Niveau 1 : Ouvrier
('Technicien', 2),  -- Niveau 2 : Technicien
('Cadre', 3),  -- Niveau 3 : Cadre
('Manager', 4),  -- Niveau 4 : Manager
('Directeur', 5);  -- Niveau 5 : Directeur


INSERT INTO personnel (nom, prenom, date_naissance, adresse, date_embauche, salaire, id_departement, id_categorie, poste)
VALUES
('Dupont', 'Jean', '1990-01-15', '10 rue des Lilas, Paris', '2020-05-10', 3000.00, 1, 3, 'Technicien'),  -- Changement id_categorie à 3
('Durand', 'Marie', '1985-06-20', '20 avenue des Fleurs, Lyon', '2018-03-05', 4000.00, 2, 7, 'Manager'),    -- Changement id_categorie à 7
('Martin', 'Alice', '1992-11-25', '15 boulevard Haussmann, Paris', '2021-09-01', 3500.00, 3, 3, 'Comptable'); -- Changement id_categorie à 3







INSERT INTO type_conge (nom_conge)
VALUES 
('Congés Payés'),
('Congés Sans Solde'),
('Congés Spéciaux'),
('Congés Maladie'),
('Congés Parentaux'),
('Congés Sabbatiques'),
('Congés Formation');




-- -- 1. Afficher toutes les demandes de congé :

-- SELECT 
--     dc.id_demande,
--     p.nom AS employe_nom,
--     p.prenom AS employe_prenom,
--     tc.nom_conge AS type_de_conge,
--     dc.date_demande,
--     dc.date_debut,
--     dc.date_fin,
--     dc.statut
-- FROM 
--     demande_conge dc
-- JOIN 
--     personnel p ON dc.id_personnel = p.id_personnel
-- JOIN 
--     type_conge tc ON dc.id_type_conge = tc.id_type_conge;



-- -- 2. Afficher les congés d’un employé spécifique :

-- SELECT 
--     tc.nom_conge AS type_de_conge,
--     dc.date_demande,
--     dc.date_debut,
--     dc.date_fin,
--     dc.statut
-- FROM 
--     demande_conge dc
-- JOIN 
--     type_conge tc ON dc.id_type_conge = tc.id_type_conge
-- WHERE 
--     dc.id_personnel = [ID_EMPLOYE];



-- -- Cote admin 
-- -- 4. Afficher les congés en attente d’approbation :

-- SELECT 
--     p.nom AS employe_nom,
--     p.prenom AS employe_prenom,
--     tc.nom_conge AS type_de_conge,
--     dc.date_demande,
--     dc.date_debut,
--     dc.date_fin
-- FROM 
--     demande_conge dc
-- JOIN 
--     personnel p ON dc.id_personnel = p.id_personnel
-- JOIN 
--     type_conge tc ON dc.id_type_conge = tc.id_type_conge
-- WHERE 
--     dc.statut = 'En attente';