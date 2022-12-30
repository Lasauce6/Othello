package othello;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Actions {
    private final Board board;

    public Actions(Board board) {
        this.board = board;
    }

    public void printBoard() {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_WHITE_BACKGROUND = "\u001B[47m";

        System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "  ");
        for (int i = 0; i < board.getSize(); i++) {
            System.out.print("  " + (char) ('A' + i));
        }
        System.out.println("    " + ANSI_RESET);

        int count = 1;
        for (Color[] row : board.getBoard()) {
            if (count < 10) System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + " " + count + "  ");
            else System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + count + "  ");
            for (Color color : row) {
                if (color == Color.BLACK) {
                    System.out.print(ANSI_BLACK + "●");
                } else if (color == Color.WHITE) {
                    String ANSI_WHITE = "\u001B[97m";
                    System.out.print(ANSI_WHITE + "●");
                } else if (color == Color.POSSIBLE_MOVE) {
                    String ANSI_GREEN = "\u001B[32m";
                    System.out.print(ANSI_GREEN + "●");
                } else {
                    System.out.print(" ");
                }
                System.out.print("  ");
            }
            if (count < 10) System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + count + " " + ANSI_RESET);
            else System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + count + ANSI_RESET);
            count++;
        }

        System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "  ");
        for (int i = 0; i < board.getSize(); i++) {
            System.out.print("  " + (char) ('A' + i));
        }
        System.out.println("    " + ANSI_RESET);
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

    private void printScore() {
        int black = 0;
        int white = 0;
        for (Color[] row : board.getBoard()) {
            for (Color color : row) {
                if (color == Color.BLACK) black++;
                else if (color == Color.WHITE) white++;
            }
        }
        System.out.println("Score: " + black + "(N) - " + white + "(B)");
    }

    private Color getOppositeColor(Color player) {
        if (player == Color.BLACK) {
            return Color.WHITE;
        } else if (player == Color.WHITE) {
            return Color.BLACK;
        } else {
            return Color.EMPTY;
        }
    }

    public void play(Color color) {
        play(color, false);
    }

    private void play(Color color, boolean loop) {
        board.setPossibleMoves(color);
        while (board.getMoves().size() > 0) {
            ArrayList<Point> possibleMoves = board.getMoves();
            if (possibleMoves.size() == 0) break;

            if (color == Color.BLACK) System.out.println("Tour du joueur noir");
            else System.out.println("Tour du joueur blanc");

            printBoard();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrer votre coup : ");
            String move = scanner.nextLine();
            while (move.length() < 2) {
                System.out.print("Coup impossible réessayez : ");
                move = scanner.nextLine();
            }
            int x = move.charAt(1) - '1';
            int y = move.charAt(0) - 'A';
            Point point = new Point(x, y);
            while (move.length() != 2 || x < 0 || x >= board.getSize() || y < 0 || y >= board.getSize() || !possibleMoves.contains(point)) {
                System.out.print("Coup impossible réessayez : ");
                move = scanner.nextLine();
                x = move.charAt(1) - '1';
                y = move.charAt(0) - 'A';
                point = new Point(x, y);
            }

            board.move(point.x, point.y, color);

            color = getOppositeColor(color);
            board.setPossibleMoves(color);

            if (board.getPossibleMoves(Color.WHITE).size() == 0 && board.getPossibleMoves(Color.BLACK).size() == 0) break;
            else if (board.getMoves().size() == 0) {
                System.out.println("Aucun coup possible pour le joueur " + color);
                color = getOppositeColor(color);
            }

            clearScreen();
        }
        printBoard();
        printScore();
        System.out.println("Fin de la partie");

    }
}
