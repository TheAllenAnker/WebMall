package com.allenanker.webstore.service.serviceImp;

import com.allenanker.webstore.dao.CategoryDao;
import com.allenanker.webstore.dao.daoImp.CategoryDaoImp;
import com.allenanker.webstore.domain.Category;
import com.allenanker.webstore.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImp implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImp();

    @Override
    public List<Category> getAllCategories() throws SQLException {
        return categoryDao.getAllCategories();
    }
}
