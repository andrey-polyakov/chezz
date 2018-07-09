package c.o.core.board;

import c.o.core.Colors;
import c.o.core.Square;
import c.o.core.exception.RulesViolationException;
import c.o.core.piece.Piece;
import c.o.core.piece.Pieces;

import java.util.List;
import java.util.Optional;

/**
 * Represents chess board and keeps track of all the pieces. Implementations
 * must take care of promotion, capture and all other aspects of the game.
 */
public interface Board {

    /**
     * Move a piece from one square to the other. May return this board or new instance depending
     * on the implementation.
     *
     * @param from
     * @param to
     */
    Board move(Square from, Square to) throws RulesViolationException;

    /**
     * Retrieves piece in given square
     * @param square
     * @return
     */
    Optional<Piece> get(Square square);

    /**
     * Places a new piece on board unless it violates the rules. This call is useful during
     * initialization only.
     *
     * @param square coordinates
     * @param piece type to be created
     * @param color black or white
     * @return materialized piece
     */
    Piece put(Square square, Pieces piece, Colors color) throws RulesViolationException;

    /**
     * Black ones only
     * @return
     */
    List<Piece> getBlackPieces();

    /**
     * White ones only
     * @return
     */
    List<Piece> getWhitePieces();

    /**
     * To be used by game controller
     * @return
     */
    boolean isGameOver();
}
