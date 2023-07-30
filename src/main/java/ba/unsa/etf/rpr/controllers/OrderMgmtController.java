package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class OrderMgmtController {
    public TableView ordersTable;

    public TableColumn idColumn;
    public TableColumn userIdColumn;
    public TableColumn orderDateColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        try {
            ordersTable.setItems(FXCollections.observableList(DaoFactory.ordersDao().getAll()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteButtonClick(ActionEvent actionEvent) {
    }
}
