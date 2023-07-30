package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Users;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class GuestMgmtController {
    public TableView usersTable;
    public TableColumn<Users,String> idColumn;
    public TableColumn<Users,String> firstNameColumn;
    public TableColumn<Users,String> lastNameColumn;
    public TableColumn<Users,String> emailColumn;
    public TableColumn<Users,String> cityColumn;
    public TableColumn<Users,String> addressColumn;
    public TableColumn<Users,String> numberColumn;
    public TableColumn<Users,String> usernameColumn;
    public TableColumn<Users,String> passwordColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        try {
            usersTable.setItems(FXCollections.observableList(DaoFactory.usersDao().getAll()));
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
