package com.allenanker.webstore.web.servlet;

import com.allenanker.webstore.domain.Category;
import com.allenanker.webstore.service.CategoryService;
import com.allenanker.webstore.service.serviceImp.CategoryServiceImp;
import com.allenanker.webstore.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CategoryService categoryService = new CategoryServiceImp();
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("allCats", categoryList);
        return "/jsp/index.jsp";
    }
}
