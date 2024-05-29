package com.wellnessapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * Responsible for managing the logic for the settings popup.
 */
public class SettingsPopupController {
    @FXML private CheckBox reminderCheckbox;
    @FXML private CheckBox yesCheckbox;
    @FXML private CheckBox noCheckbox;
    @FXML private TextField reminderIntervalField;

    @FXML private Button SupportResources;
    @FXML private Label welcomeText;

    @FXML
    private VBox mainContainer;

    private boolean isBlackBackground = false;

    private ReminderService reminderService;

    public SettingsPopupController() {
        this.reminderService = new ReminderService();
    }

    @FXML
    private void initialize() {
        try {
            // Load reminder settings from persistence and update UI accordingly
            boolean remindersEnabled = reminderService.areRemindersEnabled();
            int reminderInterval = reminderService.getReminderInterval();
            //reminderCheckbox.setSelected(remindersEnabled);
            //reminderIntervalField.setText(String.valueOf(reminderInterval));
        } catch (Exception e) {
            // Handle initialization errors
            e.printStackTrace();
        }
    }

    @FXML
    private void handleReminderCheckboxAction() {
        try {
            boolean remindersEnabled = reminderCheckbox.isSelected();
            reminderService.setRemindersEnabled(remindersEnabled);
        } catch (Exception e) {
            // Handle checkbox action errors
            e.printStackTrace();
        }
    }

    @FXML
    private void handleReminderIntervalChange() {
        try {
            String intervalText = reminderIntervalField.getText();
            int interval = Integer.parseInt(intervalText);
            reminderService.setReminderInterval(interval);
        } catch (NumberFormatException e) {
            // Handle invalid input
            System.err.println("Invalid reminder interval format: " + e.getMessage());
        } catch (Exception e) {
            // Handle interval change errors
            e.printStackTrace();
        }
    }

    @FXML
    private void handleYesCheckboxAction() {
        if (yesCheckbox.isSelected()) {
            showMessage("Great!");
        }
    }

    @FXML
    private void handleNoCheckboxAction() {
        if (noCheckbox.isSelected()) {
            showMessage("👀");
        }
    }


    @FXML
    private void openIssueReportForm() {
        // Implement functionality to open issue reporting form dialog or view
        showMessage("Sending The report issue to the support team");
    }


    @FXML
    private void onSupportusButtonClick() {
        String supportResourcesText = "1. Contact our support team at CAB302Team7@qut.edu.au\n" +
                "2. Visit our website for FAQs and documentation = WWW.team7qut.com\n" +
                "3. Follow us on social media for updates and announcements, Instagram Id= CAB302-Team7";
        welcomeText.setText(supportResourcesText);
        SupportResources.setVisible(false); // Set visibility to false
        showMessage("Showing support resources...");
    }

    @FXML
    private void changeBackgroundColor() {
        if (isBlackBackground) {
            mainContainer.setStyle("-fx-background-color: white;");
        } else {
            mainContainer.setStyle("-fx-background-color: black;");
        }
        isBlackBackground = !isBlackBackground;
    }



    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
