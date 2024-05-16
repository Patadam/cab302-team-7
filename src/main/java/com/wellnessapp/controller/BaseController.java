package com.wellnessapp.controller;

import com.wellnessapp.Main;
import com.wellnessapp.annotations.ApplyStylesheet;
import com.wellnessapp.model.User;
import com.wellnessapp.services.AuthService;
import com.wellnessapp.services.TrayService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Objects;

public class BaseController implements IBaseController {
    @FXML private Pane base;
    @FXML private Label userEmail;
    private Stage stage;

    /**
     * Called on initialisation of any controller inheriting from BaseController.
     * Forces application to minimise to tray when closed without exiting from system tray.
     */
    public BaseController() {
        onMount(()->{
            getStage().setOnCloseRequest((event) -> TrayService.handleCloseWithSystemTray(getStage()));
            initStylesheet();
            initUser();
            TrayService.handleCloseWithSystemTray(getStage());
        });
    }

    private void initUser() {
        User currentUser = AuthService.getInstance().getCurrentUser();
        if (currentUser != null && userEmail != null) {
            userEmail.setText(currentUser.getEmail());
        }
    }

    private void initStylesheet() {
        stage.getScene().getStylesheets().add(
                Objects.requireNonNull(Main.class.getResource("global.css")).toExternalForm()
        );

        Class<?> controllerClass = getClass();
        ApplyStylesheet annotation = controllerClass.getAnnotation(ApplyStylesheet.class);
        if (annotation != null) {
            URL css = Main.class.getResource(annotation.value());
            if (css != null) {
                stage.getScene().getStylesheets().add(css.toExternalForm());
            }
        }

    }

    /**
     * Gets the stage instance by using required "base" fx:id attribute in all FXML.
     * @return The current stage connected to the controller.
     */
    @Override
    public Stage getStage() {
        try {
            if (this.stage == null) {
                this.stage = (Stage) base.getScene().getWindow();
            }
            return this.stage;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
            //throw new NoSuchElementException("You must set fx:id=\"base\" on all fxml referenced by " + this.getClass());
        }
    }

    /**
     * The onMount method forces code execution to wait until it can be executed on the JavaFX thread
     * when the page mount is completed. Automatically executes a {@link #getStage()} call before callback.
     * @param callback A call back method for any code to be run on the JavaFX thread when ready.
     *
     */
    public void onMount(Runnable callback) {
        Platform.runLater(()->{
            this.getStage();
            callback.run();
        });
    }

    /**
     * Initialises access to the stage by running {@code getStage()} when the FXML thread is mounted and ready.
     * The stage can be accessed through {@link #getStage()} method of the {@link BaseController}.
     * This method is unnecessary if using the {@link #onMount(Runnable)} method.
     * @see #getStage()
     */
    public void initStage() {
        Platform.runLater(this::getStage);
    }

}
