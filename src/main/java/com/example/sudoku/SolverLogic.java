package com.example.sudoku;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SolverLogic {

    private static final int SUDOKU_SIZE = 9;
    private static final int NO_VALUE = 0;
    private static final int SUBSECTION_SIZE = 3;
    private static final int SUDOKU_START_INDEX = 0;

    public int[][] sudoku = new int[SUDOKU_SIZE][SUDOKU_SIZE];


    public SolverLogic(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public SolverLogic() {
    }

    public void setSudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    public String[][] csvToStringArray(File file) throws IOException {

        List<String[]> lists = new ArrayList<>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {

            line = line.replaceAll("\\s+", "");

            String[] values = line.split(",");
            lists.add(values);
        }

        String[][] itemsArray = new String[lists.size()][];
        itemsArray = lists.toArray(itemsArray);

        return itemsArray;
    }

    public int[][] convertToIntArray(String[][] stringArray) {
        int[][] intArray = new int[SUDOKU_SIZE][SUDOKU_SIZE];

        for (int i = 0; i < SUDOKU_SIZE; i++) {
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                intArray[i][j] = Integer.parseInt(stringArray[i][j]);
            }
        }

        return intArray;
    }

    public boolean isFileSizeValid(String[][] sudokuArray) throws IOException {

        if (sudokuArray.length != SUDOKU_SIZE) {
            return false;
        }

        for (String[] arr : sudokuArray) {
            if (arr.length != SUDOKU_SIZE) {
                return false;
            }
        }

        return true;
    }

    public boolean areFileCharactersValid(String[][] sudokuArray) {

        for (String[] arr : sudokuArray) {
            for (String s : arr) {
                if (!s.trim().matches("[0-9]") || s.trim().length() != 1) {
                    return false;
                }
            }
        }

        return true;
    }




    public boolean isBoxValid(int[][] sudoku, int row, int column) {
        boolean[] constraint = new boolean[SUDOKU_SIZE];
        int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

        int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(sudoku, r, constraint, c)) return false;
            }
        }
        return true;
    }

    public boolean isColumnValid(int[][] sudoku, int column) {
        boolean[] constraint = new boolean[SUDOKU_SIZE];
        return IntStream.range(0, SUDOKU_SIZE)
                .allMatch(row -> checkConstraint(sudoku, row, constraint, column));
    }

    public boolean isRowValid(int[][] sudoku, int row) {
        boolean[] constraint = new boolean[SUDOKU_SIZE];
        return IntStream.range(0, SUDOKU_SIZE)
                .allMatch(column -> checkConstraint(sudoku, row, constraint, column));
    }
    public boolean isValid(int[][] sudoku, int row, int column) {
        return (isRowValid(sudoku, row)
                && isColumnValid(sudoku, column)
                && isBoxValid(sudoku, row, column));
    }

    public boolean solve(int[][] sudoku) {
        for (int row = SUDOKU_START_INDEX; row < SUDOKU_SIZE; row++) {
            for (int column = SUDOKU_START_INDEX; column < SUDOKU_SIZE; column++) {
                if (sudoku[row][column] == NO_VALUE) {
                    for (int k = 1; k <= 9; k++) {
                        sudoku[row][column] = k;
                        if (isValid(sudoku, row, column) && solve(sudoku)) {
                            return true;
                        }
                        sudoku[row][column] = NO_VALUE;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSudokuValid(int[][] sudoku){
        for (int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col++){
                if (!isValid(sudoku, row, col)){
                    return false;
                }
            }
        }
        return true;
    }


    boolean checkConstraint(
            int[][] sudoku,
            int row,
            boolean[] constraint,
            int column) {
        if (sudoku[row][column] != NO_VALUE) {
            if (!constraint[sudoku[row][column] - 1]) {
                constraint[sudoku[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}