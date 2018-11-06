package com.allenanker.webstore.service;

import com.allenanker.webstore.domain.PageModel;
import com.allenanker.webstore.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> findHots() throws SQLException;

    List<Product> findNews() throws SQLException;

    Product findProductByPid(String pid) throws SQLException;

    PageModel findProductsWithCidAndPage(String cid, int currNum) throws SQLException;
}
