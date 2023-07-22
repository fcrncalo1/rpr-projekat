package ba.unsa.etf.rpr.domain;

import java.sql.Date;
import java.util.Objects;

public class Orders implements Idable{

    private int id;
    private Users user;
    private Date orderDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date date) {
        this.orderDate = date;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + orderDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, orderDate);
    }
}
