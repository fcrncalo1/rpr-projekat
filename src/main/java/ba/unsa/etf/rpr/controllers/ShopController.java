package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProductsManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Products;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class ShopController {
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

    private final ProductsManager productsManager = new ProductsManager();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Products,String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Products,String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Products,String>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Products,String>("quantity"));
        scIdColumn.setCellValueFactory(new PropertyValueFactory<Products,String>("id"));
        scNameColumn.setCellValueFactory(new PropertyValueFactory<Products,String>("name"));
        scPriceColumn.setCellValueFactory(new PropertyValueFactory<Products,String>("price"));
        scQuantityColumn.setCellValueFactory(new PropertyValueFactory<Products,String>("quantity"));
        priceText.setText("0");
        try {
            productTable.setItems(FXCollections.observableList(DaoFactory.productsDao().getAll()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToCartClick(ActionEvent actionEvent) {
        Products product = (Products) productTable.getSelectionModel().getSelectedItem();
        if(quantityText.getText().trim().isEmpty() || product.getQuantity() < Integer.parseInt(quantityText.getText()) || Integer.parseInt(quantityText.getText()) == 0)
            return;
        product.setQuantity(Integer.parseInt(quantityText.getText()));
        shoppingCart.getItems().add(product);
        shoppingCart.refresh();
        priceText.setText(String.valueOf(Double.parseDouble(priceText.getText()) + Double.parseDouble(quantityText.getText())*product.getPrice()));
    }

    public void removeButtonClick(ActionEvent actionEvent) {
        Products selectedProduct = (Products) shoppingCart.getSelectionModel().getSelectedItem();
        shoppingCart.getItems().remove(shoppingCart.getSelectionModel().getSelectedItem());
        priceText.setText(String.valueOf(Double.parseDouble(priceText.getText()) - selectedProduct.getQuantity()*selectedProduct.getPrice()));
        shoppingCart.refresh();
    }
}
