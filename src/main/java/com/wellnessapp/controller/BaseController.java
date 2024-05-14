package com.wellnessapp.controller;

import com.wellnessapp.services.TrayService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BaseController implements IBaseController {
    @FXML private Pane base;
    private Stage stage;

    /**
     * Called on initialisation of any controller inheriting from BaseController.
     * Forces application to minimise to tray when closed without exiting from system tray.
     */
    public BaseController() {
        onMount(()->{
            getStage().setOnCloseRequest((event) -> TrayService.handleCloseWithSystemTray(getStage()));
        });
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
            throw new RuntimeException("You must set fx:id=\"base\" on all fxml referenced by " + this.getClass());
        }
    }
}
