package com.be.be_app.playload;

import com.be.be_app.models.Plan;

public class UploadPlanFileResponse {
    private String name;
    private String link;
    private String type;
    private  long size;
    private Plan plan;

    public UploadPlanFileResponse(String name, String link, String type, long size, Plan plan) {
        this.name = name;
        this.link = link;
        this.type = type;
        this.size = size;
        this.plan = plan;
    }

    public UploadPlanFileResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
