package othello.graphics;

import othello.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Le menu de l'IA
 */
public class AiPanel extends JPanel implements ActionListener {
    private final Client client;
    private final Board board;
    private final JButton button1 = new JButton();
    private final JButton button2 = new JButton();
    private final JButton button3 = new JButton();
    private final JRadioButton black = new JRadioButton("Noir");
    private final JRadioButton white = new JRadioButton("Blanc");
    private final JButton buttonReturn = new JButton();

    /**
     * Le constructeur du panel
     * @param client le client
     * @param board le plateau de jeu
     */
    public AiPanel(Client client, Board board) {
        super();
        this.client = client;
        this.board = board;
        setLayout(null);
        setOpaque(true);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        buttonReturn.addActionListener(this);
    }

    /**
     * Affiche le panel
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        setTitle(g);
        setButton1();
        setButton2();
        setButton3();
        setPlayer(g);
        setButtonReturn();
    }

    /**
     * Affiche le titre
     * @param g the <code>Graphics</code> object to protect
     */
    private void setTitle(Graphics g) {
        String title = "Choix de la difficulté";
        g.setFont(new Font("arial", Font.BOLD, 50));
        g.drawString(title, getWidth() / 2 - 265, 100);
    }

    /**
     * Affiche le bouton Difficulté 1
     */
    private void setButton1() {
        button1.setBounds(getWidth() / 2 - 140, 150, 300, 100);
        button1.setFont(new Font("arial", Font.PLAIN, 24));
        button1.setText("Difficulté 1");
        this.add(button1);
    }

    /**
     * Affiche le bouton Difficulté 2
     */
    private void setButton2() {
        button2.setBounds(getWidth() / 2 - 140, 275, 300, 100);
        button2.setFont(new Font("arial", Font.PLAIN, 24));
        button2.setText("Difficulté 2");
        this.add(button2);
    }

    /**
     * Affiche le bouton Difficulté 3
     */
    private void setButton3() {
        button3.setBounds(getWidth() / 2 - 140, 400, 300, 100);
        button3.setFont(new Font("arial", Font.PLAIN, 24));
        button3.setText("Difficulté 3");
        this.add(button3);
    }

    /**
     * Affiche le choix de joueur
     * @param g the <code>Graphics</code> object to protect
     */
    private void setPlayer(Graphics g) {
        String title = "Choix du joueur";
        g.setFont(new Font("arial", Font.PLAIN, 30));
        g.drawString(title, getWidth() / 2 - 100, 550);
        black.setBounds(getWidth() / 2 - 100, 550, 175, 100);
        white.setBounds(getWidth() / 2 + 75, 550, 175, 100);
        black.setFont(new Font("arial", Font.PLAIN, 20));
        white.setFont(new Font("arial", Font.PLAIN, 20));
        white.setSelected(true);
        ButtonGroup bg = new ButtonGroup();
        bg.add(black);
        bg.add(white);
        this.add(black);
        this.add(white);
    }

    /**
     * Affiche le bouton Retour
     */
    private void setButtonReturn() {
        buttonReturn.setBounds(0, 0, 100, 100);
        buttonReturn.setFont(new Font("arial", Font.PLAIN, 15));
        buttonReturn.setText("Retour");
        this.add(buttonReturn);
    }

    /**
     * Effectue l'action voulue en fonction du bouton pressé
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            if (white.isSelected()) client.gameVsAi(board, 1, 1);
            else client.gameVsAi(board, -1, 1);
        } else if (e.getSource() == button2) {
            if (white.isSelected()) client.gameVsAi(board, 1, 2);
            else client.gameVsAi(board, -1, 2);
        } else if (e.getSource() == button3) {
            if (white.isSelected()) client.gameVsAi(board, 1, 3);
            else client.gameVsAi(board, -1, 3);
        } else client.menu(board);
    }
}
