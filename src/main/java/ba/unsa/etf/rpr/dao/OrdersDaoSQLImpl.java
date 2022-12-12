package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.domain.Products;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class OrdersDaoSQLImpl implements OrdersDao {

    private Connection connection;

    public OrdersDaoSQLImpl() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582897","sql7582897","z2bc7SG6Nh");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Orders getById(int id) {
        String query = "SELECT * FROM orders WHERE order_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getDate("order_date"));
                rs.close();
                return order;
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Orders add(Orders item) {
        String insert = "INSERT INTO orders(user_id,order_date) VALUES(?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,item.getUserId());
            stmt.setDate(2, (java.sql.Date) item.getOrderDate());
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
    public Orders update(Orders item) {
        String updt = "UPDATE orders SET user_id = ?, order_date = ? WHERE order_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(updt,Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1,item.getUserId());
            stmt.setObject(2,item.getOrderDate());
            stmt.setObject(3,item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String dlt = "DELETE FROM orders WHERE order_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(dlt,Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Orders> getAll() {
        String query = "SELECT * FROM orders";
        List<Orders> orders = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getDate("order_date"));
                orders.add(order);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Orders> getByDateRange(Date start, Date end) {
        String query = "SELECT * FROM orders WHERE order_date BETWEEN ? AND ?";
        List<Orders> orders = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setDate(1, (java.sql.Date) start);
            stmt.setDate(2, (java.sql.Date) end);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getDate("order_date"));
                orders.add(order);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
