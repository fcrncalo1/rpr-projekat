package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.UsersException;
import ba.unsa.etf.rpr.model.UsersModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * JavaFX class for creating and updating Users objects
 * @author Faris Crnčalo
 */
public class AddUpdateUserController {
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField emailField;
    public TextField cityField;
    public TextField addressField;
    public TextField phoneField;
    public TextField usernameField;
    public TextField passwordField;
    private final UsersManager usersManager = new UsersManager();
    public GridPane addupdateGridPane;
    private UsersModel usersModel = new UsersModel();
    private Integer uId;

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public AddUpdateUserController() {
        uId = null;
    }
    public AddUpdateUserController(Integer uId) {
        this.uId = uId;
    }

    @FXML
    public void initialize() {
        firstNameField.textProperty().bindBidirectional(usersModel.firstNameFieldProperty());
        lastNameField.textProperty().bindBidirectional(usersModel.lastNameFieldProperty());
        emailField.textProperty().bindBidirectional(usersModel.emailFieldProperty());
        cityField.textProperty().bindBidirectional(usersModel.cityFieldProperty());
        addressField.textProperty().bindBidirectional(usersModel.addressFieldProperty());
        phoneField.textProperty().bindBidirectional(usersModel.numberFieldProperty());
        usernameField.textProperty().bindBidirectional(usersModel.usernameFieldProperty());
        passwordField.textProperty().bindBidirectional(usersModel.passwordFieldProperty());

        if (uId != null) {
            try {
                usersModel.fromUser(usersManager.getById(uId));
            } catch (UsersException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.OK).show();
            }
        }
    }

    /**
     * OK button event handler
     * @param actionEvent
     */
    public void okClick(ActionEvent actionEvent) {
        Users u = usersModel.toUser();
        try {
            if (uId != null) {
                u.setId(uId);
                usersManager.update(u);
                addupdateGridPane.getScene().getWindow().hide();
            } else {
                Boolean flag = true;
                System.out.println(firstNameField.textProperty().getValue());
                if (firstNameField.textProperty().getValue().trim().length() == 0) {
                    firstNameField.getStyleClass().removeAll("poljeIspravno");
                    firstNameField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (lastNameField.textProperty().getValue().trim().length() == 0) {
                    lastNameField.getStyleClass().removeAll("poljeIspravno");
                    lastNameField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if(!EmailValidator.getInstance().isValid(emailField.textProperty().getValue())) {
                    emailField.getStyleClass().removeAll("poljeIspravno");
                    emailField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (cityField.textProperty().getValue().trim().length() == 0) {
                    cityField.getStyleClass().removeAll("poljeIspravno");
                    cityField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (addressField.textProperty().getValue().trim().length() == 0) {
                    addressField.getStyleClass().removeAll("poljeIspravno");
                    addressField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (phoneField.textProperty().getValue().trim().length() == 0) {
                    phoneField.getStyleClass().removeAll("poljeIspravno");
                    phoneField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (usernameField.textProperty().getValue().trim().length() == 0) {
                    usernameField.getStyleClass().removeAll("poljeIspravno");
                    usernameField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (passwordField.textProperty().getValue().trim().length() == 0) {
                    passwordField.getStyleClass().removeAll("poljeIspravno");
                    passwordField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (flag){
                    usersManager.add(u);
                    addupdateGridPane.getScene().getWindow().hide();
                }
            }

        } catch (UsersException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Cancel button event handler
     * @param actionEvent
     */
    public void cancelClick(ActionEvent actionEvent) {
        addupdateGridPane.getScene().getWindow().hide();
    }
}