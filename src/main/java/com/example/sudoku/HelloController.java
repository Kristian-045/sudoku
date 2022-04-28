package com.example.sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Arrays;

public class HelloController {

    private Parent root;
    private final DialogOperations dialogOperations = new DialogOperations();
    private final SolverLogic solverLogic = new SolverLogic();

    String lastCellId = "#cell11";
    @FXML
    AnchorPane anchorPane;
    private final SceneOperations sceneOperations = new SceneOperations();

    @FXML
    public void solveBtn(ActionEvent event) throws IOException {
        int[][] sudoka = new int[9][9];
        Scene scene = sceneOperations.getSceneActionEvent(event);
        String id = "#cell";

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                id = "#cell" + i + j;
                Pane cell = (Pane) scene.lookup(id);
//                System.out.println(id);
                Text text = (Text) cell.getChildren().get(0);
                String cislo = text.getText().trim();
                if (!cislo.equals("")) {
                    sudoka[i - 1][j - 1] = Integer.parseInt(text.getText());
                }else{
                    sudoka[i - 1][j - 1] = 0;
                }
            }
        }

        int [][] convertedSudoku = convertToRows(sudoka);

        System.out.println(Arrays.deepToString(convertedSudoku));

        solverLogic.setSudoku(convertedSudoku);

        if (solverLogic.solveSudoku(solverLogic.getSudoku())){

            sceneOperations.setSolvedScene(event, root, solverLogic);

        }else{
            dialogOperations.invalidSudokuAlert();
        }

    }

    public int[][] convertToRows(int[][] sudoka) {
        int [][] novaSudoka = new int[9][9];
        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j <9 ; j++) {
                int x =j%3;
                int y =j/3;
                int row = i/3;
                int col = i%3;
                novaSudoka[i][j]=sudoka[y+row*3][x+col*3];
            }
        }
        return novaSudoka;
    }

    public void initialize() {
//        Stage stage = (Stage) anchorPane.getScene().getWindow();
        anchorPane.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            int cislo = getValidNumber(event);
            if (cislo != 0) {
                String id = lastCellId;
                Scene scene = sceneOperations.getSceneKeyEvent(event);
                Pane cell = (Pane) scene.lookup(id);
                Text text = (Text) cell.getChildren().get(0);
                text.setText(String.valueOf(cislo));
            }

            System.out.println(event.getCode());
        });

    }

    @FXML
    protected void clear(ActionEvent event) {
        String id = lastCellId;
        Scene scene = sceneOperations.getSceneActionEvent(event);
        Pane cell = (Pane) scene.lookup(id);
        Text text = (Text) cell.getChildren().get(0);
        text.setText("");
    }

    @FXML
    protected void highlight(MouseEvent event) {

        String id = "#" + event.getSource().toString().split("id=")[1].substring(0, 6);
        Scene scene = sceneOperations.getScene(event);
        Pane lastCell = (Pane) scene.lookup(lastCellId);
        Pane cell = (Pane) scene.lookup(id);

        lastCell.setStyle("-fx-background-color: transparent ");
        cell.setStyle("-fx-background-color: #3C638D ");

        lastCellId = id;
    }

    protected int getValidNumber(KeyEvent event) {
        int cislo = 0;
        if (KeyCode.NUMPAD1 == event.getCode()) {
            cislo = 1;
        } else if (KeyCode.NUMPAD2 == event.getCode()) {
            cislo = 2;
        } else if (KeyCode.NUMPAD3 == event.getCode()) {
            cislo = 3;
        } else if (KeyCode.NUMPAD4 == event.getCode()) {
            cislo = 4;
        } else if (KeyCode.NUMPAD5 == event.getCode()) {
            cislo = 5;
        } else if (KeyCode.NUMPAD6 == event.getCode()) {
            cislo = 6;
        } else if (KeyCode.NUMPAD7 == event.getCode()) {
            cislo = 7;
        } else if (KeyCode.NUMPAD8 == event.getCode()) {
            cislo = 8;
        } else if (KeyCode.NUMPAD9 == event.getCode()) {
            cislo = 9;
        }
        return cislo;
    }

}