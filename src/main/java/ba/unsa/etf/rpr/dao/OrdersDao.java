package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Orders;

import java.sql.Date;
import java.util.List;

public interface OrdersDao extends Dao<Orders> {

    List<Orders> getByUserId(int uId);
}
