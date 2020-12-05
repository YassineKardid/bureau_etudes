package com.be.be_app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  siteId;
    @NotNull
    private String name;

    public Site(Long siteId, @NotNull String name) {
        this.siteId = siteId;
        this.name = name;
    }

    public Site() {
    }

    public Long getSiteId() {
        return siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

