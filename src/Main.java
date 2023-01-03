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
        while (choice != 3) {
            System.out.println("1. Jouer");
            System.out.println("2. Charger une partie");
            System.out.println("3. Quitter");
            System.out.print("Choix : ");
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer un nombre");
                scanner.next();
            }
            switch (choice) { // TODO: ajouter un menu pour l'IA
                case 1 -> {
                    System.out.print("Taille du plateau : ");
                    try {
                        int size = scanner.nextInt();
                        Board board1 = new Board(size);
                        Actions actions1 = new Actions(board1);
                        System.out.println("Choix du premier joueur :");
                        System.out.println("1. Noir");
                        System.out.println("2. Blanc");
                        System.out.print("Choix : ");
                        int player = scanner.nextInt();
                        actions1.play(player == 1 ? Color.BLACK : Color.WHITE);
                        choice = 0;
                    } catch (Exception e) {
                        System.out.println("Erreur : Veuillez entrer un nombre");
                        scanner.next();
                    }
                }
                case 2 -> {
                    System.out.print("Entrer le nom du fichier : ");
                    Scanner scanner2 = new Scanner(System.in);
                    String fileName = scanner2.nextLine(); // On demande le nom du fichier
                    System.out.println("Chargement en cours...");
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName + ".bin"));
                        Board board2 = (Board) ois.readObject(); // On charge la partie
                        System.out.println("Chargement terminé");
                        Actions actions2 = new Actions(board2);
                        if (board2 != null) actions2.play(board2.getCurrentPlayer());
                        else System.out.println("Erreur : Impossible de charger la partie");
                    } catch (Exception e) {
                        System.out.println("Erreur : Fichier introuvable");
                        choice = 0;
                        break;
                    }
                    choice = 0;
                }
                case 3 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide !");
            }
        }
    }
}