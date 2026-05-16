import javax.swing.*;
import java.awt.*;

public class Card extends JPanel {

    private static final Color BACK_COLOR = new Color(44, 62, 80);
    private static final Color FACE_COLOR = new Color(236, 240, 241);
    private static final Color BORDER_COLOR = new Color(189, 195, 199);
    private static final Color MATCHED_TINT = new Color(46, 204, 113, 40);

    private final int shapeId;
    private boolean faceUp;
    private boolean matched;

    public Card(int shapeId) {
        this.shapeId = shapeId;
        setPreferredSize(new Dimension(100, 100));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public int getShapeId()  { return shapeId; }
    public boolean isFaceUp()  { return faceUp; }
    public boolean isMatched() { return matched; }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
        repaint();
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
        if (matched) setCursor(Cursor.getDefaultCursor());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth() - 6;
        int h = getHeight() - 6;
        int arc = 12;

        if (faceUp || matched) {
            g2.setColor(FACE_COLOR);
            g2.fillRoundRect(3, 3, w, h, arc, arc);
            if (matched) {
                g2.setColor(MATCHED_TINT);
                g2.fillRoundRect(3, 3, w, h, arc, arc);
            }
            ShapeRenderer.drawShape(g2, shapeId, 3 + 8, 3 + 8, Math.min(w, h) - 16);
        } else {
            g2.setColor(BACK_COLOR);
            g2.fillRoundRect(3, 3, w, h, arc, arc);
            g2.setColor(FACE_COLOR);
            g2.setFont(new Font("SansSerif", Font.BOLD, Math.min(w, h) / 3));
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString("?", 3 + (w - fm.stringWidth("?")) / 2,
                    3 + (h + fm.getAscent() - fm.getDescent()) / 2);
        }

        g2.setColor(BORDER_COLOR);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRoundRect(3, 3, w, h, arc, arc);
        g2.dispose();
    }
}
