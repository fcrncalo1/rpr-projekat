package ba.unsa.etf.rpr.model;

import ba.unsa.etf.rpr.domain.Users;
import javafx.beans.property.SimpleStringProperty;

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
    public SimpleStringProperty firstNameFieldProperty() { return firstNameField; }
    public SimpleStringProperty lastNameFieldProperty() { return lastNameField; }
    public SimpleStringProperty emailFieldProperty() { return emailField; }
    public SimpleStringProperty cityFieldProperty() { return cityField; }
    public SimpleStringProperty addressFieldProperty() { return addressField; }
    public SimpleStringProperty numberFieldProperty() { return numberField; }
    public SimpleStringProperty usernameFieldProperty() { return usernameField; }
    public SimpleStringProperty passwordFieldProperty() { return passwordField; }

}
