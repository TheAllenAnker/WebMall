package com.allenanker.webstore.service.serviceImp;

import com.allenanker.webstore.dao.ProductDao;
import com.allenanker.webstore.dao.daoImp.ProductDaoImp;
import com.allenanker.webstore.domain.PageModel;
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

    @Override
    public PageModel findProductsWithCidAndPage(String cid, int currNum) throws SQLException {
        int totalRecords = productDao.findTotalRecords(cid);
        PageModel pm = new PageModel(currNum, totalRecords, 12);
        List<Product> products = productDao.findProductsWithCidAndPage(cid, pm.getStartIndex(), pm.getPageSize());
        pm.setList(products);
        pm.setUrl("");
        return pm;
    }
}
