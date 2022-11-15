package othello;

public record Cell(int r, int c, int player) {
    public Cell(int r, int c) {
        this(r, c, 0);
    }
}
