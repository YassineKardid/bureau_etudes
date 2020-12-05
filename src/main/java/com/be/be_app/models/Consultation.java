package com.be.be_app.models;


import com.be.be_app.Embeddables.ConsultationId;

import javax.persistence.*;

@Entity
public class Consultation {
    @EmbeddedId
    ConsultationId consultationId;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("plan_id")
    @JoinColumn(name = "plan_id")
    Plan plan;

    @ManyToOne
    @MapsId("action_id")
    @JoinColumn(name = "action_id")
    Action action;

    public Consultation(ConsultationId consultationId, User user, Plan plan, Action action) {
        this.consultationId = consultationId;
        this.user = user;
        this.plan = plan;
        this.action = action;
    }

    public Consultation() {
    }

    public ConsultationId getConsultationId() {
        return consultationId;
    }

    public User getUser() {
        return user;
    }

    public Plan getPlan() {
        return plan;
    }

    public Action getAction() {
        return action;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
