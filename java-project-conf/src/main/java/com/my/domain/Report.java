package com.my.domain;

import java.io.Serializable;

public class Report implements Entity, Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private Long userId;
    private Long conferenceId = 1L;

    public Report (String title, Long userId) {
        this.title = title;
        this.userId = userId;
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
