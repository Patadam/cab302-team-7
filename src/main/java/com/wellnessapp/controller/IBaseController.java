package com.wellnessapp.controller;

import javafx.stage.Stage;

public interface IBaseController {
    Stage getStage();
    void onMount(Runnable callback);
    void initStage();
}
