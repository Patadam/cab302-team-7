package com.example.wellnessapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {
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
        Image image = new Image(getClass().getResourceAsStream("/com/example/wellnessapp/Computer.png"));
        imageView.setImage(image);  //Loading an image with ImageView
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

}
