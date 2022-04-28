package com.example.sudoku;

import javafx.scene.control.Alert;

public class DialogOperations {
    public void noInputAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Input Method Selected");
        alert.setHeaderText(null);
        alert.setContentText("You haven't selected any input method! Close the dialog and select one to continue.");
        alert.show();
    }

    public void invalidFileInput(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No File Selected");
        alert.setHeaderText(null);
        alert.setContentText("You haven't selected any input file! Close this dialog and select a .csv file.");
        alert.show();
    }

    public void invalidFileFormatAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid File Format");
        alert.setHeaderText(null);
        alert.setContentText("Your selected file's format isn't valid. It should be a .csv file only containing numbers 0-9 in a 9x9 format.");
        alert.show();
    }

    public void invalidSudokuAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Sudoku Entered");
        alert.setHeaderText(null);
        alert.setContentText("You've entered an invalid and unsolvable sudoku. Please close this dialog and enter a valid sudoku.");
        alert.show();
    }

}
