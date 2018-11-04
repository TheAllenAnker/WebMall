package com.allenanker.webstore.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
    private static ComboPooledDataSource ds = new ComboPooledDataSource();
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    /**
     * Get connection from thread.
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        // get connection from thread
        Connection conn = tl.get();
        if (conn == null) {
            conn = ds.getConnection();
            tl.set(conn);
        }
        return conn;
    }

    public static DataSource getDataSource() {
        return ds;
    }

    public static void closeResource(Statement st, ResultSet rs) {
        closeResultSet(rs);
        closeStatement(st);
    }

    public static void closeResource(Connection conn, Statement st, ResultSet rs) {
        closeResource(st, rs);
        closeConn(conn);
    }

    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                tl.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

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

    public static void startTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    /**
     * Commit transaction and close connection.
     */
    public static void commitAndClose() {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.commit();
            conn.close();
            tl.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rollback transaction and close connection
     */
    public static void rollbackAndClose() {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.rollback();
            conn.close();
            tl.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
