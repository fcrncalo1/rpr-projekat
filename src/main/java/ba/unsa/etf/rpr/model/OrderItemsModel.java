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

    public Orders getOrder() {
        return order.get();
    }

    public SimpleObjectProperty<Orders> orderProperty() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order.set(order);
    }

    public Products getProduct() {
        return product.get();
    }

    public SimpleObjectProperty<Products> productProperty() {
        return product;
    }

    public void setProduct(Products product) {
        this.product.set(product);
    }

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }
}
