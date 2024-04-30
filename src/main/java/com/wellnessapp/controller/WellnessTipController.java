package com.wellnessapp.controller;

import com.wellnessapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class WellnessTipController {
    @FXML private Label welcomeText;
    @FXML private TextArea termsAndConditions;
    @FXML private Button Home;

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
    protected void onCancelButtonClick() {
        Stage stage = (Stage) Home.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onContactusButtonClick() {
        welcomeText.setText("CAB302Team7@qut.edu.au");
    }

    @FXML
    protected void onHomeButtonClick() throws IOException {
        Stage stage = (Stage) Home.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        scene.getStylesheets().add(Main.class.getResource("global.css").toExternalForm());
        stage.setScene(scene);
    }

}
