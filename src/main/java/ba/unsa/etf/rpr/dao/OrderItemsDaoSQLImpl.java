package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.OrderItems;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class OrderItemsDaoSQLImpl  implements OrderItemsDao {

    private Connection connection;

    public OrderItemsDaoSQLImpl() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582897","sql7582897","z2bc7SG6Nh");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItems getById(int id) {
        String query = "SELECT * FROM order_items WHERE order_items_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                OrderItems orderItem = new OrderItems();
                orderItem.setId(rs.getInt("order_items_id"));
                orderItem.setOrderId(rs.getInt("order_id"));
                orderItem.setProductId(rs.getInt("product_id"));
                orderItem.setAmount(rs.getInt("amount"));
                rs.close();
                return orderItem;
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderItems add(OrderItems item) {
        String insert = "INSERT INTO order_items(order_id,product_id,amount) VALUES(?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,item.getOrderId());
            stmt.setInt(2, item.getProductId());
            stmt.setInt(3,item.getAmount());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
            return item;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderItems update(OrderItems item) {
        String updt = "UPDATE order_items SET order_id = ?, product_id = ?, amount = ? WHERE order_items_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(updt,Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1,item.getOrderId());
            stmt.setObject(2,item.getProductId());
            stmt.setObject(3,item.getAmount());
            stmt.setObject(4,item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String dlt = "DELETE FROM order_items WHERE order_items_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(dlt,Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderItems> getAll() {
        String query = "SELECT * FROM orders";
        List<OrderItems> orderItems = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderItems orderItem = new OrderItems();
                orderItem.setId(rs.getInt("order_items_id"));
                orderItem.setOrderId(rs.getInt("order_id"));
                orderItem.setProductId(rs.getInt("product_id"));
                orderItem.setAmount(rs.getInt("amount"));
                orderItems.add(orderItem);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }
}
