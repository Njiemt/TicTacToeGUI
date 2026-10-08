import java.util.Scanner;

public class TicTacToe
{
    public static final int ROWS = 3;
    public static final int COLS = 3;
    public static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain)
        {
            clearBoard();
            int moveCnt = 0;
            String player = "X";
            boolean gameOver = false;

            IO.println("Welcome to Tic Tac Toe!");

            while (!gameOver)
            {
                int row;
                int col;

                do
                {
                    showBoard();
                    IO.println("Player " + player + "'s turn.");

                    row = SafeInput.getRangedInt(input, "Enter row (1-3)", 1, 3) - 1;
                    col = SafeInput.getRangedInt(input, "Enter col (1-3)", 1, 3) - 1;

                    if (!isValidMove(row, col))
                    {
                        IO.println("That cell is already taken! Try again.");
                    }
                } while (!isValidMove(row, col));

                board[row][col] = player;
                moveCnt++;

                if (moveCnt >= 5 && isWin(player))
                {
                    showBoard();
                    IO.println("Player " + player + " wins!");
                    gameOver = true;
                }
                else if (moveCnt == 9 || isTie())
                {
                    showBoard();
                    IO.println("It's a tie!");
                    gameOver = true;
                }
                else
                {
                    if (player.equals("X"))
                    {
                        player = "O";
                    }
                    else
                    {
                        player = "X";
                    }
                }
            }

            playAgain = SafeInput.getYNConfirm(input, "Do you want to play again?");
        }

        IO.println("Thanks for playing! Goodbye.");
        input.close();
    }

    public static void clearBoard()
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    public static void showBoard()
    {
        IO.println("");
        IO.println("  1   2   3");
        for (int row = 0; row < ROWS; row++)
        {
            IO.print((row + 1) + " ");
            for (int col = 0; col < COLS; col++)
            {
                IO.print(board[row][col]);
                if (col < COLS - 1)
                {
                    IO.print(" | ");
                }
            }
            IO.println("");
            if (row < ROWS - 1)
            {
                IO.println("  ---------");
            }
        }
        IO.println("");
    }

    public static boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if (row >= 0 && row < ROWS && col >= 0 && col < COLS
                && board[row][col].equals(" "))
        {
            retVal = true;
        }
        return retVal;
    }

    public static boolean isWin(String player)
    {
        if (isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }
        return false;
    }

    public static boolean isRowWin(String player)
    {
        for (int row = 0; row < ROWS; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isColWin(String player)
    {
        for (int col = 0; col < COLS; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isDiagnalWin(String player)
    {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
        {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
        {
            return true;
        }
        return false;
    }

    public static boolean isTie()
    {
        boolean row0blocked = false;
        boolean row1blocked = false;
        boolean row2blocked = false;
        boolean col0blocked = false;
        boolean col1blocked = false;
        boolean col2blocked = false;
        boolean diag1blocked = false;
        boolean diag2blocked = false;

        if ((board[0][0].equals("X") || board[0][1].equals("X") || board[0][2].equals("X")) &&
                (board[0][0].equals("O") || board[0][1].equals("O") || board[0][2].equals("O")))
        {
            row0blocked = true;
        }

        if ((board[1][0].equals("X") || board[1][1].equals("X") || board[1][2].equals("X")) &&
                (board[1][0].equals("O") || board[1][1].equals("O") || board[1][2].equals("O")))
        {
            row1blocked = true;
        }

        if ((board[2][0].equals("X") || board[2][1].equals("X") || board[2][2].equals("X")) &&
                (board[2][0].equals("O") || board[2][1].equals("O") || board[2][2].equals("O")))
        {
            row2blocked = true;
        }

        if ((board[0][0].equals("X") || board[1][0].equals("X") || board[2][0].equals("X")) &&
                (board[0][0].equals("O") || board[1][0].equals("O") || board[2][0].equals("O")))
        {
            col0blocked = true;
        }

        if ((board[0][1].equals("X") || board[1][1].equals("X") || board[2][1].equals("X")) &&
                (board[0][1].equals("O") || board[1][1].equals("O") || board[2][1].equals("O")))
        {
            col1blocked = true;
        }

        if ((board[0][2].equals("X") || board[1][2].equals("X") || board[2][2].equals("X")) &&
                (board[0][2].equals("O") || board[1][2].equals("O") || board[2][2].equals("O")))
        {
            col2blocked = true;
        }

        if ((board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X")) &&
                (board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O")))
        {
            diag1blocked = true;
        }

        if ((board[0][2].equals("X") || board[1][1].equals("X") || board[2][0].equals("X")) &&
                (board[0][2].equals("O") || board[1][1].equals("O") || board[2][0].equals("O")))
        {
            diag2blocked = true;
        }

        return row0blocked && row1blocked && row2blocked
                && col0blocked && col1blocked && col2blocked
                && diag1blocked && diag2blocked;
    }
}
