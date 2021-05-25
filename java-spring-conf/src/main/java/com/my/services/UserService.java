package com.my.services;

import com.my.dao.UserDao;
import com.my.domain.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private UserDao userDao = new UserDao();

    public String login(String email, String password) throws Exception {
        User user = userDao.findByEmail(email);
        if (email.trim().equals("") ||
            password.trim().equals("")) {
            return "Заполните поля";
        }
        if (user == null) {
            return "Не верная почта";
        }
        else {
            if (!user.getPassword().equals(password)) {
                return "Не правильный пароль";
            }

            return "OK";
        }
    }

    public String userCheck(String firstName, String lastName, String email, String password) {
        String error = "OK";

        Pattern pattern = Pattern.compile("[0-9]+?");
        Matcher matcher = pattern.matcher(firstName);

        if (matcher.find()) {
            error = "В имени не должно быть цыфр.";
        }
        matcher = pattern.matcher(lastName);
        if (matcher.find()) {
            error = "В фамилии не должно быть цыфр.";
        }

        if (firstName.trim().equals("") ||
                lastName.trim().equals("") ||
                password.trim().equals("") ||
                email.trim().equals("")) {
            error = "Все поля должны быть заполнены.";
        }
        return error;
    }

    public void addUser(String firstName, String lastName, String email, String password, int roleId) throws Exception {
        User user = new User(firstName, lastName, email, password, roleId);
        userDao.save(user);
    }

    public User getUser(String email) throws Exception {
        return userDao.findByEmail(email);
    }
    public User getUser(Long id) throws Exception {
        return userDao.findById(id);
    }

    public void updateUser(User user) throws Exception {
        userDao.update(user);
    }

    public List<User> findAll() throws Exception {
        return  userDao.findAll();
    }

}
