package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class GuestController {
    public BorderPane mainBorderPane;
    public Pane mainPane;
    public Label nameLabel;

    public void logoutClick(ActionEvent actionEvent) throws IOException {
        Node n = (Node) actionEvent.getSource();
        Stage stage1 = (Stage) n.getScene().getWindow();
        stage1.close();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();
        stage.setTitle("Login");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    public void dashboardClick(ActionEvent actionEvent) {
        mainBorderPane.setCenter(mainPane);
    }

    public void shopClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/shop.fxml"));
        GridPane gridPane = loader.load();
        ShopController shopController = loader.getController();
        shopController.setLoggedUser(nameLabel.getText());
        gridPane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        mainBorderPane.setCenter(gridPane);
    }

    public void yourOrdersClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/your_orders.fxml"));
        GridPane gridPane = loader.load();
        YourOrdersController yourOrdersController = loader.getController();
        yourOrdersController.setLoggedUser(nameLabel.getText());
        gridPane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        mainBorderPane.setCenter(gridPane);
    }
}