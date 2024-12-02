package com.rh.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class PlanningAbsence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_personnel", nullable = false) // Correspond à la clé étrangère dans la base
    private Personnel personnel;

    @Temporal(TemporalType.DATE)
    private Date ledate;

    private int durree;

    // Getters et setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Date getLedate() {
        return ledate;
    }

    public void setLedate(Date ledate) {
        this.ledate = ledate;
    }

    public int getDurree() {
        return durree;
    }

    public void setDurree(int durree) {
        this.durree = durree;
    }
}
