package com.example.sudoku;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class LandingScreen {


    private SceneOperations sceneOperations = new SceneOperations();

    @FXML
    protected void quitSolver(){
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void startSolver(ActionEvent event) throws IOException {
        sceneOperations.setScene(event, "input-method-screen.fxml");
    }
}
