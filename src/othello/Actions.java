package othello;

public class Actions {
    private final Board board;

    public Actions(Board board) {
        this.board = board;
    }

    public void printBoard() {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_WHITE_BACKGROUND = "\u001B[47m";

        System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "    A  B  C  D  E  F  G  H    " + ANSI_RESET);
        int count = 1;
        for (Color[] row : board.getBoard()) {
            System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + " " + count + "  ");
            for (Color color : row) {
                if (color == Color.BLACK) {
                    System.out.print(ANSI_BLACK + "●");
                } else if (color == Color.WHITE) {
                    String ANSI_WHITE = "\u001B[97m";
                    System.out.print(ANSI_WHITE + "●");
                } else {
                    System.out.print(" ");
                }
                System.out.print("  ");
            }
            System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + count + " " + ANSI_RESET);
            count++;
        }
        System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "    A  B  C  D  E  F  G  H    " + ANSI_RESET);
    }

    public void clearScreen() { // ne fonctionne pas sur un IDE
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) Runtime.getRuntime().exec("cls");
            else Runtime.getRuntime().exec("clear");
        } catch (final Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
