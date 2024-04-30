package com.wellnessapp.controller;

import com.wellnessapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class MainController extends BaseController {
    @FXML private Label welcomeText;
    @FXML private TextArea termsAndConditions;
    @FXML private CheckBox agreeCheckBox;
    @FXML private Button Home;

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

    // Handle popup for mood log
    private Stage popup = null;
    @FXML
    protected void onMoodLogPopupButton() throws IOException {
        if (popup == null || !popup.isShowing()) {
            final int WIDTH = 300;
            final int HEIGHT = 400;
            Stage stage = new Stage();
            stage.setTitle("New Mood Entry");
            Pane pane = new Pane();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mood-popup.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
            scene.getStylesheets().add(Main.class.getResource("global.css").toExternalForm());
            popup = stage;
            stage.setScene(scene);
            stage.show();
        } else {
            popup.centerOnScreen();
            popup.requestFocus();
        }
    }

    @FXML
    protected void onMoodChartButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mood-chart-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        scene.getStylesheets().add(Main.class.getResource("global.css").toExternalForm());
        getStage().setTitle("Mood Chart");
        getStage().setScene(scene);
        getStage().show();
        getStage().centerOnScreen();
    }
}
