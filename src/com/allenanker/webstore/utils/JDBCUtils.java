package com.allenanker.webstore.utils;

import java.sql.*;

import javax.sql.DataSource;

import com.allenanker.webstore.domain.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class JDBCUtils {
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("myDataSource");
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    private static final String url = "jdbc:mysql://localhost:3306/store_40";
    private static final String username = "root";
    private static final String password = "ASDFJKLP@189cf";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从线程中获取连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        //从线程中获取conneciton
        Connection conn = tl.get();
        if (conn == null) {
            conn = cpds.getConnection();
            //和当前线程绑定
            tl.set(conn);
        }
        return conn;
    }

    // 获取数据源
    public static DataSource getDataSource() {
        return cpds;
    }

    // 释放资源
    public static void closeResource(Statement st, ResultSet rs) {
        closeResultSet(rs);
        closeStatement(st);
    }

    // 释放资源
    public static void closeResource(Connection conn, Statement st, ResultSet rs) {
        closeResource(st, rs);
        closeConn(conn);
    }

    // 释放 connection
    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                //和线程解绑
                tl.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    // 释放 statement ctrl + shift + f 格式化代码
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            st = null;
        }
    }

    // 释放结果集
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
    }


    //开启事务
    public static void startTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    /**
     * 事务提交且释放连接
     */
    public static void commitAndClose() {
        Connection conn = null;
        try {
            conn = getConnection();
            //事务提交
            conn.commit();
            //关闭资源
            conn.close();
            //解除版定
            tl.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 事务回滚且释放资源
     */
    public static void rollbackAndClose() {
        Connection conn = null;
        try {
            conn = getConnection();
            //事务回滚
            conn.rollback();
            //关闭资源
            conn.close();
            //解除版定
            tl.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getJDBCConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        String sql = "select * from user";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        qr.query(sql, new BeanHandler<User>(User.class));
    }
}
