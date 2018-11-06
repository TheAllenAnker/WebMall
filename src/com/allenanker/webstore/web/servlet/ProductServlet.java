package com.allenanker.webstore.web.servlet;

import com.allenanker.webstore.domain.PageModel;
import com.allenanker.webstore.domain.Product;
import com.allenanker.webstore.service.ProductService;
import com.allenanker.webstore.service.serviceImp.ProductServiceImp;
import com.allenanker.webstore.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ProductServlet extends BaseServlet {
    ProductService productService = new ProductServiceImp();

    public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String pid = request.getParameter("pid");
        Product product = productService.findProductByPid(pid);
        request.setAttribute("pro", product);
        return "/jsp/product_info.jsp";
    }

    public String findProductsWithCidAndPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cid = request.getParameter("cid");
        int currNum = Integer.parseInt(request.getParameter("num"));
        PageModel pm  = productService.findProductsWithCidAndPage(cid, currNum);
        request.setAttribute("page", pm);
        return "/jsp/product_list.jsp";
    }
}
