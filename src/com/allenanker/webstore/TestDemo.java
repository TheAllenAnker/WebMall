package com.allenanker.webstore;

import com.allenanker.webstore.utils.JDBCUtils;


/**
 * A class for testing simple functionality.
 */
public class TestDemo {
    public static void main(String[] args) throws Exception {
        JDBCUtils.getDataSource();
    }
}
