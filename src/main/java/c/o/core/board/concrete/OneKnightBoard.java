package c.o.core.board.concrete;

import c.o.core.Colors;
import c.o.core.Square;
import c.o.core.board.AbstractBoard;
import c.o.core.board.Board;
import c.o.core.exception.RulesViolationException;
import c.o.core.piece.Piece;
import c.o.core.piece.Pieces;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Represent a hypothetical layout. Specifically, empty board with with one (black) knight.<br><br>
 * Design wise, this is one piece immutable board.
 */
public class OneKnightBoard extends AbstractBoard {

    public OneKnightBoard(Square knightCoordinates) {
        pieces.put(knightCoordinates, new Knight(knightCoordinates, Colors.BLACK, this));
    }

    @Override
    public Board move(Square from, Square to) {
        //
        // Checks are omitted for this theoretical exercise.
        // Real game accounts for pawn promotion, castling, en passant
        get(from).orElseThrow(() -> new IllegalArgumentException("No piece at 'from' position"));
        Board newBoard = new OneKnightBoard(to);
        return newBoard;
    }

    @Override
    public Piece put(Square square, Pieces piece, Colors color) throws RulesViolationException {
        throw new NotImplementedException();
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
