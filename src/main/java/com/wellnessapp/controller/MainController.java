package com.wellnessapp.controller;

import com.wellnessapp.Main;
import com.wellnessapp.model.hydration.HydrationEntry;
import com.wellnessapp.model.hydration.HydrationManager;
import com.wellnessapp.workers.HydrationWorker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController extends BaseController {
    @FXML private Button TipButton;
    @FXML private CheckBox agreeCheckBox;
    @FXML private Label TakeText;
    @FXML private ImageView imageView;
    @FXML private Label contactUsLabel;
    @FXML private Button hydrationButton;
    @FXML private Button contactUsButton;

    private Stage popup = null;
    private Stage settingsPopup = null;

    public MainController() {
        new HydrationWorker().startBackgroundExecutor();
    }

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
        contactUsButton.setVisible(false);
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

    // Hydration
    @FXML
    protected void onHydrationButton() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("New hydration entry");
        ButtonType confirm = new ButtonType("Confirm", ButtonBar.ButtonData.YES);
        ButtonType close = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.setContentText("Are you sure you want to record a new water entry?");
        dialog.getDialogPane().getButtonTypes().addAll(confirm, close);
        dialog.showAndWait().ifPresent(response -> {
            if (response == confirm) {
                HydrationManager manager = new HydrationManager();
                manager.add(new HydrationEntry());
            }
        });
    }

    // Reminders
    @FXML
    protected void onReminderButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Reminder-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        scene.getStylesheets().add(Main.class.getResource("global.css").toExternalForm());
        getStage().setTitle("Mood Chart");
        getStage().setScene(scene);
        getStage().show();
        getStage().centerOnScreen();
    }
    @FXML
    protected void onSettingsButtonClick() throws IOException {
        if (settingsPopup == null || !settingsPopup.isShowing()) {
            // Load the FXML file for the settings popup
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("settings-popup.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Create a new stage for the popup
            settingsPopup = new Stage();
            settingsPopup.setScene(scene);
            settingsPopup.setTitle("Settings");

            // Set the modality of the popup to APPLICATION_MODAL
            settingsPopup.initModality(Modality.APPLICATION_MODAL);

            // Show the popup
            settingsPopup.show();
        } else {
            // If the popup is already showing, bring it to the front
            settingsPopup.toFront();
        }
    }

}
