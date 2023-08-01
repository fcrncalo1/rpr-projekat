package ba.unsa.etf.rpr.model;

import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.domain.Users;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Model class for 2 way data binding
 * @author Faris Crnčalo
 */
public class OrdersModel {
    public SimpleObjectProperty<Users> user = new SimpleObjectProperty<>();
    public SimpleObjectProperty<LocalDate> orderDate = new SimpleObjectProperty<>();
    public void fromOrder(Orders o) {
        this.user.set(o.getUser());
        this.orderDate.set(o.getOrderDate().toLocalDate());
    }
    public Orders toOrder(){
        Orders orders = new Orders();
        orders.setUser(this.user.getValue());
        orders.setOrderDate(Date.valueOf(this.orderDate.getValue()));
        return orders;
    }

    public Users getUser() {
        return user.get();
    }

    public SimpleObjectProperty<Users> userProperty() {
        return user;
    }

    public void setUser(Users user) {
        this.user.set(user);
    }

    public LocalDate getOrderDate() {
        return orderDate.get();
    }

    public SimpleObjectProperty<LocalDate> orderDateProperty() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate.set(orderDate);
    }
}
