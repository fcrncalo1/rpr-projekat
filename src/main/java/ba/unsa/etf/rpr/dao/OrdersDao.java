package ba.unsa.etf.rpr.dao;

import java.util.Date;
import java.util.List;

public interface OrdersDao extends Dao {

    List getByDateRange(Date start, Date end);
}
