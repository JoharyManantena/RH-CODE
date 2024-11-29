package com.rh.model.BackOffice;

import jakarta.persistence.*;

@Entity
@Table(name = "rupture")
public class Rupture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rupture")
    private Integer idRupture;

    @Column(name = "types", length = 10)
    private String types;

    // Getters et Setters
    public Integer getIdRupture() {
        return idRupture;
    }

    public void setIdRupture(Integer idRupture) {
        this.idRupture = idRupture;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
