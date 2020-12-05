package com.be.be_app.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipementId;
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "secteur_id")
    private Secteur secteur;

    public Equipement(Long equipementId, @NotNull String name, Secteur secteur) {
        this.equipementId = equipementId;
        this.name = name;
        this.secteur = secteur;
    }

    public Equipement() {
    }

    public Long getEquipementId() {
        return equipementId;
    }

    public String getName() {
        return name;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }
}
