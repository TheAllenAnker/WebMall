package com.allenanker.webstore.web.servlet;

import com.allenanker.webstore.domain.Cart;
import com.allenanker.webstore.domain.CartItem;
import com.allenanker.webstore.domain.Product;
import com.allenanker.webstore.service.ProductService;
import com.allenanker.webstore.service.serviceImp.ProductServiceImp;
import com.allenanker.webstore.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CartServlet extends BaseServlet {
    public void addToCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        String pid = request.getParameter("pid");
        String num = request.getParameter("num");
        ProductService productService = new ProductServiceImp();
        Product product = productService.findProductByPid(pid);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setNum(Integer.parseInt(num));
        cart.addCartItem(cartItem);
        cart.setCartItems(cart.getCartItems());
        request.getSession().setAttribute("cart", cart);

        response.sendRedirect("/webmall/jsp/cart.jsp");
    }

    public void delCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        String pid = request.getParameter("pid");
        cart.removeCartItem(pid);
        request.getSession().setAttribute("cart", cart);

        response.sendRedirect("/webmall/jsp/cart.jsp");
    }

    public void clearCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.clearCart();
        request.getSession().setAttribute("cart", cart);

        response.sendRedirect("/webmall/jsp/cart.jsp");
    }
}
