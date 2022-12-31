package othello;//package du jeu

import java.awt.*; //interface graphique
import java.util.ArrayList;//tableau d objet où nous pouvons modifier la taille
import java.util.Scanner;//permet à un l'utilisateur d'écrire du texte et au programme de lire ce texte

public class Actions { // class Action où sont regroupées toutes les actions que les joueurs font pendant la partie
    private final Board board;//création du tableu "Board" en private et de facon immuable

    public Actions(Board board) {//methode de classe
        this.board = board;//instance de tableau ("Board")
    }

    public void printBoard() {//methode qui affiche le tableau
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_WHITE_BACKGROUND = "\u001B[47m";

        System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "  ");//affiche le tableau
        for (int i = 0; i < board.getSize(); i++) {//boucle for qui rajoute 1 a chaque "taille" du tableau"
            System.out.print("  " + (char) ('A' + i));//affiche l'abscisse et l ordonné du coup
        }
        System.out.println("    " + ANSI_RESET);

        int count = 1; //compteur qui commence a 1
        for (Color[] row : board.getBoard()) {// boucle for each
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

    private void printScore() {//methode qui affcihe le score de  la partie 
        int black = 0;//en debut de partie, chaque joueur a  point
        int white = 0;
        for (Color[] row : board.getBoard()) {//boucle for each qui regarde toute les row dans le tableu(getter)
            for (Color color : row) {//boucle qui regarde la couleur de la rangée
                if (color == Color.BLACK) black++; // si c est noir alors noir++
                else if (color == Color.WHITE) white++;//sinon blanc ++
            }
        }
        System.out.println("Score: " + black + "(N) - " + white + "(B)"); //affiche le score
    }

    private Color getOppositeColor(Color player) {//methode qui prends la couleur oppose
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

            try (Scanner scanner = new Scanner(System.in)) {
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
            }
            color = getOppositeColor(color);
            board.setPossibleMoves(color);

            if (board.getPossibleMoves(Color.WHITE).size() == 0 && board.getPossibleMoves(Color.BLACK).size() == 0) break;
            else if (board.getMoves().size() == 0) {
                if (color == Color.BLACK) System.out.println("Aucun coup possible pour le joueur noir");
                else System.out.println("Aucun coup possible pour le joueur blanc");
                color = getOppositeColor(color);
                board.setPossibleMoves(color);
            }

            clearScreen();
        }


        printBoard();
        printScore();
        System.out.println("Fin de la partie");

    }
}
