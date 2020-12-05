package com.be.be_app.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long profileId;
    @NotNull
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    private Set<Role> roles;
    public Profile(Long profileId, @NotNull String name, Set<Role> roles) {
        this.profileId = profileId;
        this.name = name;
        this.roles = roles;
    }

    public Profile() {
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
