package com.be.be_app.Embeddables;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class ConsultationId implements Serializable {
    @Column(name = "user_id")
    Long userId;
    @Column(name = "plan_id")
    String planId;
    @Column(name = "action_id")
    Long actionId;
    LocalDateTime timestamp;

    public ConsultationId(Long userId, String planId, Long actionId, LocalDateTime timestamp) {
        this.userId = userId;
        this.planId = planId;
        this.actionId = actionId;
        this.timestamp = timestamp;
    }

    public ConsultationId() {
    }

    public Long getUserId() {
        return userId;
    }

    public String getPlanId() {
        return planId;
    }

    public Long getActionId() {
        return actionId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
