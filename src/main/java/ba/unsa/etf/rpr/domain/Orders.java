package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Orders {

    private int id;
    private int userId;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
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
        return Objects.hash(id, userId, date);
    }
}
