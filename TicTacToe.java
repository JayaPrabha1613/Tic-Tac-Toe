import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    JButton[] buttons = new JButton[9];
    boolean xTurn = true; // X starts
    JButton resetButton;

    TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for game grid
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);

        // Reset button
        resetButton = new JButton("Restart");
        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetButton.addActionListener(e -> resetGame());
        add(resetButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            return; // ignore if already clicked
        }

        clicked.setText(xTurn ? "X" : "O");
        xTurn = !xTurn;

        String winner = checkWinner();
        if (!winner.equals("")) {
            JOptionPane.showMessageDialog(this, winner + " Wins!");
            resetGame();
        } else if (isDraw()) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
            resetGame();
        }
    }

    private String checkWinner() {
        int[][] combos = {
            {0,1,2},{3,4,5},{6,7,8}, // rows
            {0,3,6},{1,4,7},{2,5,8}, // columns
            {0,4,8},{2,4,6}           // diagonals
        };

        for (int[] c : combos) {
            if (!buttons[c[0]].getText().equals("") &&
                buttons[c[0]].getText().equals(buttons[c[1]].getText()) &&
                buttons[c[1]].getText().equals(buttons[c[2]].getText())) {
                return buttons[c[0]].getText();
            }
        }
        return "";
    }

    private boolean isDraw() {
        for (JButton b : buttons) {
            if (b.getText().equals("")) return false;
        }
        return true;
    }

    private void resetGame() {
        for (JButton b : buttons) {
            b.setText("");
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
