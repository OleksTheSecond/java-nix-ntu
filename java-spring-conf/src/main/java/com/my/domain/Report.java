package com.my.domain;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@Table(name="reports")
public class Report implements Entity, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="report_id")
    private Long id;
    @Column(name="report_title")
    private String title;
    @Column(name="user_id")
    private Long userId;
    @Column(name="conf_id")
    private Long conferenceId;

    public Report(){}

    public Report (String title, Long userId) {
        this.title = title;
        this.userId = userId;
        conferenceId = 1L;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Long getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", userId=" + userId +
                ", conferenceId=" + conferenceId +
                '}';
    }
}
