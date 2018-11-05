package com.allenanker.webstore.service.serviceImp;

import com.allenanker.webstore.dao.UserDao;
import com.allenanker.webstore.dao.daoImp.UserDaoImp;
import com.allenanker.webstore.domain.User;
import com.allenanker.webstore.service.UserService;

import java.sql.SQLException;

public class UserServiceImp implements UserService {

    @Override
    public void userRegist(User user) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDaoImp();
        userDao.userRegist(user);
    }

    @Override
    public User userLogin(User user) throws Exception {
        UserDao userDao = new UserDaoImp();
        User returnUser = userDao.userLogin(user);
        if (returnUser == null) {
            throw new RuntimeException("Account name or password is incorrect.");
        } else {
            return user;
        }
    }
}
