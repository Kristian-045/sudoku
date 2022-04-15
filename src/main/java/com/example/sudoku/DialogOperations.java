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

}
