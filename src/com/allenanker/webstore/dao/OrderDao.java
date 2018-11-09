package com.allenanker.webstore.dao;

import com.allenanker.webstore.domain.Order;
import com.allenanker.webstore.domain.OrderItem;

import java.sql.SQLException;

public interface OrderDao {
    void saveOrder(Order order) throws SQLException;

    void saveOrderItem(OrderItem orderItem) throws SQLException;
}
