package com.rh.model.BackOffice;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "document_fin")
public class DocumentFin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document")
    private Integer idDocument;

    @Column(name = "type_document", length = 50, nullable = false)
    private String typeDocument;

    @Column(name = "date_emission", nullable = false)
    private LocalDate dateDemission;

    @Lob
    @Column(name = "fichier", columnDefinition = "LONGBLOB")
    private byte[] fichier;

    @ManyToOne
    @JoinColumn(name = "id_rupture", nullable = false)
    private RuptureContrat ruptureContrat;

    // Getters et Setters
    public Integer getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Integer idDocument) {
        this.idDocument = idDocument;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public LocalDate getDateEmission() {
        return dateDemission;
    }

    public void setDateEmission(LocalDate dateDemission) {
        this.dateDemission = dateDemission;
    }

    public byte[] getFichier() {
        return fichier;
    }

    public void setFichier(byte[] fichier) {
        this.fichier = fichier;
    }

    public RuptureContrat getRuptureContrat() {
        return ruptureContrat;
    }

    public void setRuptureContrat(RuptureContrat ruptureContrat) {
        this.ruptureContrat = ruptureContrat;
    }
}
