package com.allenanker.webstore.service.serviceImp;

import com.allenanker.webstore.dao.UserDao;
import com.allenanker.webstore.dao.daoImp.UserDaoImp;
import com.allenanker.webstore.domain.User;
import com.allenanker.webstore.service.UserService;

import java.sql.SQLException;

public class UserServiceImp implements UserService {
    UserDao userDao = new UserDaoImp();

    @Override
    public void userRegist(User user) throws SQLException, ClassNotFoundException {
        userDao.userRegist(user);
    }

    @Override
    public User userLogin(User user) throws Exception {
        User returnUser = userDao.userLogin(user);
        if (returnUser == null) {
            throw new RuntimeException("Account name or password is incorrect.");
        } else {
            return user;
        }
    }

    @Override
    public boolean hasUser(String username) throws Exception {
        UserDao userDao = new UserDaoImp();
        return userDao.hasUser(username);
    }
}
