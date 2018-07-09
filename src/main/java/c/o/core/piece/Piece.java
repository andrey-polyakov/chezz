package c.o.core.piece;

import c.o.core.Colors;
import c.o.core.Square;

import java.util.Set;

/**
 * Generic chess piece that can be moved.
 */
public interface Piece {

    /**
     * Each piece can determine its possible moves based on rules and current layout
     * of the board.
     *
     * <br>
     *
     * @return squares where this piece can be legally moved to
     */
    Set<Square> getPossibleMoves();

    /**
     *
     * @return player's color
     */
    Colors getColor();

    /**
     * Some pices may be promoted over the course of the game. To be precise, pawns only.
     * @return current type
     */
    Pieces getType();

    /**
     *
     * @return this piece coordinates
     */
    Square getSquare();

}
