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
            catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer un nombre entre 1 et 4 inclus");
                scanner.next();
            }
            switch (choice) { // TODO: ajouter un menu pour l'IA
                case 1 -> {
                    System.out.print("Choix de la taille du plateau : ");
                    int size = choseSize();
                    if (size == 0) break;
                    Board board1 = new Board(size);
                    Actions actions1 = new Actions(board1);
                    actions1.play(Color.BLACK);
                    choice = 0;
                }

                case 2 -> {
                    System.out.print("Choix de la taille du plateau : ");
                    int size = choseSize();
                    if (size == 0) break;
                    Board board2 = new Board(size);
                    Actions actions2 = new Actions(board2);
                    System.out.println("Choix du joueur : ");

                    Color player = choseColor();
                    if (player == Color.EMPTY) break;

                    System.out.println("Choix de l'IA : ");
                    System.out.println("1. Naif");
                    System.out.println("2. Minimax");
                    System.out.print("Choix : ");
                    int choiceIA = scanner.nextInt();
                    if (choiceIA == 1) {
                        actions2.playIA(Color.BLACK, 1);
                    }
                    else if (choiceIA == 2) {
                        actions2.playIA(Color.BLACK, 2);
                    }
                    else {
                        System.out.println("Erreur : Veuillez entrer un nombre entre 1 et 2 inclus");
                        break;
                    }
                    choice = 0;
                }

                case 3 -> {
                    System.out.print("Entrer le nom du fichier : ");
                    Scanner scanner3 = new Scanner(System.in);
                    String fileName = scanner3.nextLine(); // On demande le nom du fichier
                    System.out.println("Chargement en cours...");
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName + ".bin"));
                        Board board3 = (Board) ois.readObject(); // On charge la partie
                        System.out.println("Chargement terminé");
                        Actions actions2 = new Actions(board3);
                        if (board3 != null) actions2.play(board3.getCurrentPlayer());
                        else System.out.println("Erreur : Impossible de charger la partie");
                    } catch (Exception e) {
                        System.out.println("Erreur : Fichier introuvable ou inexistant");
                        choice = 0;
                        break;
                    }
                    choice = 0;
                }

                case 4 -> System.out.println("Au revoir !");

                default -> System.out.println("Choix invalide !");
            }
        }
    }

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

    private static Color choseColor() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
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
}

