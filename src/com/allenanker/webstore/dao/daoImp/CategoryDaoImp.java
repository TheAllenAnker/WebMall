package com.allenanker.webstore.dao.daoImp;

import com.allenanker.webstore.dao.CategoryDao;
import com.allenanker.webstore.domain.Category;
import com.allenanker.webstore.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImp implements CategoryDao {
    @Override
    public List<Category> getAllCategories() throws SQLException {
        String sql = "SELECT * FROM category";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<>(Category.class));
    }
}
