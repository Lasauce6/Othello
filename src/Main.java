import ai.MinMax;
import ai.Naif;
import othello.Actions;
import othello.Board;
import othello.Color;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

/**
 * Classe Main
 */
public class Main {

    /**
     * Lancement du menu principal
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 4) {
            System.out.println("Menu principal");
            System.out.println("1. Jouer");
            System.out.println("2. Jouer contre une IA");
            System.out.println("3. Charger une partie");
            System.out.println("4. Quitter");
            System.out.print("Choix : ");

            try {
                choice = scanner.nextInt();
            }
            catch (Exception e) { // Si l'utilisateur entre autre chose qu'un entier
                System.out.println("Erreur : Veuillez entrer un nombre entre 1 et 4 inclus");
                scanner.next();
            }
            switch (choice) {
                case 1 -> { // Jouer en 1v1
                    System.out.print("Choix de la taille du plateau : ");
                    int size = choseSize(); // On demande la taille du plateau
                    if (size == 0) break;
                    Board board1 = new Board(size); // On crée le plateau
                    Actions actions1 = new Actions(board1); // On crée les actions
                    actions1.play(Color.BLACK); // On lance la partie
                    choice = 0;
                }

                case 2 -> { // Jouer contre une IA
                    // On demande la taille du plateau
                    System.out.print("Choix de la taille du plateau : ");
                    int size = choseSize();
                    if (size == 0) break;
                    Board board2 = new Board(size); // On crée le plateau
                    Actions actions2 = new Actions(board2); // On crée les actions

                    // On demande le joueur
                    System.out.println("Choix du joueur : ");
                    Color player = choseColor();
                    if (player == Color.EMPTY) break;

                    // On demande l'IA
                    System.out.println("Choix de l'IA : ");
                    int choiceIA = choseIA();
                    if (choiceIA == 0) break;

                    // On lance la partie
                    if (choiceIA == 1) {
                        Naif naif = new Naif(1, player.getOpponent(), board2);
                        board2.setNaif(naif);
                        actions2.play(naif);
                    }
                    else if (choiceIA == 2) {
                        Naif naif = new Naif(2, player.getOpponent(), board2);
                        board2.setNaif(naif);
                        actions2.play(naif);
                    }
                    else if (choiceIA == 3) {
                        MinMax minMax = new MinMax(player.getOpponent(), 1);
                        board2.setMinMax(minMax);
                        actions2.play(minMax);
                    }
                    else if (choiceIA == 4) {
                        MinMax minMax = new MinMax(player.getOpponent(), 2);
                        board2.setMinMax(minMax);
                        actions2.play(minMax);
                    }

                    choice = 0;
                }

                case 3 -> { // Charger une partie
                    System.out.print("Entrer le nom du fichier : ");
                    Scanner scanner3 = new Scanner(System.in);
                    String fileName = scanner3.nextLine(); // On demande le nom du fichier
                    System.out.println("Chargement en cours...");
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName + ".bin"));
                        Board board3 = (Board) ois.readObject(); // On charge la partie
                        System.out.println("Chargement terminé");
                        Actions actions2 = new Actions(board3);
                        if (board3 != null && board3.isIA() && board3.getNaif() != null) actions2.play(board3.getNaif()); // Si l'IA est une IA Naif
                        else if (board3 != null && board3.isIA() && board3.getMinMax() != null) actions2.play(board3.getMinMax()); // Si l'IA est une IA MinMax
                        else if (board3 != null && !board3.isIA()) actions2.play(board3.getCurrentPlayer()); // Si c'est une partie de 1v1
                        else System.out.println("Erreur : Impossible de charger la partie");
                    } catch (Exception e) {
                        System.out.println("Erreur : Fichier introuvable ou inexistant");
                        choice = 0;
                        break;
                    }
                    choice = 0;
                }

                case 4 -> System.out.println("Au revoir !"); // Quitter

                default -> System.out.println("Choix invalide !"); // Choix invalide
            }
        }
    }

    /**
     * Retourne la taille du plateau choisie par l'utilisateur
     * @return la taille du plateau choisie par l'utilisateur
     */
    private static int choseSize() {
        Scanner scanner = new Scanner(System.in);
        int size;
        try {
            size = scanner.nextInt();
            if (size < 4 || size > 8) {
                System.out.println("Erreur : Veuillez entrer un nombre entre 4 et 8 inclus");
                return 0;
            }
        }
        catch (Exception e) {
            System.out.println("Erreur : Veuillez entrer un nombre entre 4 et 8 inclus");
            scanner.next();
            return 0;
        }
        return size;
    }

    /**
     * Retourne la couleur choisie par l'utilisateur
     * @return la couleur choisie par l'utilisateur
     */
    private static Color choseColor() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            System.out.println("1. Noir");
            System.out.println("2. Blanc");
            System.out.print("Choix : ");
            choice = scanner.nextInt();
            if (choice < 1 || choice > 2) {
                System.out.println("Erreur : Veuillez entrer un nombre entre 1 et 2 inclus");
                return Color.EMPTY;
            }
        }
        catch (Exception e) {
            System.out.println("Erreur : Veuillez entrer un nombre entre 1 et 2 inclus");
            scanner.next();
            return Color.EMPTY;
        }
        if (choice == 1) return Color.BLACK;
        else return Color.WHITE;
    }

    /**
     * Retourne le choix de l'IA choisie par l'utilisateur
     * @return le choix de l'IA choisie par l'utilisateur
     */
    private static int choseIA() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            System.out.println("1. Naif");
            System.out.println("2. Naif + Arbre");
            System.out.println("3. Minimax");
            System.out.println("4. Alpha-Beta");
            System.out.print("Choix : ");
            choice = scanner.nextInt();
            if (choice < 1 || choice > 4) {
                System.out.println("Erreur : Veuillez entrer un nombre entre 1 et 4 inclus");
                return 0;
            }
        }
        catch (Exception e) {
            System.out.println("Erreur : Veuillez entrer un nombre entre 1 et 4 inclus");
            scanner.next();
            return 0;
        }
        return choice;
    }
}

