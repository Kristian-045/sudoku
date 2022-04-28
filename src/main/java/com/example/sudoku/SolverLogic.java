package com.example.sudoku;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolverLogic {

    final int sudokuLength = 9;

    public int[][] sudoku = new int[sudokuLength][sudokuLength];


    public SolverLogic(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public SolverLogic() {
    }

    public void setSudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public int[][] getSudoku(){
        return sudoku;
    }

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

    public int[][] convertToIntArray(String[][] stringArray){
        int[][] intArray = new int[sudokuLength][sudokuLength];

        for (int i = 0; i < sudokuLength; i++){
            for (int j = 0; j < sudokuLength; j++){
                intArray[i][j] = Integer.parseInt(stringArray[i][j]);
            }
        }

        return intArray;
    }

    public boolean isFileSizeValid(String[][] sudokuArray) throws IOException {

        if (sudokuArray.length != sudokuLength){
            return false;
        }

        for (String[] arr : sudokuArray){
            if (arr.length != sudokuLength){
                return false;
            }
        }

        return true;
    }

    public boolean areFileCharactersValid(String[][] sudokuArray){

        for (String[] arr : sudokuArray){
            for (String s : arr){
                if (!s.trim().matches("[0-9]") || s.trim().length() != 1){
                    return false;
                }
            }
        }

        return true;
    }

    protected boolean solveSudoku(int[][] sudoku) {
        for (int row = 0; row < sudokuLength; row++) {
            for (int col = 0; col < sudokuLength; col++) {
                if (sudoku[row][col] == 0) {
                    for (int numberToTry = 1; numberToTry <= sudokuLength; numberToTry++) {
                        if (isPlacementValid(sudoku, numberToTry, row, col)) {
                            sudoku[row][col] = numberToTry;

                            if (solveSudoku(sudoku)) {
                                return true;
                            }
                            else {
                                sudoku[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNumberInRow(int[][] sudoku, int number, int row) {
        for (int i = 0; i < sudokuLength; i++) {
            if (sudoku[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInCol(int[][] sudoku, int number, int col) {
        for (int i = 0; i < sudokuLength; i++) {
            if (sudoku[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInBox(int[][] sudoku, int number, int row, int col) {
        int localBoxRow = row - row % 3;
        int localBoxcol = col - col % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxcol; j < localBoxcol + 3; j++) {
                if (sudoku[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isPlacementValid(int[][] sudoku, int number, int row, int col) {
        return !isNumberInRow(sudoku, number, row) &&
                !isNumberInCol(sudoku, number, col) &&
                !isNumberInBox(sudoku, number, row, col);
    }

}
