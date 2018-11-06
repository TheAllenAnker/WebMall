package com.allenanker.webstore.web.servlet;

import com.allenanker.webstore.domain.Product;
import com.allenanker.webstore.service.ProductService;
import com.allenanker.webstore.service.serviceImp.ProductServiceImp;
import com.allenanker.webstore.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ProductService productService = new ProductServiceImp();
        List<Product> hotsList = productService.findHots();
        List<Product> newsList = productService.findNews();
        request.setAttribute("hots", hotsList);
        request.setAttribute("news", newsList);
        return "/jsp/index.jsp";
    }
}
