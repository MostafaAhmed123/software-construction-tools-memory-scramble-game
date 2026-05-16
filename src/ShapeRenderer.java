import java.awt.*;

public class ShapeRenderer {

    private static final int SYMBOL_BASE = 0x2600;

    private static final Color[] COLORS = {
        new Color(231, 76, 60),   new Color(46, 204, 113),
        new Color(52, 152, 219),  new Color(155, 89, 182),
        new Color(241, 196, 15),  new Color(230, 126, 34),
        new Color(26, 188, 156),  new Color(233, 30, 99),
        new Color(0, 150, 136),   new Color(63, 81, 181),
        new Color(255, 87, 34),   new Color(139, 195, 74),
        new Color(121, 85, 72),   new Color(0, 188, 212),
        new Color(255, 152, 0),   new Color(103, 58, 183),
        new Color(244, 67, 54),   new Color(33, 150, 243),
    };

    public static void drawShape(Graphics2D g2, int shapeId, int x, int y, int size) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(COLORS[shapeId % COLORS.length]);
        g2.setFont(new Font("SansSerif", Font.PLAIN, (int) (size * 0.7)));

        String symbol = String.valueOf((char) (SYMBOL_BASE + shapeId));
        FontMetrics fm = g2.getFontMetrics();
        int tx = x + (size - fm.stringWidth(symbol)) / 2;
        int ty = y + (size + fm.getAscent() - fm.getDescent()) / 2;
        g2.drawString(symbol, tx, ty);
    }
}
