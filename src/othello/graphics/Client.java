package othello.graphics;

import othello.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Client {
    private final JFrame frame = new JFrame("Othello");

    public Client() {
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1155, 730));
        frame.pack();
        frame.setVisible(true);
    }

    public void menu(Board board) {
        MenuPanel panel = new MenuPanel(this, board);
        panel.setBounds(0, 0, 1155, 730);
        frame.setContentPane(panel);
        frame.repaint(5);
    }

    public void menuAi(Board board) {
        AiPanel panel = new AiPanel(this, board);
        panel.setBounds(0, 0, 1155, 730);
        frame.setContentPane(panel);
        frame.repaint(5);
    }

    public void game1v1(Board board) {
        GamePanel panel = new GamePanel(this, board);
        panel.setBounds(0, 0, 1155, 730);
        frame.setContentPane(panel);
        frame.repaint(5);
    }

    public void gameVsAi(Board board, int aiPlayer, int aiLevel) {
        GamePanel panel = new GamePanel(this, board, aiPlayer, aiLevel);
        panel.setBounds(0, 0, 1155, 730);
        frame.setContentPane(panel);
        frame.repaint(5);
    }

    public void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
