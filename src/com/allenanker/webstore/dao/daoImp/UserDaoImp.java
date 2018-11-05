package com.allenanker.webstore.dao.daoImp;

import com.allenanker.webstore.dao.UserDao;
import com.allenanker.webstore.domain.User;
import com.allenanker.webstore.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.*;

public class UserDaoImp implements UserDao {

    @Override
    public void userRegist(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params = {user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
                user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode()};
        qr.update(sql, params);
    }

    @Override
    public User userLogin(User user) throws Exception {
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanHandler<>(User.class), user.getUsername(), user.getPassword());
    }

    @Override
    public boolean hasUser(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username=?";
        boolean hasUser = false;
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        if (qr.query(sql, new BeanHandler<>(User.class), username) != null) {
            hasUser = true;
        }

        return hasUser;
    }
}
