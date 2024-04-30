package com.wellnessapp.controller;

import com.wellnessapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends BaseController {
    @FXML private Button TipButton;
    @FXML private CheckBox agreeCheckBox;
    @FXML private Label TakeText;
    @FXML private ImageView imageView;
    @FXML private Label contactUsLabel;

    private Stage popup = null;

    //--// Initialise //--//
    @FXML
    public void initialize(){
        Image image = new Image(getClass().getResourceAsStream("/Images/Computer.png"));
        imageView.setImage(image);
    }

    //--// Wellness Tips //--//
    @FXML
    protected void onContactusButtonClick() {
        contactUsLabel.setText("CAB302Team7@qut.edu.au");
    }
    @FXML
    protected void onTakeButtonClick() {
        TakeText.setText("Take Test!");
    }
    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) TipButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onAgreeCheckBoxClick() {
        boolean accepted = agreeCheckBox.isSelected();
        TipButton.setDisable(!accepted);
    }
    @FXML
    protected void onWellnessTipsButtonClick() throws IOException {
        Stage stage = (Stage) TipButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("wellness-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        scene.getStylesheets().add(Main.class.getResource("global.css").toExternalForm());
        stage.setScene(scene);
    }

    //--// Mood Tracking //--//
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
