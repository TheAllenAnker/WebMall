package com.allenanker.webstore.web.servlet;

import com.allenanker.webstore.domain.*;
import com.allenanker.webstore.service.OrderService;
import com.allenanker.webstore.service.serviceImp.OrderServiceImp;
import com.allenanker.webstore.utils.UUIDUtils;
import com.allenanker.webstore.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class OrderServlet extends BaseServlet {

    public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("msg", "Please login before making an order.");
            return "/jsp/info.jsp";
        }

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Order order = new Order();
        order.setOid(UUIDUtils.getCode());
        order.setOrderTime(new Date());
        order.setTotal(cart.getTotal());
        order.setState(1);
        order.setUser(user);

        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItemId(UUIDUtils.getId());
            orderItem.setQuantity(cartItem.getNum());
            orderItem.setTotal(cartItem.getSubTotal());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        }

        OrderService orderService = new OrderServiceImp();
        orderService.saveOrder(order);

        cart.clearCart();
        request.setAttribute("order", order);

        return "/jsp/order_info.jsp";
    }
}
