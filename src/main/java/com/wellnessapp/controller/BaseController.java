package com.wellnessapp.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BaseController implements IBaseController {
    @FXML private VBox base;
    private Stage stage;

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
