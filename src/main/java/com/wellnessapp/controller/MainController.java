package com.wellnessapp.controller;

import com.wellnessapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Label welcomeText;

    @FXML
    private TextArea termsAndConditions;

    @FXML
    private CheckBox agreeCheckBox;

    @FXML
    private Button Home;


    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) Home.getScene().getWindow();
        stage.close();
    }



    @FXML
    public void initialize() {
        termsAndConditions.setText("""
                Text,
                Turn your computer off every one hour.
                Go to sleep if you feel tired
                """);
    }

    public TextArea getTermsAndConditions() {
        return termsAndConditions;
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("CAB302Team7@qut.edu.au");
    }
    @FXML
    protected void onHomeButtonClick() throws IOException {
        Stage stage = (Stage) Home.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
    }
}
