package com.be.be_app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Action {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long actionId;
    @NotNull
    private String name;

    public Action() {
    }

    public Action(Long actionId, @NotNull String name) {
        this.actionId = actionId;
        this.name = name;
    }

    public Long getActionId() {
        return actionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
