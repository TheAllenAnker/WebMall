package com.allenanker.webstore.web.servlet;

import com.allenanker.webstore.domain.User;
import com.allenanker.webstore.service.UserService;
import com.allenanker.webstore.service.serviceImp.UserServiceImp;
import com.allenanker.webstore.utils.MyBeanUtils;
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
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UserServlet extends BaseServlet {
    public String registUI(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/register.jsp";
    }

    public String userRegist(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        MyBeanUtils.populate(user, parameterMap);

        UserService userService = new UserServiceImp();
        try {
            userService.userRegist(user);
            request.setAttribute("meg", "Register Succeeded.");
        } catch (Exception e) {
            request.setAttribute("meg", "Register Failed.");
        }

        return "/jsp/info.jsp";
    }
}
