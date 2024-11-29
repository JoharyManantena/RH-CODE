package com.rh.model.BackOffice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluation")
    private Integer idEvaluation;

    @Column(name = "note_experience")
    private Double noteExperience = 0.0;

    @Column(name = "note_competence")
    private Double noteCompetence = 0.0;

    @Column(name = "note_adequation")
    private Double noteAdequation = 0.0;

    @Column(name = "note_entretien")
    private Double noteEntretien = 0.0;

    @Column(name = "note_totale")
    private Double noteTotale = null;

    public Integer getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(Integer idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public Double getNoteExperience() {
        return noteExperience;
    }

    public void setNoteExperience(Double noteExperience) {
        if (noteExperience == null) {
            this.noteExperience = 0.0;
        }else {
            this.noteExperience = noteExperience;

        }
    }

    public Double getNoteCompetence() {
        return noteCompetence;
    }

    public void setNoteCompetence(Double noteCompetence) {
        if (noteCompetence == null) {
            this.noteCompetence = 0.0;
        }else {
            this.noteCompetence = noteCompetence;

        }
    }

    public Double getNoteAdequation() {
        return noteAdequation;
    }

    public void setNoteAdequation(Double noteAdequation) {
        if (noteAdequation == null) {
            this.noteAdequation = 0.0;
        }else {
            this.noteAdequation = noteAdequation;

        }
    }

    public Double getNoteEntretien() {
        return noteEntretien;
    }

    public void setNoteEntretien(Double noteEntretien) {
        if (noteEntretien == null) {
            this.noteEntretien = 0.0;
        }else {
            this.noteEntretien = noteEntretien;
        }
    }

    public Double getNoteTotale() {
        return noteTotale;
    }

    public void calculerNoteTotale() {
        
        this.noteTotale = (this.noteExperience + this.noteEntretien + this.noteAdequation + this.noteAdequation) / 4;
    }

    public Evaluation() {

    }

    public Evaluation( Double noteExperience, Double noteCompetence, Double noteAdequation,
            Double noteEntretien) {
        this.setNoteExperience(noteExperience);
        this.setNoteCompetence(noteCompetence);
        this.setNoteAdequation(noteAdequation);
        this.setNoteEntretien(noteEntretien);
        this.calculerNoteTotale();
    }

}
