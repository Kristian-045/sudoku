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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 576);

        stage.setTitle("Sudoku solver");
//        ked budeme mat nejaku ikonku tak ju tam mozme dat
//        stage.getIcons().add(new Image("file:src/main/resources/app/mediaplayer/7215-youtube-playbutton.png"));
            scene.getStylesheets().add(getClass().getResource("style.css").toString());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}