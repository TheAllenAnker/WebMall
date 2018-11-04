package com.allenanker.webstore.service.serviceImp;

import com.allenanker.webstore.dao.UserDao;
import com.allenanker.webstore.dao.daoImp.UserDaoImp;
import com.allenanker.webstore.domain.User;
import com.allenanker.webstore.service.UserService;

public class UserServiceImp implements UserService {

    @Override
    public void userRegist(User user) {
        UserDao userDao = new UserDaoImp();
        userDao.userRegist(user);
    }
}
