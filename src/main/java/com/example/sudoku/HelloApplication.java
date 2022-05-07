package com.example.sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("landing-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 576);

        stage.setTitle("Sudoku solver");
        stage.getIcons().add(new Image("file:src/main/resources/com/example/sudoku/SolverIcon.png"));
            scene.getStylesheets().add(getClass().getResource("style.css").toString());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}