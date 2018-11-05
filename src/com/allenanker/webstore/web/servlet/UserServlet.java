package com.allenanker.webstore.web.servlet;

import com.allenanker.webstore.domain.User;
import com.allenanker.webstore.service.UserService;
import com.allenanker.webstore.service.serviceImp.UserServiceImp;
import com.allenanker.webstore.utils.MyBeanUtils;
import com.allenanker.webstore.utils.UUIDUtils;
import com.allenanker.webstore.web.base.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class UserServlet extends BaseServlet {
    public String registUI(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/register.jsp";
    }

    public String userRegist(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        MyBeanUtils.populate(user, parameterMap);
        user.setUid(UUIDUtils.getId());
        user.setState(0);
        user.setCode(UUIDUtils.getCode());
        System.out.println(user);
        UserService userService = new UserServiceImp();
        try {
            userService.userRegist(user);
            request.setAttribute("msg", "Register Succeeded.");
        } catch (Exception e) {
            request.setAttribute("msg", "Register Failed.");
            e.printStackTrace();
        }

        return "/jsp/info.jsp";
    }

    public String loginUI(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/login.jsp";
    }

    public String userLogin(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        MyBeanUtils.populate(user, request.getParameterMap());
        UserService userService = new UserServiceImp();
        User actualUser = null;
        try {
            actualUser = userService.userLogin(user);
            request.getSession().setAttribute("user", actualUser);
            response.sendRedirect("/webmall/index.jsp");
            return null;
        } catch (Exception e) {
            String msg = e.getMessage();
            request.setAttribute("msg", msg);
            return "/jsp/login.jsp";
        }
    }

    public String userLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        response.sendRedirect("/webmall/index.jsp");
        return null;
    }
}
