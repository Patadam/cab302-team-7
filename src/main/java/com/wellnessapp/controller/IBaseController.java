package com.wellnessapp.controller;

import javafx.stage.Stage;

/**
 * Interface for base controllers in the application.
 */
public interface IBaseController {
    Stage getStage();
    void onMount(Runnable callback);
    void initStage();
}
