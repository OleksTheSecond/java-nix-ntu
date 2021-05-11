package com.my.demos;

import com.my.dao.UserDao;
import com.my.domain.Roles;
import com.my.domain.User;

import java.sql.SQLException;
import java.util.List;

public class DemoUser {
    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }


    public static void main(String[] args) throws SQLException {
       // User user1 = new User(1L, "OleksII",
       //         "Aleksieiev", Roles.MODERATOR.getRoleId());
//

      //  User user2 = new User(2L, "Aleks",
       //         "Alekss", Roles.SPEAKER.getRoleId());
//
      //  User user3 = new User(3L, "Popka",
     //           "NeDyrak", Roles.LISTENER.getRoleId());

        UserDao userDao = new UserDao();

      //  //Add users to base
      //  userDao.create(user1);
      //  userDao.create(user2);
      //  userDao.create(user3);
        printList(userDao.findAll());
        System.out.println("###########################################");

        //Update user
     //   User toUpdate = new User(2L, "AleksUpdated",
     //                   "Up", Roles.LISTENER.getRoleId());

      //  userDao.update(toUpdate);
        printList(userDao.findAll());
        System.out.println("###########################################");
        //Find by ID
        User finded = (userDao.findById(2L));
        System.out.println(finded);
        System.out.println("###########################################");

        //Delete all
//        userDao.deleteById(1L);
//        userDao.deleteById(2L);
//        userDao.deleteById(3L);
    }
}
