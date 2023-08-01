package ba.unsa.etf.rpr.dao;

/**
 * Factory method for DAO implementations
 * @author Faris Crnčalo
 */
public class DaoFactory {

    private static final OrderItemsDao orderItemsDao = new OrderItemsDaoSQLImpl();
    private static final OrdersDao ordersDao = new OrdersDaoSQLImpl();
    private static final ProductsDao productsDao = new ProductsDaoSQLImpl();
    private static final UsersDao usersDao = new UsersDaoSQLImpl();

    private DaoFactory(){
    }

    public static OrderItemsDao orderItemsDao(){
        return orderItemsDao;
    }

    public static OrdersDao ordersDao(){
        return ordersDao;
    }

    public static ProductsDao productsDao(){
        return productsDao;
    }

    public static UsersDao usersDao(){
        return usersDao;
    }

}
