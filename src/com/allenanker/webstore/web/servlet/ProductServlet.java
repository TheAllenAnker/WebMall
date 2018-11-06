package com.allenanker.webstore.web.servlet;

import com.allenanker.webstore.domain.Product;
import com.allenanker.webstore.service.ProductService;
import com.allenanker.webstore.service.serviceImp.ProductServiceImp;
import com.allenanker.webstore.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ProductServlet extends BaseServlet {
    public String findProductByPid(HttpServletRequest request , HttpServletResponse response) throws SQLException {
        String pid = request.getParameter("pid");
        ProductService productService = new ProductServiceImp();
        Product product = productService.findProductByPid(pid);
        request.setAttribute("pro", product);
        return "/jsp/product_info.jsp";
    }
}
