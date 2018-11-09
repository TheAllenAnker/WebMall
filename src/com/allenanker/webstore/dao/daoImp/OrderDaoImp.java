package com.allenanker.webstore.dao.daoImp;

import com.allenanker.webstore.dao.OrderDao;
import com.allenanker.webstore.domain.Order;
import com.allenanker.webstore.domain.OrderItem;
import com.allenanker.webstore.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class OrderDaoImp implements OrderDao {
    @Override
    public void saveOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
        Object[] params = {order.getOid(), order.getOrderTime(), order.getTotal(), order.getState(),
                order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid()};
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql, params);
    }

    @Override
    public void saveOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "INSERT INTO orderitem VALUES(?,?,?,?,?)";
        Object[] params = {orderItem.getItemId(), orderItem.getQuantity(), orderItem.getTotal(),
                orderItem.getProduct().getPid(), orderItem.getOrder().getOid()};
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql, params);
    }
}
