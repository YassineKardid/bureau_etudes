package com.be.be_app.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Plan {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String planId;
    private String number;
    private String title;
    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "sous_equipement_id")
    private SousEquipement sousEquipement;

    @OneToMany(mappedBy = "plan")
    Set<Consultation> usersConsultation;

    public Plan(String planId, String number, String title, String description, SousEquipement sousEquipement, Set<Consultation> usersConsultation) {
        this.planId = planId;
        this.number = number;
        this.title = title;
        this.description = description;
        this.sousEquipement = sousEquipement;
        this.usersConsultation = usersConsultation;
    }

    public Plan() {
    }

    public String getPlanId() {
        return planId;
    }

    public String getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public SousEquipement getSousEquipement() {
        return sousEquipement;
    }

    public Set<Consultation> getUsersConsultation() {
        return usersConsultation;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSousEquipement(SousEquipement sousEquipement) {
        this.sousEquipement = sousEquipement;
    }

    public void setUsersConsultation(Set<Consultation> usersConsultation) {
        this.usersConsultation = usersConsultation;
    }
}
