package com.allenanker.webstore.dao.daoImp;

import com.allenanker.webstore.dao.ProductDao;
import com.allenanker.webstore.domain.Product;
import com.allenanker.webstore.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImp implements ProductDao {
    @Override
    public List<Product> findHots() throws SQLException {
        String sql = "SELECT * FROM product WHERE pflag=0 AND is_hot=1 ORDER BY pdate DESC LIMIT 0,9";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<>(Product.class));
    }

    @Override
    public List<Product> findNews() throws SQLException {
        String sql = "SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT 0,9";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<>(Product.class));
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        String sql = "SELECT * FROM product WHERE pid=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanHandler<>(Product.class), pid);
    }

    @Override
    public int findTotalRecords(String cid) throws SQLException {
        String sql = "SELECT COUNT(*) FROM product WHERE cid=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return ((Long) qr.query(sql, new ScalarHandler(), cid)).intValue();
    }

    @Override
    public List<Product> findProductsWithCidAndPage(String cid, int startIndex, int pageSize) throws SQLException {
        String sql = "SELECT * FROM product WHERE cid=? LIMIT ?,?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<>(Product.class), cid, startIndex, pageSize);
    }
}
