import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard extends JPanel {
    
    private final Card[][] cards;
    private int remainingSeconds;
    private boolean inputLocked;
    private Card firstSelected;
    private Card secondSelected;
    private int matchedPairs;

    private final JLabel timerLabel = new JLabel();
    private Timer countdown;

    public GameBoard(GameConfig config) {
        this.cards = new Card[config.getRows()][config.getCols()];
        this.remainingSeconds = config.getTimeoutSeconds();

        setLayout(new BorderLayout());


        JPanel grid = new JPanel(new GridLayout(config.getRows(), config.getCols(), 6, 6));
        grid.setBackground(new Color(44, 62, 80));
        grid.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < config.getTotalPairs(); i++) { ids.add(i); ids.add(i); }
        Collections.shuffle(ids);

        int idx = 0;
        for (int r = 0; r < config.getRows(); r++) {
            for (int c = 0; c < config.getCols(); c++) {
                Card card = new Card(ids.get(idx++));
                cards[r][c] = card;
                card.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent e) {
                        if (inputLocked || card.isFaceUp() || card.isMatched()) return;

                        card.setFaceUp(true);

                        if (firstSelected == null) {
                            firstSelected = card;
                            return;
                        }
                        secondSelected = card;
                        inputLocked = true;

                        if (firstSelected.getShapeId() == secondSelected.getShapeId()) {
                            firstSelected.setMatched(true);
                            secondSelected.setMatched(true);
                            matchedPairs++;

                            resetSelection();

                            if (matchedPairs == config.getTotalPairs()) {
                                countdown.stop();
                                inputLocked = true;
                                promptReplay("Congratulations! You Win!");
                            }

                        } else {
                            Timer delay = new Timer(800, t -> {
                                firstSelected.setFaceUp(false);
                                secondSelected.setFaceUp(false);
                                resetSelection();
                            });
                            delay.setRepeats(false);
                            delay.start();
                        }
                    }
                });
                grid.add(card);
            }
        }

        add(grid, BorderLayout.CENTER);


        timerLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        timerLabel.setForeground(Color.BLACK);
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setText("Time: " + remainingSeconds + "s");
        add(timerLabel, BorderLayout.NORTH);

        countdown = new Timer(1000, e -> {
            remainingSeconds--;
            timerLabel.setText("Time: " + remainingSeconds + "s");

            if (remainingSeconds <= 0) {
                ((Timer) e.getSource()).stop();
                inputLocked = true;
                promptReplay("Time's up! Game Over!");
            }
        });
        countdown.start();
    }

    private void resetSelection() {
        firstSelected = null;
        secondSelected = null;
        inputLocked = false;
    }

    private void promptReplay(String message) {
        int choice = JOptionPane.showConfirmDialog(this,
            message + "\nWould you like to play again?",
            "Game Over", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            GameConfig newConfig = new ConfigDialog(frame).showDialog();
            if (newConfig != null) {
                GameBoard newBoard = new GameBoard(newConfig);
                frame.setContentPane(newBoard);
                frame.setSize(Math.max(newConfig.getCols() * 110 + 30, 400), newConfig.getRows() * 110 + 100);
                frame.revalidate();
            }
        }
    }
}
