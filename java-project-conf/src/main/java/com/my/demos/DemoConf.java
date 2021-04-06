package com.my.demos;

import com.my.dao.ConfDao;
import com.my.domain.Conference;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DemoConf {

    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd*HH:mm");

    public static void prinList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    public static void main(String[] args) throws SQLException {

        Date timeStart[] = new Date[3];
        Date timeEnd[] = new Date[3];
        String times[] = {"2021-03-08*13:00", "2021-03-09*13:00", "2021-03-10*13:00",
                "2021-03-08*14:00", "2021-03-09*14:00", "2021-03-10*14:00"};

        Date startToUpdate = null;
        Date endToUpdate = null;

        try {

            timeStart[0] = dateFormat.parse(times[0]);
            timeStart[1] = dateFormat.parse(times[1]);
            timeStart[2] = dateFormat.parse(times[2]);
            timeEnd[0] = dateFormat.parse(times[3]);
            timeEnd[1] = dateFormat.parse(times[4]);
            timeEnd[2] = dateFormat.parse(times[5]);

            startToUpdate = dateFormat.parse("2021-02-09*18:00");
            endToUpdate = dateFormat.parse("2021-02-09*19:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Conference conference1 = new Conference(1, new Timestamp(timeStart[0].getTime()),
                new Timestamp(timeEnd[0].getTime()), "Tyt#1", 0);

        Conference conference2 = new Conference(2, new Timestamp(timeStart[1].getTime()),
                new Timestamp(timeEnd[1].getTime()), "Tyt#2", 0);

        Conference conference3 = new Conference(3, new Timestamp(timeStart[2].getTime()),
                new Timestamp(timeEnd[2].getTime()), "Tyt#3", 0);


        ConfDao confDao = new ConfDao();

        //Add conf to base
        confDao.create(conference1);
        confDao.create(conference2);
        confDao.create(conference3);

        prinList(confDao.findAll());

        //Update
        Conference toUpdate = new Conference(2, new Timestamp(startToUpdate.getTime()),
                new Timestamp(endToUpdate.getTime()), "Tyt#UPDATED", 0);
        confDao.update(toUpdate);
        System.out.println("#######################################");
        prinList(confDao.findAll());
        System.out.println("#######################################");

        //Find by ID
        Conference finded = (Conference) (confDao.findById(2));
        System.out.println(finded);

        //Delete all
        confDao.delete(conference1);
        confDao.delete(conference2);
        confDao.delete(conference3);
    }


}

