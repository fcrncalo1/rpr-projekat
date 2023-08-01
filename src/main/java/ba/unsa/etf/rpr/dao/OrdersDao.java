package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Orders;

import java.sql.Date;
import java.util.List;

/**
 * Dao interface for orders bean
 */
public interface OrdersDao extends Dao<Orders> {

    /**
     * Method that returns all orders with the given user id
     * @param uId
     * @return list of filtered orders
     */
    List<Orders> getByUserId(int uId);
}
