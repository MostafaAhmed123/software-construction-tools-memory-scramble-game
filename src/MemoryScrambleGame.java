import javax.swing.*;
import java.awt.*;

public class MemoryScrambleGame {

    public static void main(String[] args) {
        
        GameConfig config = new GameConfig(4, 4, 60); 

        
        JFrame frame = new JFrame("Memory Scramble Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(config.getRows(), config.getCols(), 10, 10));
        frame.setSize(500, 500);

        
        for (int i = 0; i < config.getTotalCards(); i++) {
            int shapeId = i % config.getTotalPairs();
            Card card = new Card(shapeId);
            frame.add(card);
        }

        frame.setVisible(true);
    }
}