package com.my.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@javax.persistence.Entity
@Table(name="CONFERENCES")
public class Conference implements Entity, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="conf_id")
    private Long confId;
    @Column(name="start_time")
    private Timestamp startTime;
    @Column(name="end_time")
    private Timestamp endTime;
    @Column(name="place")
    private String place;
    @Column(name="reports_count")
    private int reportsCount;

    public Conference(Long confId, Timestamp startTime, Timestamp endTime, String place, int reportsCount) {
        this.confId = confId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
        this.reportsCount = reportsCount;
    }

    public Conference(Timestamp startTime, Timestamp endTime, String place) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
        this.reportsCount = 0;
    }

    public Conference() {}

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

    public Long getConfId() {
        return confId;
    }

    public void setConfId(Long confId) {
        this.confId = confId;
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
