import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JPanel
{
    private final JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
    private final JButton[] buttons = new JButton[9];
    private String currentPlayer = "X";
    private int moveCount;
    private boolean gameOver;

    public TicTacToeFrame()
    {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        statusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(statusLabel, BorderLayout.NORTH);

        TicTacToe.clearBoard();

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        for (int i = 0; i < buttons.length; i++)
        {
            JButton button = new JButton("");
            button.setFont(new Font("Arial", Font.BOLD, 48));
            buttons[i] = button;
            boardPanel.add(button);

            final int row = i / 3;
            final int col = i % 3;
            button.addActionListener(e -> makeMove(row, col));
        }
        add(boardPanel, BorderLayout.CENTER);

        JPanel controls = new JPanel(new FlowLayout());
        JButton resetButton = new JButton("New Game");
        resetButton.addActionListener(e -> startNewGame());
        controls.add(resetButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> confirmQuit());
        controls.add(quitButton);
        add(controls, BorderLayout.SOUTH);

        updateStatus();
    }

    private void makeMove(int row, int col)
    {
        if (gameOver)
        {
            return;
        }

        if (!TicTacToe.isValidMove(row, col))
        {
            JOptionPane.showMessageDialog(
                    this,
                    "That square is already taken. Choose an empty square.",
                    "Illegal Move",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        TicTacToe.board[row][col] = currentPlayer;
        moveCount++;

        JButton button = buttons[row * 3 + col];
        button.setText(TicTacToe.board[row][col]);

        String endMessage = null;
        if (moveCount >= 5 && TicTacToe.isWin(currentPlayer))
        {
            gameOver = true;
            endMessage = "Player " + currentPlayer + " wins!";
        }
        else if (TicTacToe.isTie() || moveCount == 9)
        {
            gameOver = true;
            endMessage = "The game is a tie!";
        }
        else
        {
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }

        if (gameOver)
        {
            for (JButton square : buttons)
            {
                square.setEnabled(false);
            }
        }

        updateStatus();
        if (gameOver)
        {
            JOptionPane.showMessageDialog(
                    this,
                    endMessage,
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE
            );
            promptPlayAgain();
        }
    }

    private void updateStatus()
    {
        if (gameOver && TicTacToe.isWin(currentPlayer))
        {
            statusLabel.setText("Player " + currentPlayer + " wins!");
        }
        else if (gameOver)
        {
            statusLabel.setText("It's a tie!");
        }
        else
        {
            statusLabel.setText("Player " + currentPlayer + "'s turn");
        }
    }

    private void startNewGame()
    {
        if (!gameOver)
        {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "The current game is still in progress. Start a new game?",
                    "New Game",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (choice != JOptionPane.YES_OPTION)
            {
                return;
            }
        }
        resetGame();
    }

    private void promptPlayAgain()
    {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Would you like to play another game?",
                "Play Again",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (choice == JOptionPane.YES_OPTION)
        {
            resetGame();
        }
        else
        {
            showGoodbyeAndClose();
        }
    }

    private void resetGame()
    {
        TicTacToe.clearBoard();
        currentPlayer = "X";
        moveCount = 0;
        gameOver = false;

        for (JButton button : buttons)
        {
            button.setText("");
            button.setEnabled(true);
        }
        updateStatus();
    }

    public void confirmQuit()
    {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to quit?",
                "Quit Tic Tac Toe",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (choice == JOptionPane.YES_OPTION)
        {
            showGoodbyeAndClose();
        }
    }

    private void showGoodbyeAndClose()
    {
        JOptionPane.showMessageDialog(
                this,
                "Thanks for playing!",
                "Goodbye",
                JOptionPane.INFORMATION_MESSAGE
        );
        closeWindow();
    }

    private void closeWindow()
    {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null)
        {
            window.dispose();
        }
    }

}
