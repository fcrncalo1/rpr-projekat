package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.OrdersManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.exceptions.OrdersException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class OrderMgmtController {
    public TableView ordersTable;

    public TableColumn idColumn;
    public TableColumn userIdColumn;
    public TableColumn orderDateColumn;

    private final OrdersManager ordersManager = new OrdersManager();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        fillOrders();
    }

    public void deleteButtonClick(ActionEvent actionEvent) {
        Orders order = (Orders) ordersTable.getSelectionModel().getSelectedItem();
        if(order == null) return;
        else {
            Integer oId = order.getId();
            try {
                ordersManager.delete(oId);
                fillOrders();
                ordersTable.refresh();
            } catch (OrdersException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
            }
        }
    }
    public void fillOrders() {
        try {
            ordersTable.setItems(FXCollections.observableList(DaoFactory.ordersDao().getAll()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
