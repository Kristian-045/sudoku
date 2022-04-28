package com.example.sudoku;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Arrays;

public class SolvedScreenController {

    private SolverLogic solverLogic;
    private final SceneOperations sceneOperations = new SceneOperations();

    @FXML
    private void continueSolver(ActionEvent event) throws IOException {

        sceneOperations.setScene(event, "landing-screen.fxml");
    }

    @FXML
    private void quitSolver(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }

    public void setSolverLogic(SolverLogic solverLogic) {
        this.solverLogic = solverLogic;
    }


   public void fillSolvedSudoku(Scene scene){
       int[][] solvedSudoku = solverLogic.getSudoku();

       for (int i = 0; i < solvedSudoku.length; i++){
           for (int j = 0; j < solvedSudoku.length; j++){

               int x =j%3;
               int y =i%3;
               int row = i/3;
               int col = j/3;

               String id = "#cell" + ((row * 3 + col) + 1) + ((x + y * 3) + 1);

               Pane cell = (Pane) scene.lookup(id);
               Text text = (Text) cell.getChildren().get(0);
               text.setText(String.valueOf(solvedSudoku[i][j]));

           }
       }
   }


}
