


INSERT INTO admin (username, password)
    values('Admin', 'test');


INSERT INTO diplome (libelle, niveau) VALUES 
('Licence', 1),
('Master', 2),
('Doctorat', 3);

INSERT INTO departement (nom_departement) VALUES
('Ressources Humaines'),
('Développement Informatique'),
('Recherche et Développement');


INSERT INTO besoin_recrutement (id_departement, date_demande, annees_experience, id_diplome) VALUES
(1, '2024-11-01', 2, 1), -- Licence
(2, '2024-11-05', 3, 2), -- Master
(3, '2024-11-10', 5, 3); -- Doctorat


INSERT INTO Candidat (id_candidat, email, password) VALUES
(1, 'candidate1@example.com', 'password1'),
(2, 'candidate2@example.com', 'password2'),
(3, 'candidate3@example.com', 'password3'),
(4, 'candidate4@example.com', 'password4'),
(5, 'candidate5@example.com', 'password5'),
(6, 'candidate6@example.com', 'password6'),
(7, 'candidate7@example.com', 'password7'),
(8, 'candidate8@example.com', 'password8'),
(9, 'candidate9@example.com', 'password9'),
(10, 'candidate10@example.com', 'password10');


INSERT INTO offre_emploi (titre_poste, description_poste, responsabilite_principale, fourchette_salaire, date_publication, id_besoin_recrutement, date_limite_candidature) VALUES
('Développeur Web', 'Développement et maintenance des sites web.', 'Développement front-end et back-end', '30k-40k', '2024-11-15', 1, '2024-12-15'),
('Data Scientist', 'Analyse des données et création de modèles.', 'Travailler avec des big data', '50k-60k', '2024-11-20', 2, '2024-12-20'),
('Chercheur en IA', 'Recherche sur les technologies d intelligence artificielle.', 'Concevoir des algorithmes innovants', '70k-80k', '2024-11-25', 3, '2024-12-25');


INSERT INTO cv (nom, prenom, date_naissance, adresse, annees_experience, id_candidat, id_diplome) VALUES
('Dupont', 'Jean', '1990-01-01', 'Paris, France', 2, 1, 1), -- Licence
('Martin', 'Claire', '1992-02-02', 'Lyon, France', 3, 2, 2), -- Master
('Durand', 'Paul', '1995-03-03', 'Marseille, France', 5, 3, 3), -- Doctorat
('Bernard', 'Alice', '1988-04-04', 'Toulouse, France', 2, 4, 1), -- Licence
('Thomas', 'Luc', '1990-05-05', 'Nice, France', 3, 5, 2), -- Master
('Petit', 'Julie', '1993-06-06', 'Bordeaux, France', 5, 6, 3), -- Doctorat
('Robert', 'Hugo', '1996-07-07', 'Strasbourg, France', 2, 7, 1), -- Licence
('Richard', 'Emma', '1998-08-08', 'Lille, France', 3, 8, 2), -- Master
('Dumas', 'Noah', '1997-09-09', 'Rennes, France', 5, 9, 3), -- Doctorat
('Moreau', 'Sophie', '1995-10-10', 'Nantes, France', 2, 10, 1); -- Licence


INSERT INTO statut_candidature (id_statut, libelle) VALUES
(1, 'En attente'),
(2, 'En cours'),
(3, 'Acceptée'),
(4, 'Rejetée');


INSERT INTO candidature (date_postulation, id_cv, id_candidat, id_offre_emploi, id_statut_candidature) VALUES
('2024-11-16', 1, 1, 1, 2),
('2024-11-17', 2, 2, 1, 2),
('2024-11-18', 3, 3, 2, 2),
('2024-11-19', 4, 4, 2, 2),
('2024-11-20', 5, 5, 3, 2),
('2024-11-21', 6, 6, 3, 2),
('2024-11-22', 7, 7, 1, 2),
('2024-11-23', 8, 8, 1, 2),
('2024-11-24', 9, 9, 2, 2),
('2024-11-25', 10, 10, 3, 2);




-- INSERT INTO cv (date_postulation, nom, prenom, date_naissance, adresse, annees_experience)
-- VALUES
-- ('2020-04-01', 'Jean', 'Dupont', '1990-01-15', '10 rue des Lilas, Paris', 5),
-- ('2017-02-25', 'Marie', 'Durand', '1985-06-20', '20 avenue des Fleurs, Lyon', 8),
-- ('2021-08-15', 'Alice', 'Martin', '1992-11-25', '15 boulevard Haussmann, Paris', 3);



-- Insertion de catégories de personnel
INSERT INTO categorie_personnel (nom_categorie, niveau_hierarchique)
VALUES
('Ouvrier', 1),     
('Technicien', 2),  
('Cadre', 3),       
('Manager', 4),     
('Directeur', 5);   


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



-- SELECT
--     p.id_personnel,
--     p.nom,
--     p.prenom,
--     (TIMESTAMPDIFF(YEAR, p.date_embauche, CURDATE()) * 12) * 2.5 AS droits_conge,  -- 2,5 jours par mois
--     IFNULL(SUM(CASE WHEN d.statut = 'Approuvé' THEN d.duree_conge ELSE 0 END), 0) AS conges_approuves,  -- Congés approuvés
--     IFNULL(SUM(CASE WHEN d.statut = 'Validée' THEN d.duree_conge ELSE 0 END), 0) AS conges_valides,  -- Congés validés
--     ((TIMESTAMPDIFF(YEAR, p.date_embauche, CURDATE()) * 12) * 2.5) - IFNULL(SUM(d.duree_conge), 0) AS solde_conge  -- Solde de congé restant
-- FROM
--     personnel p
-- LEFT JOIN
--     demande_conge d ON p.id_personnel = d.id_personnel
-- GROUP BY
--     p.id_personnel;



