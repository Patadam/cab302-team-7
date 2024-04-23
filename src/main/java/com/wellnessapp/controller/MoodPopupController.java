package com.wellnessapp.controller;

import com.wellnessapp.enums.MoodType;

import com.wellnessapp.model.MoodDAO;
import com.wellnessapp.model.MoodEntry;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MoodPopupController {
    @FXML
    private Button testBtn;

    /**
     * Starter method that runs on page mount
     */
    public void initialize(){
        System.out.println("started");
        initialiseDatabase();
    }

    private void initialiseDatabase() {
        MoodDAO moodDAO = new MoodDAO();
    }

    @FXML
    protected void onTestClicked() {
        // run code
        System.out.println("Btn Clicked");
        MoodEntry entry = new MoodEntry(MoodType.HAPPY);
    }

}
