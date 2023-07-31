package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProductsManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.exceptions.ProductsException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class ProductMgmtController {
    public TableView productsTable;
    public TableColumn idColumn;
    public TableColumn nameColumn;
    public TableColumn priceColumn;
    public TableColumn quantityColumn;
    private final ProductsManager productsManager = new ProductsManager();
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        fillProducts();
    }

    public void addButtonClick(ActionEvent actionEvent) {
        addupdateScene(null);
    }

    public void updateButtonClick(ActionEvent actionEvent) {
        Products selectedProduct = (Products) productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) return;
        Integer pId = selectedProduct.getId();
        addupdateScene(pId);
    }

    public void deleteButtonClick(ActionEvent actionEvent) {
        Products selectedProduct = (Products) productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) return;
        else {
            Integer pId = selectedProduct.getId();
            try {
                productsManager.delete(pId);
                fillProducts();
                productsTable.refresh();
            } catch (ProductsException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
            }
        }
    }

    private void fillProducts() {
        try {
            productsTable.setItems(FXCollections.observableList(DaoFactory.productsDao().getAll()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void addupdateScene(Integer pId) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addupdateproduct.fxml"));
            loader.setController(new AddUpdateProductController(pId));
            Parent root = loader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.setOnHiding(event -> {
                fillProducts();
                productsTable.refresh();
            });
            if (pId != null) {
                stage.setTitle("Edit a product");
            } else stage.setTitle("Add a product");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
