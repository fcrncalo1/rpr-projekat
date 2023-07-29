package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.domain.Users;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class YourOrdersController {

    private String loggedUser;
    public TableView ordersTable;
    public TableColumn<Orders,String> orderDateColumn;
    public TableColumn<Orders,String> idColumn;

    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    }

    public void loadButtonClick(ActionEvent actionEvent) {
        if(ordersTable.getItems().isEmpty()) {
            Users user = DaoFactory.usersDao().getByUsername(loggedUser);
            ordersTable.setItems(FXCollections.observableList(DaoFactory.ordersDao().getByUserId(user.getId())));
            ordersTable.refresh();
        }
    }
}
