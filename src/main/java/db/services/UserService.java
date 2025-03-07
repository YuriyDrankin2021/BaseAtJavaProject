package db.services;

import db.dao.UserDao;
import db.models.Auto;
import db.models.User;

import java.util.List;

public class UserService {
    private final UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User findUserById(int id) {
        return usersDao.getById(id);
    }

    public User saveUser(User user) {
        return usersDao.create(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

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
