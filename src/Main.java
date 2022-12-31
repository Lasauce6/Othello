import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import othello.Actions;
import othello.Board;
import othello.Color;

public class Main {
    /**
     * Lancement du menu principal
     * 
     * read()  = lire un caractères
     * readline()  = lire une chaine de caractères
     * skip(n) = ignore n car
     */

    public static void main(String[] args) throws Exception
    {InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr); //attention synchrone
        //Scanner sc = new Scanner(); //attention asynchrone donc + rapide car n'attend pas la finalisation des taches
        

        Board board = new Board(4);
        Actions actions = new Actions(board);
        actions.play(Color.BLACK);
    }
}