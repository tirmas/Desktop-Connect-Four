package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonReset extends JButton implements ActionListener {
    public ButtonReset(String text) {
        setName("ButtonReset");
        setText(text);
        setSize(40, 20);
        addActionListener(this);
        setEnabled(true);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setBackground(Color.green);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Cell[][] cellGrid = Cell.cellGrid;
        for (int row = 0; row < ConnectFour.GRID_ROWS; row++) {
            for (int col = 0; col < ConnectFour.GRID_COLUMNS; col++) {
                cellGrid[row][col].setText(" ");
                cellGrid[row][col].setBackground(ConnectFour.BASE_COLOR);
                cellGrid[row][col].isMarked = false;
                cellGrid[row][col].setEnabled(true);
            }
        }
        ConnectFour.setP1Turn(true);
    }
}
