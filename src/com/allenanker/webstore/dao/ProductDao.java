package com.allenanker.webstore.dao;

import com.allenanker.webstore.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> findHots() throws SQLException;

    List<Product> findNews() throws SQLException;

    Product findProductByPid(String pid) throws SQLException;
}
