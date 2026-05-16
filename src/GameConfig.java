public class GameConfig {
    private final int rows;
    private final int cols;
    private final int timeoutSeconds;

    public GameConfig(int rows, int cols, int timeoutSeconds) {
        if (rows < 2 || cols < 2) {
            throw new IllegalArgumentException("Rows and columns must be at least 2.");
        }
        if ((rows * cols) % 2 != 0) {
            throw new IllegalArgumentException("Total number of cells (rows * cols) must be even.");
        }
        if (timeoutSeconds < 1) {
            throw new IllegalArgumentException("Timeout must be at least 1 second.");
        }
        this.rows = rows;
        this.cols = cols;
        this.timeoutSeconds = timeoutSeconds;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public int getTotalCards() {
        return rows * cols;
    }

    public int getTotalPairs() {
        return getTotalCards() / 2;
    }
}
