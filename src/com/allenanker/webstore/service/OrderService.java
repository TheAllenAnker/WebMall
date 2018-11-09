package com.allenanker.webstore.service;

import com.allenanker.webstore.domain.Order;

import java.sql.SQLException;

public interface OrderService {
    void saveOrder(Order order) throws SQLException;
}
