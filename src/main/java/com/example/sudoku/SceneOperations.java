package com.example.sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneOperations {

    public Stage getStage(ActionEvent event){
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    public Scene getScene(MouseEvent event){
        return (Scene) ((Node) event.getSource()).getScene();
    }
    public Scene getSceneKeyEvent(KeyEvent event){
        return (Scene) ((Node) event.getSource()).getScene();
    }
    public Scene getSceneActionEvent(ActionEvent event){
        return (Scene) ((Node) event.getSource()).getScene();
    }

    public void setScene(ActionEvent event, String sceneName) throws IOException {

        Stage stage = getStage(event);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
        Scene scene = new Scene(loader.load(), 1024, 576);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toString());

        stage.setScene(scene);
        stage.show();
    }
    public void setSolvedScene(ActionEvent event, Parent root, SolverLogic solverLogic) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("solved-screen.fxml"));
        root = loader.load();

        SolvedScreenController solvedScreenController = loader.getController();
        solvedScreenController.setSolverLogic(solverLogic);

        Stage stage = getStage(event);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toString());
        stage.setScene(scene);
        stage.show();

        solvedScreenController.fillSolvedSudoku(scene);
    }

}


