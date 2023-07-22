package ba.unsa.etf.rpr.model;

import ba.unsa.etf.rpr.domain.Products;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductsModel {
    public SimpleStringProperty nameField = new SimpleStringProperty();
    public SimpleDoubleProperty priceField = new SimpleDoubleProperty();
    public SimpleIntegerProperty quantityField = new SimpleIntegerProperty();

    public void fromProduct(Products p) {
        this.nameField.set(p.getName());
        this.priceField.set(p.getPrice());
        this.quantityField.set(p.getQuantity());
    }
    public Products toProduct() {
        Products p = new Products();
        p.setName(this.nameField.getValue());
        p.setPrice(this.priceField.getValue());
        p.setQuantity(this.quantityField.getValue());
        return p;
    }

    public SimpleStringProperty nameFieldProperty() {
        return nameField;
    }

    public SimpleDoubleProperty priceFieldProperty() {
        return priceField;
    }

    public SimpleIntegerProperty quantityFieldProperty() {
        return quantityField;
    }
}
