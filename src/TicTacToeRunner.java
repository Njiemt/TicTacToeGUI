import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TicTacToeRunner
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tic Tac Toe");
            TicTacToeFrame panel = new TicTacToeFrame();

            frame.setContentPane(panel);
            frame.setSize(600, 500);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosing(WindowEvent e)
                {
                    panel.confirmQuit();
                }
            });
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
