package com.be.be_app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @NotNull
    private  String name;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Role(Long roleId, @NotNull String name, Profile profile) {
        this.roleId = roleId;
        this.name = name;
        this.profile = profile;
    }

    public Role() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public Profile getProfile() {
        return profile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
