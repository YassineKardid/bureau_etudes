package com.be.be_app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Secteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long secteurId ;
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "site_id")
    @JsonIgnore
    private Site site;

    public Secteur(Long secteurId, @NotNull String name, Site site) {
        secteurId = secteurId;
        this.name = name;
        this.site = site;
    }

    public Secteur() {
    }

    public Long getSecteurId() {
        return secteurId;
    }

    public String getName() {
        return name;
    }

    public Site getSite() {
        return site;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
