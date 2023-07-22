package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.exceptions.OrdersException;
import ba.unsa.etf.rpr.exceptions.ProductsException;

import java.sql.SQLException;
import java.util.List;

public class OrdersManager {
    public void delete(int id) throws OrdersException {
        try {
            DaoFactory.ordersDao().delete(id);
        } catch (SQLException e) {
            throw new OrdersException(e.getMessage());
        }
    }

    public Orders add(Orders o) throws OrdersException {
        try {
            DaoFactory.ordersDao().add(o);
        } catch (SQLException e) {
            throw new OrdersException(e.getMessage());
        }
        return o;
    }

    public Orders update(Orders o) throws OrdersException{
        try {
            return DaoFactory.ordersDao().update(o);
        } catch (SQLException e) {
            throw new OrdersException(e.getMessage());
        }
    }

    public List<Orders> getAll() throws OrdersException {
        try {
            return DaoFactory.ordersDao().getAll();
        }catch (SQLException e) {
            throw new OrdersException(e.getMessage());
        }
    }

    public Orders getById(int uId) throws OrdersException {
        try {
            return DaoFactory.ordersDao().getById(uId);
        }catch (SQLException e) {
            throw new OrdersException(e.getMessage());
        }
    }
}
