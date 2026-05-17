import javax.swing.*;
import java.awt.*;

public class ConfigDialog extends JDialog {

    private final JSpinner rowsSpinner = new JSpinner(new SpinnerNumberModel(4, 2, 10, 1));
    private final JSpinner colsSpinner = new JSpinner(new SpinnerNumberModel(4, 2, 10, 1));
    private final JSpinner timeoutSpinner = new JSpinner(new SpinnerNumberModel(60, 10, 300, 10));
    private GameConfig result;

    public ConfigDialog(Frame owner) {
        super(owner, "Memory Scramble - Game Setup", true);
        setLayout(new GridLayout(5, 2, 10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        add(new JLabel("Rows:"));           add(rowsSpinner);
        add(new JLabel("Columns:"));        add(colsSpinner);
        add(new JLabel("Timeout (sec):"));  add(timeoutSpinner);

        JLabel hint = new JLabel("Rows \u00D7 Columns must be even");
        hint.setForeground(Color.GRAY);
        add(hint);
        add(new JLabel());

        JButton startBtn = new JButton("Start Game");
        startBtn.addActionListener(e -> onStart());
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> { result = null; dispose(); });
        add(cancelBtn);
        add(startBtn);

        getRootPane().setDefaultButton(startBtn);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(owner);
    }

    private void onStart() {
        int rows = (int) rowsSpinner.getValue();
        int cols = (int) colsSpinner.getValue();
        int timeout = (int) timeoutSpinner.getValue();

        if ((rows * cols) % 2 != 0) {
            JOptionPane.showMessageDialog(this,
                "Rows \u00D7 Columns = " + (rows * cols) + " must be even.",
                "Invalid Board Size", JOptionPane.WARNING_MESSAGE);
            return;
        }

        result = new GameConfig(rows, cols, timeout);
        dispose();
    }

    public GameConfig showDialog() {
        setVisible(true);
        return result;
    }
}
