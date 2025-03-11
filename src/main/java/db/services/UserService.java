package db.services;

import db.dao.UserDao;
import db.models.Auto;
import db.models.User;
import io.qameta.allure.Step;

import java.util.List;

public class UserService {
    private final UserDao usersDao = new UserDao();

    public UserService() {
    }

    @Step("Get from db data of user with id = {id}")
    public User findUserById(int id) {
        return usersDao.getById(id);
    }

    @Step("Create in db new User with data: {user}")
    public User saveUser(User user) {
        return usersDao.create(user);
    }

    @Step("Delete in db new User with data: {user}")
    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    @Step("Update in db new User with data: {user}")
    public User updateUser(User user) {
        return usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }

    public Auto findAutoById(int id) {
        return usersDao.findAutoById(id);
    }

}
