package com.allenanker.webstore.service;

import com.allenanker.webstore.domain.User;


import java.sql.SQLException;

public interface UserService {
    void userRegist(User user) throws SQLException, ClassNotFoundException;
}
