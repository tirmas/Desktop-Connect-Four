package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton implements ActionListener {

    boolean isMarked = false;
    static Cell[][] cellGrid = new Cell[ConnectFour.GRID_ROWS][ConnectFour.GRID_COLUMNS];

    public Cell(String name) {
        setName("Button" + name);
        setText(" ");
        setFont(new Font("Arial", Font.BOLD, 20));
        setFocusPainted(false);
        addActionListener(this);
        setBackground(ConnectFour.BASE_COLOR);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int columnIndex = getName().charAt(6) - 65;
        int startingRow = findStartingRowInAColumn(columnIndex);

        Cell c = cellGrid[startingRow][columnIndex];
        c.isMarked = true;

        if (ConnectFour.isP1Turn()) {
            if (isColumnNotFull(cellGrid, columnIndex)) {
                c.setText(ConnectFour.PLAYER_1_MOVE);
                ConnectFour.setP1Turn(false);
                if (ConnectFour.isVertWin(cellGrid, columnIndex, ConnectFour.PLAYER_1_MOVE) ||
                        ConnectFour.isHorWin(cellGrid, startingRow, ConnectFour.PLAYER_1_MOVE) ||
                        ConnectFour.isPosDiagWin(cellGrid, ConnectFour.PLAYER_1_MOVE) ||
                        ConnectFour.isNegDiagWin(cellGrid, ConnectFour.PLAYER_1_MOVE)) {

                    stopGame(cellGrid);
                }
            }
        } else {
            if (isColumnNotFull(cellGrid, columnIndex)) {
                c.setText(ConnectFour.PLAYER_2_MOVE);
                ConnectFour.setP1Turn(true);
                if (ConnectFour.isVertWin(cellGrid, columnIndex, ConnectFour.PLAYER_2_MOVE) ||
                        ConnectFour.isHorWin(cellGrid, startingRow, ConnectFour.PLAYER_2_MOVE) ||
                        ConnectFour.isPosDiagWin(cellGrid, ConnectFour.PLAYER_2_MOVE) ||
                        ConnectFour.isNegDiagWin(cellGrid, ConnectFour.PLAYER_2_MOVE)) {

                    stopGame(cellGrid);
                }
            }
        }
    }

    private int findStartingRowInAColumn(int col) {
        int startingRow = 0;
        for (int i = ConnectFour.GRID_ROWS - 1; i >= 0; i--) {
            Cell c = cellGrid[i][col];
            if (c.isMarked) {
                startingRow = i + 1;
                if (startingRow > ConnectFour.GRID_ROWS - 1) {
                    startingRow = ConnectFour.GRID_ROWS - 1;
                }
                break;
            }
        }
        return startingRow;
    }

    private boolean isColumnNotFull(Cell[][] cellGrid, int column) {
        return !cellGrid[ConnectFour.GRID_ROWS - 1][column].getText().equals("X") &&
                !cellGrid[ConnectFour.GRID_ROWS - 1][column].getText().equals("O");
    }

    private void stopGame(Cell[][] cellGrid) {
        for (int row = 0; row < ConnectFour.GRID_ROWS; row++) {
            for (int col = 0; col < ConnectFour.GRID_COLUMNS; col++) {
                cellGrid[row][col].setEnabled(false);
            }
        }
    }
}
