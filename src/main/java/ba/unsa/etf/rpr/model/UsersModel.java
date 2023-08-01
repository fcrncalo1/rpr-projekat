package ba.unsa.etf.rpr.model;

import ba.unsa.etf.rpr.domain.Users;
import javafx.beans.property.SimpleStringProperty;

/**
 * Model class for 2 way data binding
 * @author Faris Crnčalo
 */
public class UsersModel {
    public SimpleStringProperty firstNameField = new SimpleStringProperty("");
    public SimpleStringProperty  lastNameField = new SimpleStringProperty("");
    public SimpleStringProperty  emailField = new SimpleStringProperty("");
    public SimpleStringProperty  cityField = new SimpleStringProperty("");

    public SimpleStringProperty addressField = new SimpleStringProperty("");
    public SimpleStringProperty  numberField = new SimpleStringProperty("");
    public SimpleStringProperty  usernameField = new SimpleStringProperty("");
    public SimpleStringProperty  passwordField = new SimpleStringProperty("");

    public void fromUser(Users u) {
        this.firstNameField.set(u.getFirstName());
        this.lastNameField.set(u.getLastName());
        this.cityField.set(u.getCity());
        this.addressField.set(u.getAddress());
        this.emailField.set(u.getEmail());
        this.usernameField.set(u.getUsername());
        this.passwordField.set(u.getPassword());
        this.numberField.set(u.getMobileNumber());
    }
    public Users toUser(){
        Users u = new Users();
        u.setFirstName(this.firstNameField.getValue());
        u.setLastName(this.lastNameField.getValue());
        u.setEmail(this.emailField.getValue());
        u.setCity(this.cityField.getValue());
        u.setAddress(this.addressField.getValue());
        u.setMobileNumber(this.numberField.getValue());
        u.setUsername(this.usernameField.getValue());
        u.setPassword(this.passwordField.getValue());
        return u;
    }

    public String getFirstNameField() {
        return firstNameField.get();
    }

    public SimpleStringProperty firstNameFieldProperty() {
        return firstNameField;
    }

    public void setFirstNameField(String firstNameField) {
        this.firstNameField.set(firstNameField);
    }

    public String getLastNameField() {
        return lastNameField.get();
    }

    public SimpleStringProperty lastNameFieldProperty() {
        return lastNameField;
    }

    public void setLastNameField(String lastNameField) {
        this.lastNameField.set(lastNameField);
    }

    public String getEmailField() {
        return emailField.get();
    }

    public SimpleStringProperty emailFieldProperty() {
        return emailField;
    }

    public void setEmailField(String emailField) {
        this.emailField.set(emailField);
    }

    public String getCityField() {
        return cityField.get();
    }

    public SimpleStringProperty cityFieldProperty() {
        return cityField;
    }

    public void setCityField(String cityField) {
        this.cityField.set(cityField);
    }

    public String getAddressField() {
        return addressField.get();
    }

    public SimpleStringProperty addressFieldProperty() {
        return addressField;
    }

    public void setAddressField(String addressField) {
        this.addressField.set(addressField);
    }

    public String getNumberField() {
        return numberField.get();
    }

    public SimpleStringProperty numberFieldProperty() {
        return numberField;
    }

    public void setNumberField(String numberField) {
        this.numberField.set(numberField);
    }

    public String getUsernameField() {
        return usernameField.get();
    }

    public SimpleStringProperty usernameFieldProperty() {
        return usernameField;
    }

    public void setUsernameField(String usernameField) {
        this.usernameField.set(usernameField);
    }

    public String getPasswordField() {
        return passwordField.get();
    }

    public SimpleStringProperty passwordFieldProperty() {
        return passwordField;
    }

    public void setPasswordField(String passwordField) {
        this.passwordField.set(passwordField);
    }
}
