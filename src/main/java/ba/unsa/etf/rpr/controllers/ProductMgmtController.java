package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class ProductMgmtController {
    public TableView productsTable;
    public TableColumn idColumn;
    public TableColumn nameColumn;
    public TableColumn priceColumn;
    public TableColumn quantityColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        try {
            productsTable.setItems(FXCollections.observableList(DaoFactory.productsDao().getAll()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addButtonClick(ActionEvent actionEvent) {
    }

    public void updateButtonClick(ActionEvent actionEvent) {
    }

    public void deleteButtonClick(ActionEvent actionEvent) {
    }
}
