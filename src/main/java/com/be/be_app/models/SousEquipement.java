package com.be.be_app.models;

import javax.persistence.*;

@Entity
public class SousEquipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sousEquipementId;
    private  String name;
    private String serialNumber;
    @ManyToOne
    @JoinColumn(name = "equipement_id")
    private Equipement equipement;

    public SousEquipement(Long sousEquipementId, String name, String serialNumber, Equipement equipement) {
        this.sousEquipementId = sousEquipementId;
        this.name = name;
        this.serialNumber = serialNumber;
        this.equipement = equipement;
    }

    public SousEquipement() {
    }

    public Long getSousEquipementId() {
        return sousEquipementId;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }
}
