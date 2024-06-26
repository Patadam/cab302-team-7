package com.wellnessapp.controller;

import com.wellnessapp.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import com.wellnessapp.model.User;
import com.wellnessapp.model.UserDAO;

/**
 * Responsible for managing the logic for the signup view.
 */
public class SignUpController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Hyperlink loginLink;
    @FXML
    private Label signupPasswordError;

    UserDAO userDAO;

    @FXML
    public void initialize(){
        userDAO = new UserDAO();
    }


    @FXML
    private void handleSignUp(ActionEvent event) throws IOException {

        //this method should add the account to database and return user to the homepage
        // this method must also compare the passwordField and confirmPasswordField for them to be equal


        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (password.length() <8) {
            signupPasswordError.setText("Password must have 8 characters or more");
        }

        else if (password.equals(confirmPassword)) {
            User user = new User(email, password);
            userDAO.insert(user);
            goToLoginPage();
        }
        else {
            signupPasswordError.setText("Please make sure your passwords match");
        }
    }
    @FXML
    private void goToLoginPage() throws IOException {
        Stage stage = (Stage) loginLink.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        scene.getStylesheets().add(Main.class.getResource("global.css").toExternalForm());
        stage.setScene(scene);
    }
}