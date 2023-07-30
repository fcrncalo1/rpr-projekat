package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.UsersException;
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

    private final UsersManager usersManager = new UsersManager();
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
        fillGuests();
    }
    public void addButtonClick(ActionEvent actionEvent) {
        addupdateScene(null);
    }

    public void updateButtonClick(ActionEvent actionEvent) {
        Users selectedUser = (Users) usersTable.getSelectionModel().getSelectedItem();
        if(selectedUser == null) return;
        Integer uId = selectedUser.getId();
        addupdateScene(uId);
    }

    public void deleteButtonClick(ActionEvent actionEvent) {
        Users selectedUser = (Users) usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) return;
        else {
            Integer uId = selectedUser.getId();
            try {
                usersManager.delete(uId);
                fillGuests();
                usersTable.refresh();
            } catch (UsersException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
            }
        }
    }

    public void fillGuests() {
        try {
            usersTable.setItems(FXCollections.observableList(DaoFactory.usersDao().getAll()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addupdateScene(Integer uId){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addupdateuser.fxml"));
            loader.setController(new AddUpdateUserController(uId));
            Parent root = loader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.setOnHiding(event -> {fillGuests(); usersTable.refresh();});
            if(uId != null) {
                stage.setTitle("Edit a user");
            }
            else stage.setTitle("Add a user");
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
