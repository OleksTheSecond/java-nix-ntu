package com.my.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Conference implements Entity, Serializable {
    private Long confId;  private static final long serialVersionUID = 1L;
    private Timestamp startTime;
    private Timestamp endTime;
    private String place;
    private int reportsCount;

    public Conference(Long confId, Timestamp startTime, Timestamp endTime, String place, int reportsCount) {
        this.confId = confId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
        this.reportsCount = reportsCount;
    }

    public Long getId() {
        return confId;
    }

    public void setId(Long confId) {
        this.confId = confId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getReportsCount() {
        return reportsCount;
    }

    public void setReportsCount(int reportsCount) {
        this.reportsCount = reportsCount;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "confId=" + confId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", place='" + place + '\'' +
                ", reportsCount=" + reportsCount +
                '}';
    }
}
