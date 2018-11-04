package com.allenanker.webstore;

import com.allenanker.webstore.domain.User;
import com.allenanker.webstore.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * A class for testing simple functionality.
 */
public class TestDemo {
    public static void main(String[] args) throws Exception {
//        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
//        String sql = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)";
//        User user = new User();
//        Object[] params = {user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
//                user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode()};
//        qr.update(sql, params);
        String url = "jdbc:mysql://localhost:3306/store_40";
        String username = "root";
        String password = "ASDFJKLP@189cf";
        Connection connection = DriverManager.getConnection(url, username, password);
    }
}
