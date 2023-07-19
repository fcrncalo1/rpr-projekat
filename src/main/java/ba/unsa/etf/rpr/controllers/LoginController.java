package ba.unsa.etf.rpr.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {
    public TextField fieldUsername;
    public PasswordField fieldPassword;

    @FXML
    public void initialize() {
        fieldUsername.getStyleClass().add("poljeNijeIspravno");
        fieldUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (fieldUsername.getText().trim().isEmpty()) {
                    fieldUsername.getStyleClass().removeAll("poljeJeIspravno");
                    fieldUsername.getStyleClass().add("poljeNijeIspravno");
                } else {
                    fieldUsername.getStyleClass().removeAll("poljeNijeIspravno");
                    fieldUsername.getStyleClass().add("poljeJeIspravno");
                }
            }
        });
        fieldPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (fieldPassword.getText().trim().isEmpty()) {
                    fieldPassword.getStyleClass().removeAll("poljeJeIspravno");
                    fieldPassword.getStyleClass().add("poljeNijeIspravno");
                } else {
                    fieldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                    fieldPassword.getStyleClass().add("poljeJeIspravno");
                }
            }
        });
    }

    public void loginBttnClick(ActionEvent actionEvent) throws IOException {
        if (fieldUsername.getText().isEmpty() || fieldPassword.getText().isEmpty()) {
            return;
        }
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/window.fxml"));
        Parent root = loader.load();
        WindowController windowController = loader.getController();
        windowController.labela.setText(windowController.labela.getText() + fieldUsername.getText());
        stage.setTitle("Welcome");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }
}
