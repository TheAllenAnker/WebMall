package com.allenanker.webstore.dao.daoImp;

import com.allenanker.webstore.dao.UserDao;
import com.allenanker.webstore.domain.User;
import com.allenanker.webstore.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.*;

public class UserDaoImp implements UserDao {

    @Override
    public void userRegist(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)";
//        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
//        Object[] params = {user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
//                user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode()};
//        qr.update(sql, params);
        // the above code using c3p0 does not work yet, so I am doing it in the simple way.
        Connection connection = JDBCUtils.getJDBCConnection();
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

    @Override
    public User userLogin(User user) throws Exception {
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        Connection connection = JDBCUtils.getJDBCConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = null;
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        rs = preparedStatement.executeQuery();
        boolean flag = false;
        User returnUser = null;
        if (rs.next()) {
            flag = true;
            returnUser = new User();
            user.setUsername(user.getUsername());
            user.setPassword(user.getPassword());
        }
        rs.close();
        preparedStatement.close();
        connection.close();

        return flag ? returnUser : null;
    }
}
