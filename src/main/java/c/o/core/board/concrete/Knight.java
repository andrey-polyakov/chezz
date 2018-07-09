package c.o.core.board.concrete;

import c.o.core.Colors;
import c.o.core.Square;
import c.o.core.board.Board;
import c.o.core.piece.Piece;
import c.o.core.piece.Pieces;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * The knight move is unusual among chess pieces. It moves to a square that is two squares away horizontally and
 * one square vertically, or two squares vertically and one square horizontally. The complete move therefore looks like
 * the letter "L". <br><br>
 * Unlike all other standard chess pieces, the knight can "jump over" all other pieces (of either color) to its destination square.
 */
public class Knight implements Piece {
    private Square position;
    private Colors color;
    private Board board;
    private Pieces type;

    Knight(Square position, Colors color, Board board) {
        this.position = position;
        this.color = color;
        this.board = board;
        type = Pieces.KNIGHT;
    }

    void setPosition(Square position) {
        this.position = position;
    }

    @Override
    public Set<Square> getPossibleMoves() {
        Set<Square> moves = new HashSet<>();
        Optional<Square> upper = position.shiftRank(+2);
        if (upper.isPresent()) {
            upper.get().shiftFile(+1).ifPresent(square -> moves.add(square));
            upper.get().shiftFile(-1).ifPresent(square -> moves.add(square));
        }
        Optional<Square> lower = position.shiftRank(-2);
        if (lower.isPresent()) {
            lower.get().shiftFile(+1).ifPresent(square -> moves.add(square));
            lower.get().shiftFile(-1).ifPresent(square -> moves.add(square));
        }
        Optional<Square> left = position.shiftFile(-2);
        if (left.isPresent()) {
            left.get().shiftRank(+1).ifPresent(square -> moves.add(square));
            left.get().shiftRank(-1).ifPresent(square -> moves.add(square));
        }
        Optional<Square> right = position.shiftFile(+2);
        if (right.isPresent()) {
            right.get().shiftRank(+1).ifPresent(square -> moves.add(square));
            right.get().shiftRank(-1).ifPresent(square -> moves.add(square));
        }
        for (Square move : moves) {
            board.get(move).ifPresent(piece -> {
                if (piece.getColor() == color || piece.getType() == Pieces.KING) {
                    moves.remove(move);
                    return;
                }
            }
            );
        }
        return moves;
    }

    @Override
    public Colors getColor() {
        return color;
    }

    @Override
    public Pieces getType() {
        return type;
    }

    @Override
    public Square getSquare() {
        return position;
    }

    @Override
    public String toString() {
        return color + " " + type + "@" + position;
    }
}
