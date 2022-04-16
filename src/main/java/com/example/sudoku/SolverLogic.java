package com.example.sudoku;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolverLogic {


    public String[][] csvToStringArray(File file) throws IOException {

        List<String[]> lists = new ArrayList<>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null){

            line = line.replaceAll("\\s+", "");

            String[] values = line.split(",");
            lists.add(values);
        }

        String[][] itemsArray = new String[lists.size()][];
        itemsArray = lists.toArray(itemsArray);

        return itemsArray;
    }

    public boolean isFileSizeValid(String[][] sudokuArray) throws IOException {

        if (sudokuArray.length != 9){
            return false;
        }

        for (String[] arr : sudokuArray){
            if (arr.length != 9){
                return false;
            }
        }

        return true;
    }

    public boolean areFileCharactersValid(String[][] sudokuArray){

        for (String[] arr : sudokuArray){
            for (String s : arr){
                if (!s.trim().matches("[1-9]") || s.trim().length() != 1){
                    return false;
                }
            }
        }

        return true;
    }


}
