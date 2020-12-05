package com.be.be_app.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PlanFile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String planFileId;

    private String name;

    private String type;
    private  String link;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    public PlanFile(String planFileId, String name, String type, String link, byte[] data, Plan plan) {
        this.planFileId = planFileId;
        this.name = name;
        this.type = type;
        this.link = link;
        this.data = data;
        this.plan = plan;
    }

    public PlanFile() {
    }

    public String getPlanFileId() {
        return planFileId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public byte[] getData() {
        return data;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
