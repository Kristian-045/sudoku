package com.example.sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class InputMethodScreen {

    private Parent root;
    private Stage stage;
    private Scene scene;

    private boolean manualSelected = false;
    private boolean importSelected = false;

    private final SceneOperations sceneOperations = new SceneOperations();
    private final DialogOperations dialogOperations = new DialogOperations();

    private final SolverLogic solverLogic = new SolverLogic();

    private void setManualSelected(boolean manualSelected){
        this.manualSelected = manualSelected;
    }

    private void setImportSelected(boolean importSelected){
        this.importSelected = importSelected;
    }

    @FXML
    Pane manualPane;

    @FXML
    Pane importPane;

    @FXML
    protected void selectManual(){
        if (!manualSelected){
            setManualSelected(true);
            manualPane.setStyle("-fx-background-color: #3C638D ; -fx-border-color: #3C638D ; -fx-border-width: 4 ;-fx-background-radius: 10 ;-fx-border-radius: 10 ;");
        }else{
            setManualSelected(false);
            manualPane.setStyle("-fx-background-color: #1E334B ; -fx-border-color: #3C638D ; -fx-border-width: 4 ;-fx-background-radius: 10 ;-fx-border-radius: 10 ;");
        }

        if (importSelected){
            setImportSelected(false);
            importPane.setStyle("-fx-background-color: #1E334B ; -fx-border-color: #3C638D ; -fx-border-width: 4 ;-fx-background-radius: 10 ;-fx-border-radius: 10 ;");
        }
    }

    @FXML
    protected void selectImport(){
        if (!importSelected){
            setImportSelected(true);
            importPane.setStyle("-fx-background-color: #3C638D ; -fx-border-color: #3C638D ; -fx-border-width: 4 ;-fx-background-radius: 10 ;-fx-border-radius: 10 ;");
        }else{
            setImportSelected(false);
            importPane.setStyle("-fx-background-color: #1E334B ; -fx-border-color: #3C638D ; -fx-border-width: 4 ;-fx-background-radius: 10 ;-fx-border-radius: 10 ;");
        }

        if (manualSelected){
            setManualSelected(false);
            manualPane.setStyle("-fx-background-color: #1E334B ; -fx-border-color: #3C638D ; -fx-border-width: 4 ;-fx-background-radius: 10 ;-fx-border-radius: 10 ;");
        }
    }

    @FXML
    protected void inputContinue(ActionEvent event) throws IOException {
        if (!importSelected && !manualSelected){
            dialogOperations.noInputAlert();
            return;
        }

        if (manualSelected){
            sceneOperations.setScene(event, "input-screen.fxml");
        }

        if (importSelected){
            Stage stage = sceneOperations.getStage(event);

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setTitle("Select a .csv file to import.");

            File file = fileChooser.showOpenDialog(stage);

            if (file != null){
                String[][] sudokuArray = solverLogic.csvToStringArray(file);

                if (!solverLogic.isFileSizeValid(sudokuArray) || !solverLogic.areFileCharactersValid(sudokuArray)){
                    dialogOperations.invalidFileFormatAlert();
                }else{

                    int[][] convertedSudoku = solverLogic.convertToIntArray(sudokuArray);

                    solverLogic.setSudoku(convertedSudoku);

                    if (solverLogic.isSudokuValid(convertedSudoku)){
                        if (solverLogic.solve(convertedSudoku)){
                            sceneOperations.setSolvedScene(event, root, solverLogic);
                        }else{
                            dialogOperations.invalidSudokuAlert();
                        }
                    }else{
                        dialogOperations.invalidSudokuAlert();
                    }
                }

            }else{
                dialogOperations.invalidFileInput();
            }

        }
    }

}
