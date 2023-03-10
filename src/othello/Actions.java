package othello;

import ai.MinMax;
import ai.Naif;
import ai.State;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe Actions
 */
public class Actions {
    private final Board board; // Le plateau de jeu

    /**
     * Constructeur de la classe Actions
     * @param board le plateau de jeu
     */
    public Actions(Board board) {
        this.board = board;
    }

    /**
     * Affiche le plateau de jeu
     */
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
   
    /**
     * Efface la Console
     */
    public void clearScreen() { // Ne fonctionne pas sur un IDE
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) Runtime.getRuntime().exec("cls");
            else Runtime.getRuntime().exec("clear");
        } catch (final Exception e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Affiche le score
     */
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

    /**
     * Lance une partie de 1v1
     * @param color le joueur qui commence
     */
    public void play(Color color) {
        board.setPossibleMoves(color);
        boolean stop = false;
        while (board.getMoves().size() > 0) {
            ArrayList<Point> possibleMoves = board.getMoves();
            if (possibleMoves.size() == 0) break;

            if (color == Color.BLACK) System.out.println("Tour du joueur noir");
            else System.out.println("Tour du joueur blanc");

            printBoard();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrer votre coup (ou exit/save) : ");
            String move = scanner.nextLine();
            while (move.length() < 2) {
                System.out.print("Coup impossible réessayez : ");
                move = scanner.nextLine();
            }
            int x = move.charAt(1) - '1';
            int y = move.charAt(0) - 'A';
            Point point = new Point(x, y);

            while (move.length() != 2 || x < 0 || x >= board.getSize() || y < 0 || y >= board.getSize() || !possibleMoves.contains(point)) {
                switch (move) {
                    case "exit" -> { // Retourne au menu
                        stop = true;
                    }
                    case "save" -> { // Sauvegarde la partie
                        System.out.print("Entrer le nom du fichier : ");
                        String fileName = scanner.nextLine(); // On demande le nom du fichier
                        System.out.println("Sauvegarde en cours...");
                        save(fileName); // On sauvegarde la partie
                        System.out.println("Sauvegarde terminée");
                        stop = true;
                    }
                    default -> { // Si le coup n'est pas valide
                        System.out.print("Coup impossible réessayez : ");
                        move = scanner.nextLine();
                        x = move.charAt(1) - '1';
                        y = move.charAt(0) - 'A';
                        point = new Point(x, y);
                    }
                }
                if (stop) break;
            }

            if (stop) break; // Si on a choisi de retourner au menu, on sort de la boucle

            board.move(point.x, point.y, color);

            color = color.getOpponent();
            board.setPossibleMoves(color);

            if (board.getPossibleMoves(Color.WHITE).size() == 0 && board.getPossibleMoves(Color.BLACK).size() == 0) break;
            else if (board.getMoves().size() == 0) {
                clearScreen();
                if (color == Color.BLACK) System.out.println("Aucun coup possible pour le joueur noir");
                else System.out.println("Aucun coup possible pour le joueur blanc");
                color = color.getOpponent();
                board.setCurrentPlayer(color);
                board.setPossibleMoves(color);
            } else clearScreen();
        }
        if (!stop) {
            clearScreen();
            printBoard();
            printScore();
        }
        System.out.println("Fin de la partie");

    }

    /**
     * Joue une partie contre l'IA naive
     * @param ia l'IA
     */
    public void play(Naif ia) {
        Color color = Color.BLACK;
        board.setPossibleMoves(color);
        boolean stop = false;
        while (board.getMoves().size() > 0) {
            if (color == ia.getColor()) {
                System.out.println("Tour de l'IA");
                printBoard();
                System.out.println("Calcul en cours...");
                Point point = ia.getBestMove();
                System.out.println("Coup joué : " + (char) (point.y + 'A') + (point.x + 1));
                board.move(point.x, point.y, color);
                System.out.print("Entrer pour continuer");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
            }
            else {
                ArrayList<Point> possibleMoves = board.getMoves();
                System.out.println("Tour du joueur");
                printBoard();

                Scanner scanner = new Scanner(System.in);
                System.out.print("Entrer votre coup (ou exit/save) : ");
                String move = scanner.nextLine();
                while (move.length() < 2) {
                    System.out.print("Coup impossible réessayez : ");
                    move = scanner.nextLine();
                }
                int x = move.charAt(1) - '1';
                int y = move.charAt(0) - 'A';
                Point point = new Point(x, y);

                while (move.length() != 2 || x < 0 || x >= board.getSize() || y < 0 || y >= board.getSize() || !possibleMoves.contains(point)) {
                    switch (move) {
                        case "exit" -> { // Retourne au menu
                            stop = true;
                        }
                        case "save" -> { // Sauvegarde la partie
                            System.out.print("Entrer le nom du fichier : ");
                            String fileName = scanner.nextLine(); // On demande le nom du fichier
                            System.out.println("Sauvegarde en cours...");
                            save(fileName); // On sauvegarde la partie
                            System.out.println("Sauvegarde terminée");
                            stop = true;
                        }
                        default -> { // Si le coup n'est pas valide
                            System.out.print("Coup impossible réessayez : ");
                            move = scanner.nextLine();
                            x = move.charAt(1) - '1';
                            y = move.charAt(0) - 'A';
                            point = new Point(x, y);
                        }
                    }
                    if (stop) break;
                }

                if (stop) break; // Si on a choisi de retourner au menu, on sort de la boucle

                board.move(point.x, point.y, color);
            }

            color = color.getOpponent();
            board.setPossibleMoves(color);

            if (board.getPossibleMoves(Color.WHITE).size() == 0 && board.getPossibleMoves(Color.BLACK).size() == 0) break;
            else if (board.getMoves().size() == 0) {
                clearScreen();
                if (color == Color.BLACK) System.out.println("Aucun coup possible pour le joueur noir");
                else System.out.println("Aucun coup possible pour le joueur blanc");
                color = color.getOpponent();
                board.setCurrentPlayer(color);
                board.setPossibleMoves(color);
            } else clearScreen();
        }
        if (!stop) {
            clearScreen();
            printBoard();
            printScore();
        }
        System.out.println("Fin de la partie");
    }

    /**
     * Joue une partie contre l'IA minimax
     * @param ia l'IA
     */
    public void play(MinMax ia) {
        Color color = Color.BLACK;
        board.setPossibleMoves(color);
        boolean stop = false;
        while (board.getMoves().size() > 0) {
            if (color == ia.getPlayer()) {
                System.out.println("Tour de l'IA");
                printBoard();
                System.out.println("Calcul en cours...");
                Point point = ia.getBestMove(new State(board));
                System.out.println("Coup joué : " + (char) (point.y + 'A') + (point.x + 1));
                board.move(point.x, point.y, color);
                System.out.print("Entrer pour continuer");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
            }
            else {
                ArrayList<Point> possibleMoves = board.getMoves();
                System.out.println("Tour du joueur");
                printBoard();

                Scanner scanner = new Scanner(System.in);
                System.out.print("Entrer votre coup (ou exit/save) : ");
                String move = scanner.nextLine();
                while (move.length() < 2) {
                    System.out.print("Coup impossible réessayez : ");
                    move = scanner.nextLine();
                }
                int x = move.charAt(1) - '1';
                int y = move.charAt(0) - 'A';
                Point point = new Point(x, y);

                while (move.length() != 2 || x < 0 || x >= board.getSize() || y < 0 || y >= board.getSize() || !possibleMoves.contains(point)) {
                    switch (move) {
                        case "exit" -> { // Retourne au menu
                            stop = true;
                        }
                        case "save" -> { // Sauvegarde la partie
                            System.out.print("Entrer le nom du fichier : ");
                            String fileName = scanner.nextLine(); // On demande le nom du fichier
                            System.out.println("Sauvegarde en cours...");
                            save(fileName); // On sauvegarde la partie
                            System.out.println("Sauvegarde terminée");
                            stop = true;
                        }
                        default -> { // Si le coup n'est pas valide
                            System.out.print("Coup impossible réessayez : ");
                            move = scanner.nextLine();
                            x = move.charAt(1) - '1';
                            y = move.charAt(0) - 'A';
                            point = new Point(x, y);
                        }
                    }
                    if (stop) break;
                }

                if (stop) break; // Si on a choisi de retourner au menu, on sort de la boucle

                board.move(point.x, point.y, color);
            }

            color = color.getOpponent();
            board.setPossibleMoves(color);

            if (board.getPossibleMoves(Color.WHITE).size() == 0 && board.getPossibleMoves(Color.BLACK).size() == 0) break;
            else if (board.getMoves().size() == 0) {
                clearScreen();
                if (color == Color.BLACK) System.out.println("Aucun coup possible pour le joueur noir");
                else System.out.println("Aucun coup possible pour le joueur blanc");
                color = color.getOpponent();
                board.setCurrentPlayer(color);
                board.setPossibleMoves(color);
            } else clearScreen();
        }
        if (!stop) {
            clearScreen();
            printBoard();
            printScore();
        }
        System.out.println("Fin de la partie");
    }

    /**
     * Sauvegarde la partie
     * @param fileName le nom du fichier
     */
    private void save(String fileName) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName + ".bin"));
            oos.writeObject(board);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
