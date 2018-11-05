package com.allenanker.webstore.web.servlet;

import com.allenanker.webstore.domain.Category;
import com.allenanker.webstore.service.CategoryService;
import com.allenanker.webstore.service.serviceImp.CategoryServiceImp;
import com.allenanker.webstore.web.base.BaseServlet;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CategoryServlet extends BaseServlet {
    public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        CategoryService categoryService = new CategoryServiceImp();
        List<Category> categoryList = categoryService.getAllCategories();
        // list to json format
        String jsonStr = JSONArray.fromObject(categoryList).toString();
        response.setContentType("application/json;charset=utf-8");
        System.out.println(jsonStr);
        response.getWriter().write(jsonStr);
        return null;
    }
}
