package com.be.be_app.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;

    private String matricule;
    private String firstName;
    private String lastName;
    private Boolean isApproved;  //Administraion approbatioon
    private Boolean isEnabled;  //Email approbation
    private String email;
    @JsonIgnore
    private String password;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    @ManyToOne
    @JoinColumn(name = "secteur_id")
    private  Secteur secteur;

    @OneToMany(mappedBy = "user")
    Set<Consultation> plansConsultation;

    public User(Long userId, String matricule, String firstName, String lastName, Boolean isApproved, Boolean isEnabled,
                String email, String password, Profile profile, Site site, Secteur secteur, Set<Consultation> plansConsultation) {
        this.userId = userId;
        this.matricule = matricule;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isApproved = isApproved;
        this.isEnabled = isEnabled;
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.site = site;
        this.secteur = secteur;
        this.plansConsultation = plansConsultation;
    }

    public User() {
        super();
        this.isApproved = false;
        this.isEnabled = false;
    }

    public Long getUserId() {
        return userId;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Profile getProfile() {
        return profile;
    }

    public Site getSite() {
        return site;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public Set<Consultation> getPlansConsultation() {
        return plansConsultation;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public void setPlansConsultation(Set<Consultation> plansConsultation) {
        this.plansConsultation = plansConsultation;
    }
}
