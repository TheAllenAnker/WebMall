package com.allenanker.webstore.service.serviceImp;

import com.allenanker.webstore.dao.ProductDao;
import com.allenanker.webstore.dao.daoImp.ProductDaoImp;
import com.allenanker.webstore.domain.Product;
import com.allenanker.webstore.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImp implements ProductService {
    ProductDao productDao = new ProductDaoImp();

    @Override
    public List<Product> findHots() throws SQLException {
        return productDao.findHots();
    }

    @Override
    public List<Product> findNews() throws SQLException {
        return productDao.findNews();
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        return productDao.findProductByPid(pid);
    }
}
