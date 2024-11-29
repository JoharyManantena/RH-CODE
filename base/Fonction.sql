DELIMITER $$

CREATE TRIGGER calculate_note_totale
BEFORE INSERT ON evaluation
FOR EACH ROW
BEGIN
    SET NEW.note_totale = (NEW.note_experience + NEW.note_competence + NEW.note_adequation + NEW.note_entretien) / 4;
END$$

CREATE TRIGGER calculate_note_totale_update
BEFORE UPDATE ON evaluation
FOR EACH ROW
BEGIN
    SET NEW.note_totale = (NEW.note_experience + NEW.note_competence + NEW.note_adequation + NEW.note_entretien) / 4;
END$$

DELIMITER ;
