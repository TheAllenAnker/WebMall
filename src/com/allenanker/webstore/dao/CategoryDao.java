package com.allenanker.webstore.dao;

import com.allenanker.webstore.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategories() throws SQLException;
}
