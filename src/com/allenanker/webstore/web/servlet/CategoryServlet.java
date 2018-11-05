package com.allenanker.webstore.web.servlet;

import com.allenanker.webstore.domain.Category;
import com.allenanker.webstore.service.CategoryService;
import com.allenanker.webstore.service.serviceImp.CategoryServiceImp;
import com.allenanker.webstore.utils.JedisUtils;
import com.allenanker.webstore.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CategoryServlet extends BaseServlet {
    public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // try to get from redis first
        Jedis jedis = JedisUtils.getJedis();
        String jsonStr = jedis.get("allCats");
        System.out.println("From redis: " + jsonStr);
        if (jsonStr == null || jsonStr.equals("")) {
            CategoryService categoryService = new CategoryServiceImp();
            List<Category> categoryList = categoryService.getAllCategories();
            // list to json format
            jsonStr = JSONArray.fromObject(categoryList).toString();
            jedis.set("allCats", jsonStr);
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonStr);
        JedisUtils.closeJedis(jedis);

        return null;
    }
}
