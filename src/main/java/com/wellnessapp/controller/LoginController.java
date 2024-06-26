package com.wellnessapp.controller;

import com.wellnessapp.Main;
import com.wellnessapp.model.User;
import com.wellnessapp.model.UserDAO;
import com.wellnessapp.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Responsible for managing the logic for the login view.
 */
public class LoginController {

    UserDAO userDAO;
    public void initialize(){
        userDAO = new UserDAO();
    }
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Hyperlink signupLink;

    @FXML
    private Label loginError;



    @FXML
    private void handleLogin(ActionEvent event) throws IOException {

        String email = emailField.getText();
        String password = passwordField.getText();

        User user = new User(email,password);
        boolean isAuthenticated = userDAO.authenticateUser(user);

        if (isAuthenticated) {
            Stage stage = (Stage) signupLink.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
            scene.getStylesheets().add(Main.class.getResource("global.css").toExternalForm());
            stage.setScene(scene);
            AuthService.getInstance().setCurrentUser(user);
        }
        else {
            loginError.setText("Invalid credentials, Please enter correct credentials");
        }

    }

    @FXML
    private void goToSignUpPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) signupLink.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("signup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        scene.getStylesheets().add(Main.class.getResource("global.css").toExternalForm());
        stage.setScene(scene);
    }
}
