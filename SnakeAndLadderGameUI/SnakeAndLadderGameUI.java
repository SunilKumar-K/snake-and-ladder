import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SnakeAndLadderGameUI extends JFrame {
    private JPanel gamePanel;
    private JButton rollButton;
    private JLabel[] cells;
    private int[] board;
    private int currentPlayer;
    private int player1Position;
    private int player2Position;

    public SnakeAndLadderGameUI() {
        setTitle("Snake and Ladder Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(10, 10));
        add(gamePanel, BorderLayout.CENTER);

        cells = new JLabel[100];
        board = new int[100];
        currentPlayer = 1;
        player1Position = 0;
        player2Position = 0;

        for (int i = 0; i < 100; i++) {
            cells[i] = new JLabel(Integer.toString(i + 1));
            cells[i].setHorizontalAlignment(SwingConstants.CENTER);
            cells[i].setVerticalAlignment(SwingConstants.CENTER);
            gamePanel.add(cells[i]);
            board[i] = 0;
        }

        // Define snake and ladder positions
        board[3] = -2; // Snake
        board[6] = -5; // Snake
        board[16] = -10; // Snake
        board[20] = -7; // Snake
        board[31] = -14; // Snake
        board[35] = -18; // Snake
        board[45] = -28; // Snake
        board[47] = -30; // Snake
        board[50] = -35; // Snake
        board[63] = -42; // Snake
        board[61] = 18; // Ladder
        board[72] = 22; // Ladder
        board[42] = 20; // Ladder
        board[26] = 18; // Ladder
        board[39] = 20; // Ladder
        board[60] = 25; // Ladder
        board[82] = 23; // Ladder

        rollButton = new JButton("Roll Dice");
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
        add(rollButton, BorderLayout.SOUTH);

        updateUI();
    }

    private void rollDice() {
        Random random = new Random();
        int diceRoll = random.nextInt(6) + 1;

        int newPosition;
        if (currentPlayer == 1) {
            newPosition = player1Position + diceRoll;
        } else {
            newPosition = player2Position + diceRoll;
        }

        if (newPosition < 100) {
            if (board[newPosition] != 0) {
                newPosition += board[newPosition];
            }
            if (currentPlayer == 1) {
                player1Position = newPosition;
            } else {
                player2Position = newPosition;
            }

            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }

        updateUI();
        checkWin();
    }

    private void updateUI() {
        for (int i = 0; i < 100; i++) {
            cells[i].setBackground(Color.WHITE);
        }

        cells[player1Position].setBackground(Color.BLUE);
        cells[player2Position].setBackground(Color.RED);
    }

    private void checkWin() {
        if (player1Position >= 99) {
            JOptionPane.showMessageDialog(this, "Player 1 wins!");
            resetGame();
        } else if (player2Position >= 99) {
            JOptionPane.showMessageDialog(this, "Player 2 wins!");
            resetGame();
        }
    }

    private void resetGame() {
        player1Position = 0;
        player2Position = 0;
        currentPlayer = 1;
        updateUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeAndLadderGameUI game = new SnakeAndLadderGameUI();
            game.setVisible(true);
        });
    }
}
