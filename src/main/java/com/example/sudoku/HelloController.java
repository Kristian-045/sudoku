package com.example.sudoku;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.util.Arrays;

public class HelloController {

    @FXML
    protected void highlight(MouseEvent event){
        String id =  event.getSource().toString().split("id=")[1].substring(0,6);


    }
}