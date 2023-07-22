package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.OrderItems;
import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.domain.Products;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class OrdersDaoSQLImpl extends AbstractDao<Orders> implements OrdersDao {


    public OrdersDaoSQLImpl() {
        super("orders");
    }


    @Override
    public List<Orders> getByDateRange(Date start, Date end) {
        String query = "SELECT * FROM orders WHERE order_date BETWEEN ? AND ?";
        List<Orders> orders = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDate(1, (java.sql.Date) start);
            stmt.setDate(2, (java.sql.Date) end);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt("id"));
                order.setUser(DaoFactory.usersDao().getById(rs.getInt("user_id")));
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
    public Orders row2object(ResultSet rs) throws SQLException {
        try {
            Orders orders = new Orders();
            orders.setId(rs.getInt("id"));
            orders.setUser(DaoFactory.usersDao().getById(rs.getInt("user_id")));
            orders.setOrderDate(rs.getDate("order_date"));
            return orders;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Orders object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("user_id", object.getUser().getId());
        row.put("order_date", object.getOrderDate());
        return row;
    }
}
