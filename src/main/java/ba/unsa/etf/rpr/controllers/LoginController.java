package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Users;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {
    public TextField fieldUsername;
    public PasswordField fieldPassword;
    public Label wrongCredentialsLabel;

    @FXML
    public void initialize() {
        wrongCredentialsLabel.setVisible(false);
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
        Users user = null;
        if (fieldUsername.getText().isEmpty() || fieldPassword.getText().isEmpty()) {
            return;
        }
        user = DaoFactory.usersDao().getByUsername(fieldUsername.getText());
        if(user == null || !user.getPassword().equals(fieldPassword.getText())) {
            wrongCredentialsLabel.setVisible(true);
            return;
        }
        else {
            wrongCredentialsLabel.setVisible(false);
        }
        Node n = (Node) actionEvent.getSource();
        Stage stage1 = (Stage) n.getScene().getWindow();
        stage1.close();
        Stage stage = new Stage();
        Scene scene = null;
        if(user.getId() == 1){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
            Parent root = loader.load();
            scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/guest.fxml"));
            Parent root = loader.load();
            scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        }
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
