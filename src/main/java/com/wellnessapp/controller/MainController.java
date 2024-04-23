package com.wellnessapp.controller;

import com.wellnessapp.Main;
import com.wellnessapp.model.UserDAO;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.beans.binding.Bindings;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainController {
    @FXML    private Label welcomeText;
    @FXML    private Label Navigete_to_1;
    @FXML    private Label Navigete_to_2;
    @FXML    private Label Navigete_to_3;
    @FXML    private Label Navigete_to_4;
    @FXML    private ImageView imageView;
    @FXML    private Button contactUsButton;
    @FXML    private Label contactUsLabel;

    //Load image in Home GUI
    public void initialize(){
        Image image = new Image(getClass().getResourceAsStream("/com/wellnessapp/Computer.png"));
        imageView.setImage(image);  //Loading an image with ImageView

        initialiseDatabase();
    }

    private void initialiseDatabase() {
        UserDAO userDAO = new UserDAO();
        userDAO.close();
    }

    //Hello! button Click Setup
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    //ContactUs! button Click Setup
    @FXML       //Bottom of the Home GUI button operation
    protected void onContactusButtonClick() {
        contactUsLabel.setText("Team7.qut.edu.au");
        contactUsButton.setVisible(false);  //after click the button disappear
        contactUsLabel.setVisible(true);    //replace the button with setText message
    }

    //Navigation Button Setup
    @FXML
    protected void onHelloButtonClick1() {
        Navigete_to_1.setText("Take me");
    }
    @FXML
    protected void onHelloButtonClick2() {
        Navigete_to_2.setText("Take me");
    }
    @FXML
    protected void onHelloButtonClick3() {
        Navigete_to_3.setText("Take me");
    }
    @FXML
    protected void onHelloButtonClick4() {
        Navigete_to_4.setText("Take me");
    }


    private Stage moodStage;
    @FXML private Button moodLogPopupButton;
    private boolean isPopupShowing = false;
    @FXML
    protected void onMoodLogPopupButton() throws IOException {
        if (!isPopupShowing) {
            final int WIDTH = 300;
            final int HEIGHT = 400;
            Stage stage = new Stage();
            stage.setTitle("New Mood Entry");
            Pane pane = new Pane();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mood-popup.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
            scene.getStylesheets().add(Main.class.getResource("global.css").toExternalForm());
            stage.setScene(scene);

            // pass the stage to the controller for use in the controller.
            MoodPopupController controller = fxmlLoader.getController();
            controller.setStage(stage);

            stage.centerOnScreen();
            stage.initStyle(StageStyle.UNIFIED);

            stage.show();
            moodStage = stage;
            isPopupShowing = true;
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    //System.out.println("Hello World");
                    isPopupShowing = false;
                }
            });

            stage.setOnHidden(event -> {
                System.out.println("[mood] closed a popup");
                isPopupShowing = false;
            });

        } else {
            moodStage.centerOnScreen();
            moodStage.requestFocus();
        }
    }
}
