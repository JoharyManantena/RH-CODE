package com.rh.model.BackOffice;

import jakarta.persistence.*;

import java.util.Date;

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
    private Date dateEmission;

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

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
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
