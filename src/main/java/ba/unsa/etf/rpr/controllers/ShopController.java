package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProductsManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.OrderItems;
import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.domain.Products;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ShopController {

    private String loggedUser;
    public TableView productTable;
    public TableColumn<Products,String> idColumn;
    public TableColumn<Products,String> nameColumn;
    public TableColumn<Products,String> priceColumn;
    public TableColumn<Products,String > quantityColumn;
    public TableView shoppingCart;
    public TableColumn<Products,String> scIdColumn;
    public TableColumn<Products,String> scNameColumn;
    public TableColumn<Products,String> scPriceColumn;
    public TableColumn<Products,String> scQuantityColumn;
    public TextField quantityText;
    public TextField priceText;

    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        scIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        scNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        scQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceText.setText("0");
        fillProducts();
    }

    public void addToCartClick(ActionEvent actionEvent) {
        Products product = (Products) productTable.getSelectionModel().getSelectedItem();
        if(product == null || quantityText.getText().trim().isEmpty() || product.getQuantity() < Integer.parseInt(quantityText.getText()) || Integer.parseInt(quantityText.getText()) == 0)
            return;
        product.setQuantity(Integer.parseInt(quantityText.getText()));
        shoppingCart.getItems().add(product);
        shoppingCart.refresh();
        priceText.setText(String.valueOf(Double.parseDouble(priceText.getText()) + Double.parseDouble(quantityText.getText())*product.getPrice()));
    }

    public void removeButtonClick(ActionEvent actionEvent) {
        Products selectedProduct = (Products) shoppingCart.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) return;
        shoppingCart.getItems().remove(shoppingCart.getSelectionModel().getSelectedItem());
        priceText.setText(String.valueOf(Double.parseDouble(priceText.getText()) - selectedProduct.getQuantity()*selectedProduct.getPrice()));
        shoppingCart.refresh();
    }

    public void createOrderClick(ActionEvent actionEvent) {
        if (shoppingCart.getItems().isEmpty()) return;
        try {
            List<Products> products = shoppingCart.getItems();
            Orders order = new Orders();
            order.setUser(DaoFactory.usersDao().getByUsername(loggedUser));
            order.setOrderDate(Date.valueOf(LocalDate.now()));
            DaoFactory.ordersDao().add(order);
            for (Products p : products) {
                Products p1 = DaoFactory.productsDao().getById(p.getId());
                p1.setQuantity(p1.getQuantity() - p.getQuantity());
                OrderItems orderItems = new OrderItems();
                orderItems.setOrder(order);
                orderItems.setProduct(p);
                orderItems.setAmount(p.getQuantity());
                DaoFactory.orderItemsDao().add(orderItems);
                DaoFactory.productsDao().update(p1);
                System.out.println(p);
                System.out.println(p1);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Order created!", ButtonType.OK);
            alert.showAndWait();
            shoppingCart.getItems().clear();
            shoppingCart.refresh();
            priceText.setText("0");
            fillProducts();
            productTable.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void fillProducts() {
        try {
            productTable.setItems(FXCollections.observableList(DaoFactory.productsDao().getAll()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
