package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.OrderItems;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class OrderItemsDaoSQLImpl extends AbstractDao<OrderItems> implements OrderItemsDao {

    public OrderItemsDaoSQLImpl() {
        super("order_items");
    }


    @Override
    public OrderItems row2object(ResultSet rs) throws SQLException {
        try {
            OrderItems orderItems = new OrderItems();
            orderItems.setId(rs.getInt("id"));
            orderItems.setOrderId(rs.getInt("order_id"));
            orderItems.setProductId(rs.getInt("product_id"));
            orderItems.setAmount(rs.getInt("amount"));
            return orderItems;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(OrderItems object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("order_id", object.getOrderId());
        row.put("product_id", object.getProductId());
        row.put("amount", object.getAmount());
        return row;
    }
}
