package com.wellnessapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class SettingsPopupController {

    @FXML private CheckBox option1Checkbox;
    @FXML private CheckBox option2Checkbox;
    @FXML private CheckBox option3Checkbox;
    // Declare other checkboxes or settings as needed

    @FXML
    private void initialize() {
        // Add event handlers to manage checkbox selection
        option1Checkbox.setOnAction(event -> {
            if (option1Checkbox.isSelected()) {
                option2Checkbox.setSelected(false);
            }
        });

        option2Checkbox.setOnAction(event -> {
            if (option2Checkbox.isSelected()) {
                option1Checkbox.setSelected(false);
                option3Checkbox.setSelected(false);
            }
        });

        option3Checkbox.setOnAction(event -> {
            if (option3Checkbox.isSelected()) {
                option1Checkbox.setSelected(false);
                option2Checkbox.setSelected(false);
            }
        });
    }

    // Add event handlers or other methods as needed
}