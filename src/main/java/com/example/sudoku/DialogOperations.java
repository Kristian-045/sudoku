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

    public void invalidFileFormatAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid File Format");
        alert.setHeaderText(null);
        alert.setContentText("Your selected file's format isn't valid. It should be a .csv file only containing numbers 1-9 in a 9x9 format.");
        alert.show();
    }

}
