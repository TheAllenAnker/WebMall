package com.allenanker.webstore.web.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A base servlet for other servlets(serve as different roles) to extend from.
 */
public class BaseServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String method = request.getParameter("method");
        String path = null;

        Class<? extends BaseServlet> thisClass = this.getClass();
        try {
            Method callMethod = thisClass.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            if (callMethod != null) {
                callMethod.setAccessible(true);
                path = (String) callMethod.invoke(this, request, response);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (path != null) {
            request.getRequestDispatcher(path).forward(request, response);
        }
    }
}
