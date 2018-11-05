package com.allenanker.webstore.web.servlet;

import com.allenanker.webstore.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return "/jsp/index.jsp";
    }
}
