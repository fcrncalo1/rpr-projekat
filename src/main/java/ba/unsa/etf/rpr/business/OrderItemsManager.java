package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.OrderItems;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.exceptions.ProductsException;

import java.sql.SQLException;
import java.util.List;

public class OrderItemsManager {
    public void delete(int id) throws ProductsException {
        try {
            DaoFactory.orderItemsDao().delete(id);
        } catch (SQLException e) {
            throw new ProductsException(e.getMessage());
        }
    }

    public OrderItems add(OrderItems oi) throws ProductsException {
        try {
            DaoFactory.orderItemsDao().add(oi);
        } catch (SQLException e) {
            throw new ProductsException(e.getMessage());
        }
        return oi;
    }

    public OrderItems update(OrderItems oi) throws ProductsException{
        try {
            return DaoFactory.orderItemsDao().update(oi);
        } catch (SQLException e) {
            throw new ProductsException(e.getMessage());
        }
    }

    public List<OrderItems> getAll() throws ProductsException {
        try {
            return DaoFactory.orderItemsDao().getAll();
        }catch (SQLException e) {
            throw new ProductsException(e.getMessage());
        }
    }

    public OrderItems getById(int uId) throws ProductsException {
        try {
            return DaoFactory.orderItemsDao().getById(uId);
        }catch (SQLException e) {
            throw new ProductsException(e.getMessage());
        }
    }
}
