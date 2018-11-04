package com.allenanker.webstore.dao.daoImp;

import com.allenanker.webstore.dao.UserDao;
import com.allenanker.webstore.domain.User;
import com.allenanker.webstore.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoImp implements UserDao {

    @Override
    public void userRegist(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)";
//        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
//        Object[] params = {user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
//                user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode()};
//        qr.update(sql, params);
        // the above code using c3p0 does not work yet, so I am doing it in the simple way.
        String url = "jdbc:mysql://localhost:3306/store_40";
        String username = "root";
        String password = "ASDFJKLP@189cf";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUid());
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getName());
        statement.setString(5, user.getEmail());
        statement.setString(6, user.getTelephone());
        statement.setObject(7, user.getBirthday());
        statement.setString(8, user.getSex());
        statement.setInt(9, user.getState());
        statement.setString(10, user.getCode());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}
