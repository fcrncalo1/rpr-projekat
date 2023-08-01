package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX class for admin dashboard navigation
 * @author Faris Crnčalo
 */
public class AdminController {
    public Label nameLabel;
    public Pane mainPane;
    public BorderPane mainBorderPane;

    /**
     * Logout button event handler
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * Dashboard button event handler
     * @param actionEvent
     */
    public void dashboardClick(ActionEvent actionEvent) {
        mainBorderPane.setCenter(mainPane);
    }

    /**
     * Guest management button event handler
     * @param actionEvent
     * @throws IOException
     */
    public void guestManagementClick(ActionEvent actionEvent) throws IOException {
        GridPane gridPane = FXMLLoader.load(getClass().getResource("/fxml/guestmgmt.fxml"));
        gridPane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        mainBorderPane.setCenter(gridPane);
    }

    /**
     * Product management button event handler
     * @param actionEvent
     * @throws IOException
     */
    public void productManagementClick(ActionEvent actionEvent) throws IOException {
        GridPane gridPane = FXMLLoader.load(getClass().getResource("/fxml/productmgmt.fxml"));
        gridPane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        mainBorderPane.setCenter(gridPane);
    }

    /**
     * Order management button event handler
     * @param actionEvent
     * @throws IOException
     */
    public void orderManagementClick(ActionEvent actionEvent) throws IOException {
        GridPane gridPane = FXMLLoader.load(getClass().getResource("/fxml/ordermgmt.fxml"));
        gridPane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        mainBorderPane.setCenter(gridPane);
    }
}
