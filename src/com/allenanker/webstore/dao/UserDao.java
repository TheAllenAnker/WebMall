package com.allenanker.webstore.dao;

import com.allenanker.webstore.domain.User;

import java.sql.SQLException;

public interface UserDao {

    void userRegist(User user) throws SQLException, ClassNotFoundException;
}
