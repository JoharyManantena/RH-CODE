

DELIMITER //

CREATE TRIGGER after_insert_personnel
AFTER INSERT ON personnel
FOR EACH ROW
BEGIN
    DECLARE v_anciennete INT;
    DECLARE v_droits_par_mois DECIMAL(5,2);
    DECLARE v_solde_initial DECIMAL(15,2);

    -- Calcul de l'ancienneté en mois
    SET v_anciennete = TIMESTAMPDIFF(MONTH, NEW.date_embauche, CURRENT_DATE);

    -- Calcul des droits (2,5 jours/mois pendant 36 mois max)
    SET v_droits_par_mois = 2.5;
    SET v_solde_initial = LEAST(v_anciennete * v_droits_par_mois, 90);

    -- Mettre à jour le solde des congés dans la table solde_conge
    INSERT INTO solde_conge (id_personnel, solde_initial, solde_restant)
    VALUES (NEW.id_personnel, v_solde_initial, v_solde_initial)
    ON DUPLICATE KEY UPDATE solde_initial = v_solde_initial, solde_restant = v_solde_initial;
END;

DELIMITER ;




DELIMITER //

CREATE TRIGGER after_update_personnel
AFTER UPDATE ON personnel
FOR EACH ROW
BEGIN
    DECLARE v_anciennete INT;
    DECLARE v_droits_par_mois DECIMAL(5,2);
    DECLARE v_solde_initial DECIMAL(15,2);

    -- Calcul de l'ancienneté en mois
    SET v_anciennete = TIMESTAMPDIFF(MONTH, NEW.date_embauche, CURRENT_DATE);

    -- Calcul des droits (2,5 jours/mois pendant 36 mois max)
    SET v_droits_par_mois = 2.5;
    SET v_solde_initial = LEAST(v_anciennete * v_droits_par_mois, 90);

    -- Mettre à jour le solde des congés dans la table solde_conge
    INSERT INTO solde_conge (id_personnel, solde_initial, solde_restant)
    VALUES (NEW.id_personnel, v_solde_initial, v_solde_initial)
    ON DUPLICATE KEY UPDATE solde_initial = v_solde_initial, solde_restant = v_solde_initial;
END//

DELIMITER ;






-- ##############################################################################

DELIMITER //

CREATE PROCEDURE `validate_demande_conge`(IN v_id_demande INT)
BEGIN
    DECLARE v_duree_conge DECIMAL(15,2);
    DECLARE v_id_personnel INT;
    DECLARE v_date_debut DATE;
    DECLARE v_date_fin DATE;
    DECLARE v_solde_restant DECIMAL(15,2);

    -- Récupérer les détails de la demande de congé
    SELECT duree_conge, id_personnel, date_debut, date_fin
    INTO v_duree_conge, v_id_personnel, v_date_debut, v_date_fin
    FROM demande_conge
    WHERE id_demande = v_id_demande;

    -- Vérifier le solde restant de congé
    SELECT solde_restant INTO v_solde_restant
    FROM solde_conge
    WHERE id_personnel = v_id_personnel;

    -- Si le solde est suffisant
    IF v_solde_restant >= v_duree_conge THEN
        -- Retourner un signal indiquant qu'on peut valider
        UPDATE solde_conge
        SET solde_restant = solde_restant - v_duree_conge
        WHERE id_personnel = v_id_personnel;

        -- Mettre à jour la demande de congé en statut "Validée"
        UPDATE demande_conge
        SET statut = 'Validée'
        WHERE id_demande = v_id_demande;

        -- Ajouter l'absence au planning
        INSERT INTO planning_absence (id_personnel, date_debut, date_fin)
        VALUES (v_id_personnel, v_date_debut, v_date_fin);

    ELSE
        -- Si le solde est insuffisant, signaler un rejet
        UPDATE demande_conge
        SET statut = 'Rejetée'
        WHERE id_demande = v_id_demande;
    END IF;
END //

DELIMITER ;







-- ##############################################################################;




DELIMITER //

CREATE EVENT process_demande_conge
ON SCHEDULE EVERY 20 SECOND
DO
BEGIN
    DECLARE v_duree_conge DECIMAL(15,2);
    DECLARE v_id_personnel INT;
    DECLARE v_date_debut DATE;
    DECLARE v_date_fin DATE;
    DECLARE v_solde_restant DECIMAL(15,2);
    DECLARE v_id_demande INT;
    DECLARE done INT DEFAULT 0;

    -- Déclaration du curseur
    DECLARE cur CURSOR FOR
        SELECT id_demande
        FROM demande_conge
        WHERE statut = 'En attente';

    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO v_id_demande;

        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Appeler la procédure pour valider la demande
        CALL validate_demande_conge(v_id_demande);

    END LOOP;

    CLOSE cur;
END //

DELIMITER ;









