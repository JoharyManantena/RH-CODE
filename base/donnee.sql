
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
('Dupont', 'Jean', '1990-01-15', '10 rue des Lilas, Paris', '2020-05-10', 3000.00, 1, 3, 'Technicien'),
('Durand', 'Marie', '1985-06-20', '20 avenue des Fleurs, Lyon', '2018-03-05', 4000.00, 2, 7, 'Manager'),
('Martin', 'Alice', '1992-11-25', '15 boulevard Haussmann, Paris', '2021-09-01', 3500.00, 3, 3, 'Comptable');



INSERT INTO type_conge (nom_conge)
VALUES 
('Congés Payés'),
('Congés Sans Solde'),
('Congés Spéciaux'),
('Congés Maladie'),
('Congés Parentaux'),
('Congés Sabbatiques'),
('Congés Formation');








