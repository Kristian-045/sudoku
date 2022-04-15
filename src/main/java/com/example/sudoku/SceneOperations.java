package com.example.sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneOperations {

    public Stage getStage(ActionEvent event){
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    public void setScene(ActionEvent event, String sceneName) throws IOException {

        Stage stage = getStage(event);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
        Scene scene = new Scene(loader.load(), 1024, 576);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toString());

        stage.setScene(scene);
        stage.show();
    }
}
