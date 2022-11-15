package othello.graphics;

import othello.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private final Client client;
    private final Board board;
    private final boolean isAi;
    private int aiPlayer;
    private int aiLevel;

    /**
     * Le constructeur du Panel pour une partie sans IA
     * @param client le client
     * @param board le plateau de jeu
     */
    public GamePanel(Client client, Board board) {
        super();
        this.client = client;
        this.board = board;
        this.isAi = false;
        setLayout(null);
        setOpaque(true);
        MouseListener ml = new MouseListener();
        addMouseListener(ml);
    }

    /**
     * Le constructeur du Panel pour une partie avec l'IA
     * @param client le client
     * @param board le plateau de jeu
     * @param aiPlayer le camp de l'IA
     * @param aiLevel le niveau de l'IA
     */
    public GamePanel(Client client, Board board, int aiPlayer, int aiLevel) {
        super();
        this.client = client;
        this.board = board;
        this.aiPlayer = aiPlayer;
        this.aiLevel = aiLevel;
        this.isAi = true;
        setLayout(null);
        setOpaque(true);
        MouseListener ml = new MouseListener();
        addMouseListener(ml);
    }

    /**
     * Prise en charge de la souris
     */
    public class MouseListener extends MouseAdapter {
        /**
         * Joue la bonne case lorsque l'on clique dessus
         * @param e the event to be processed
         */
        public void mouseClicked(MouseEvent e) {
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int height = (int) getSize().getHeight();
        int width = (int) getSize().getWidth();
        int r;
        if (height <= width) r = height / 2;
        else r = width / 2;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        g.setColor(Color.BLACK);
        g.fillOval(height / 3, width / 3, r, r);
    }
}
