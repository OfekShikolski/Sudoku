import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
class SudokuSolver {
    public static boolean solve(Board board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.getValue(row, col) == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, row, col, num)) {
                            board.setValue(row, col, num);
                            if (solve(board)) {
                                return true;
                            }
                            board.setValue(row, col, 0);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isSafe(Board board, int row, int col, int num) {
        for (int x = 0; x < 9; x++) {
            if (board.getValue(row, x) == num || board.getValue(x, col) == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getValue(i + startRow, j + startCol) == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
