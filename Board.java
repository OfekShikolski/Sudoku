import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class Board {
    private Cell[][] cells;
    private int[][] solution;

    public Board() {
        cells = new Cell[9][9];
        solution = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new Cell();
            }
        }
    }

    public void generateSolution() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        for (int col = 0; col < 9; col++) {
            cells[0][col].setValue(numbers.get(col));
            cells[0][col].setFixed(true);
        }
        SudokuSolver.solve(this);
        saveSolution();
    }

    private void saveSolution() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                solution[row][col] = cells[row][col].getValue();
            }
        }
    }

    public void removeNumbers(int count) {
        Random rand = new Random();
        int removed = 0;
        while (removed < count) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (!cells[row][col].isFixed() && cells[row][col].getValue() != 0) {
                cells[row][col].setValue(0);
                removed++;
            }
        }
    }

    public boolean checkSolution() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (cells[row][col].getValue() != solution[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getValue(int row, int col) {
        return cells[row][col].getValue();
    }

    public void setValue(int row, int col, int value) {
        cells[row][col].setValue(value);
    }
}
