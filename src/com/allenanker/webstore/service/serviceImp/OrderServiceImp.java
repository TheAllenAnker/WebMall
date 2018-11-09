package com.allenanker.webstore.service.serviceImp;

import com.allenanker.webstore.dao.OrderDao;
import com.allenanker.webstore.dao.daoImp.OrderDaoImp;
import com.allenanker.webstore.domain.Order;
import com.allenanker.webstore.domain.OrderItem;
import com.allenanker.webstore.service.OrderService;

import java.sql.SQLException;

public class OrderServiceImp implements OrderService {
    OrderDao orderDao = new OrderDaoImp();

    @Override
    public void saveOrder(Order order) throws SQLException {
        orderDao.saveOrder(order);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderDao.saveOrderItem(orderItem);
        }
    }
}
