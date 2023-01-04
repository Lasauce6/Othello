package othello;


/**
 * Classe Color
 */
public enum Color {
    
    EMPTY, WHITE, BLACK, POSSIBLE_MOVE;

    /**
     * Retourne le joueur adverse
     * @return le joueur adverse
     */
    public Color getOpponent() {
        if (this == WHITE) return BLACK;
        else if (this == BLACK) return WHITE;
        else return EMPTY;
    }
}
