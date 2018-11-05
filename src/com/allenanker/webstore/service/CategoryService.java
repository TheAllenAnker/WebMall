package com.allenanker.webstore.service;

import com.allenanker.webstore.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories() throws SQLException;
}
