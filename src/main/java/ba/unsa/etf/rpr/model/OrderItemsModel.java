package ba.unsa.etf.rpr.model;

import ba.unsa.etf.rpr.domain.OrderItems;
import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.domain.Products;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class OrderItemsModel {
    public SimpleObjectProperty<Orders> order = new SimpleObjectProperty<>();
    public SimpleObjectProperty<Products> product = new SimpleObjectProperty<>();
    public SimpleIntegerProperty amount = new SimpleIntegerProperty();
    public void fromOrderItems(OrderItems orderItems) {
        this.order.set(orderItems.getOrder());
        this.product.set(orderItems.getProduct());
        this.amount.set(orderItems.getAmount());
    }
    public OrderItems toOrderItems() {
        OrderItems orderItems = new OrderItems();
        orderItems.setOrder(this.order.getValue());
        orderItems.setProduct(this.product.getValue());
        orderItems.setAmount(this.amount.getValue());
        return orderItems;
    }
    public SimpleIntegerProperty amountProperty() { return this.amount; }
    public Orders getOrder() { return this.order.get(); }
    public void setOrder(Orders orders) { this.order.set(orders);}
    public Products getProduct() { return this.product.get(); }
    public void setProduct(Products p) { this.product.set(p);}
}
