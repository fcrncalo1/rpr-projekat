package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProductsManager;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.exceptions.ProductsException;
import ba.unsa.etf.rpr.model.ProductsModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

public class AddUpdateProductController {
    public TextField nameField;
    public TextField priceField;
    public TextField quantityField;
    public GridPane addupdateGridPane;
    private Integer pId;
    public AddUpdateProductController(Integer pId) {
        this.pId = pId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
    private final ProductsManager productsManager = new ProductsManager();
    private ProductsModel productsModel = new ProductsModel();

    @FXML
    public void initialize() {
        nameField.textProperty().bindBidirectional(productsModel.nameFieldProperty());
        Bindings.bindBidirectional(priceField.textProperty(),productsModel.priceFieldProperty(),new NumberStringConverter());
        quantityField.textProperty().bindBidirectional(productsModel.quantityFieldProperty(),new NumberStringConverter());
        if (pId != null) {
            try {
                productsModel.fromProduct(productsManager.getById(pId));
            } catch (ProductsException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.OK).show();
            }
        }
    }
    public void okClick(ActionEvent actionEvent) {
        Products p = productsModel.toProduct();
        try {
            if (pId != null) {
                p.setId(pId);
                productsManager.update(p);
                addupdateGridPane.getScene().getWindow().hide();
            } else {
                Boolean flag = true;
                if (nameField.textProperty().getValue() == null || nameField.textProperty().getValue().length() == 0) {
                    nameField.getStyleClass().removeAll("poljeIspravno");
                    nameField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (priceField.textProperty().getValue() == null || priceField.textProperty().getValue().length() == 0 || priceField.textProperty().getValue().equals(String.valueOf(0))) {
                    priceField.getStyleClass().removeAll("poljeIspravno");
                    priceField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (quantityField.textProperty().getValue() == null || quantityField.textProperty().getValue().length() == 0 || priceField.textProperty().getValue().equals(String.valueOf(0))) {
                    quantityField.getStyleClass().removeAll("poljeIspravno");
                    quantityField.getStyleClass().add("poljeNijeIspravno");
                    flag = false;
                }
                if (flag){
                    productsManager.add(p);
                    addupdateGridPane.getScene().getWindow().hide();
                }
            }

        } catch (ProductsException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void cancelClick(ActionEvent actionEvent) {
        addupdateGridPane.getScene().getWindow().hide();
    }
}
