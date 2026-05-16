import javax.swing.*;
import java.awt.*;

public class MemoryScrambleGame {

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Memory Scramble Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameConfig config = new ConfigDialog(frame).showDialog();
        if (config == null) System.exit(0);

        GameBoard board = new GameBoard(config);
        frame.setContentPane(board);
        frame.setSize(Math.max(config.getCols() * 110 + 30, 400), config.getRows() * 110 + 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}