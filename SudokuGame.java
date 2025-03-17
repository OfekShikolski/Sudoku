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

public class SudokuGame {
    private JFrame frame;
    private JTextField[][] cells = new JTextField[9][9];
    private Board board;

    public SudokuGame() {
        frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 650); // הגדלתי מעט את גובה החלון כדי להוסיף את הכיתוב
        frame.setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField cell = new JTextField();
                cells[row][col] = cell;
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("Arial", Font.PLAIN, 24));

                final int currentRow = row;
                final int currentCol = col;

                cell.addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c) || c == '0' || cell.getText().length() == 1) {
                            e.consume();
                        }
                    }
                });
                gridPanel.add(cell);
            }
        }

        frame.add(gridPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton checkButton = new JButton("בדוק פתרון");
        JButton newGameButton = new JButton("משחק חדש");
        JButton easyButton = new JButton("קל");
        JButton mediumButton = new JButton("בינוני");
        JButton hardButton = new JButton("קשה");
        JButton showSolutionButton = new JButton("הצג פתרון");

        checkButton.addActionListener(e -> {
            if (board.checkSolution()) {
                JOptionPane.showMessageDialog(frame, "פתרון נכון!", "ניצחת", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "הפתרון לא נכון", "טעות", JOptionPane.ERROR_MESSAGE);
            }
        });

        newGameButton.addActionListener(e -> {
            board = new Board();
            board.generateSolution();
            board.removeNumbers(40);
            updateBoard();
        });

        easyButton.addActionListener(e -> {
            board = new Board();
            board.generateSolution();
            board.removeNumbers(30);
            updateBoard();
        });

        mediumButton.addActionListener(e -> {
            board = new Board();
            board.generateSolution();
            board.removeNumbers(40);
            updateBoard();
        });

        hardButton.addActionListener(e -> {
            board = new Board();
            board.generateSolution();
            board.removeNumbers(50);
            updateBoard();
        });

        showSolutionButton.addActionListener(e -> {
            board.generateSolution();
            updateBoard();
        });

        buttonPanel.add(checkButton);
        buttonPanel.add(newGameButton);
        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);
        buttonPanel.add(showSolutionButton);

        // הוספת תווית עם זכויות יוצרים
        JLabel copyrightLabel = new JLabel("All Rights Reserved @Ofek Shikolski", SwingConstants.CENTER);
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // יצירת פאנל תחתון
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(copyrightLabel, BorderLayout.SOUTH);

        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        board = new Board();
        board.generateSolution();
        board.removeNumbers(40);
        updateBoard();
    }

    private void updateBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int value = board.getValue(row, col);
                JTextField cell = cells[row][col];
                if (value != 0) {
                    cell.setText(String.valueOf(value));
                    cell.setEditable(false);
                } else {
                    cell.setText("");
                    cell.setEditable(true);
                }
            }
        }
    }

    public static void main(String[] args) {
        new SudokuGame();
    }
}
