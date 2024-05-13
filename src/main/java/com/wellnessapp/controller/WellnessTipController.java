package com.wellnessapp.controller;

import com.wellnessapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class WellnessTipController {
    @FXML private Label welcomeText;
    @FXML private TextArea wellnesstips;
    @FXML private Button Home;
    @FXML private Button contactUsButton;
    private List<String> wellnessTipsList = new ArrayList<>();

    @FXML
    public void initialize() {

        populateWellnessTipsList();                             // Populate the list of wellness tips
        Collections.shuffle(wellnessTipsList);                  // Shuffle the list to present tips randomly
        displayNextWellnessTip();                               // Display the first tip
//        wellnesstips.setText("""
//                "Hey there! Remember to blink regularly to keep your eyes moisturised and reduce strain."
//                "Feeling overwhelmed? Take a deep breath. Inhale for 4 seconds, hold for 7, exhale for 8. Repeat."
//                "It's break time! Stand up, stretch, and give your eyes a rest from the screen for a few minutes."
//                "Set a timer for your screen time today. Aim for balance with offline activities."
//                "Create a tech-free zone in your home for uninterrupted relaxation and quality time."
//                "Ready for bed? Dim your screen's brightness and avoid devices for better sleep quality."
//                "Unplug and unwind! Enjoy some screen-free activities like reading or going for a walk."
//                "Time for a quick walk! Take a break and get moving to boost your energy and mood."
//                "Pause for gratitude. Reflect on three things you're thankful for today."
//                "Stay hydrated! Take a sip of water and keep your body and mind refreshed."
//                """);
    }

    // Method to populate the list of wellness tips
    private void populateWellnessTipsList() {
        wellnessTipsList.add("Hey there! Remember to blink regularly to keep your eyes moisturised and reduce strain.");
        wellnessTipsList.add("Feeling overwhelmed? Take a deep breath. Inhale for 4 seconds, hold for 7, exhale for 8. Repeat.");
        wellnessTipsList.add("It's break time! Stand up, stretch, and give your eyes a rest from the screen for a few minutes.");
        wellnessTipsList.add("Set a timer for your screen time today. Aim for balance with offline activities.");
        wellnessTipsList.add("Create a tech-free zone in your home for uninterrupted relaxation and quality time.");
        wellnessTipsList.add("Ready for bed? Dim your screen's brightness and avoid devices for better sleep quality.");
        wellnessTipsList.add("Unplug and unwind! Enjoy some screen-free activities like reading or going for a walk.");
        wellnessTipsList.add("Time for a quick walk! Take a break and get moving to boost your energy and mood.");
        wellnessTipsList.add("Pause for gratitude. Reflect on three things you're thankful for today.");
        wellnessTipsList.add("Stay hydrated! Take a sip of water and keep your body and mind refreshed.");
        // Add more tips as needed
    }

    // Method to display the next wellness tip
    private void displayNextWellnessTip() {
        if (!wellnessTipsList.isEmpty()) {
            String nextTip = wellnessTipsList.remove(0);
            wellnesstips.setText(nextTip);
        } else {
            wellnesstips.setText("No more wellness tips available.");
        }
    }

    public TextArea getWellnessTips() {
        return wellnesstips;
    }

    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) Home.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onContactusButtonClick() {
        welcomeText.setText("CAB302Team7@qut.edu.au");
        contactUsButton.setVisible(false); // Set visibility to false
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
