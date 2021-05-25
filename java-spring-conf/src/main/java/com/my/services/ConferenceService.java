package com.my.services;

import com.my.dao.ConfDao;
import com.my.domain.Conference;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ConferenceService {
    private ConfDao confDao = new ConfDao();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public List<Conference> findAll() throws Exception {
        return confDao.findAll();
    }

    public Timestamp timeParser(String time) throws ParseException {
        return new Timestamp(dateFormat.parse(time).getTime());
    }

    public void save(Conference conference) throws Exception {
        confDao.save(conference);
    }
    public String confCheck(String place) {
        String error = "OK";
        if (place.trim().equals("")) {
            error = "Заполните поле Место";
        }
        return error;
    }
}
