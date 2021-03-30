package com.my.demos;

import com.my.dao.UserDao;
import com.my.domain.Roles;
import com.my.domain.User;

import java.util.List;

public class DemoUser {
    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }


    public static void main(String[] args) {
        User user1 = new User(1, "OleksII",
                "Aleksieiev", Roles.MODERATOR.getRoleId());


        User user2 = new User(2, "Aleks",
                "Alekss", Roles.SPEAKER.getRoleId());

        User user3 = new User(3, "Popka",
                "NeDyrak", Roles.LISTENER.getRoleId());

        UserDao userDao = new UserDao();

        //Add users to base
        userDao.create(user1);
        userDao.create(user2);
        userDao.create(user3);
        printList(userDao.findAll());
        System.out.println("###########################################");

        //Update user
        User toUpdate = new User(2, "AleksUpdated",
                        "Up", Roles.LISTENER.getRoleId());

        userDao.update(toUpdate);
        printList(userDao.findAll());
        System.out.println("###########################################");
        //Find by ID
        User finded = (User) (userDao.findById(2));
        System.out.println(finded);
        System.out.println("###########################################");

        //Delete all
        userDao.delete(user1);
        userDao.delete(user2);
        userDao.delete(user3);
    }
}
