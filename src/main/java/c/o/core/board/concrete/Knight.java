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
        upper.ifPresent(square1 -> {
            square1.shiftFile(+1).ifPresent(moves::add);
            square1.shiftFile(-1).ifPresent(moves::add);
        });
        Optional<Square> lower = position.shiftRank(-2);
        lower.ifPresent(square1 -> {
            square1.shiftFile(+1).ifPresent(moves::add);
            square1.shiftFile(-1).ifPresent(moves::add);
        });
        Optional<Square> left = position.shiftFile(-2);
        left.ifPresent(square1 -> {
            square1.shiftRank(+1).ifPresent(moves::add);
            square1.shiftRank(-1).ifPresent(moves::add);
        });
        Optional<Square> right = position.shiftFile(+2);
        right.ifPresent(square1 -> {
            square1.shiftRank(+1).ifPresent(moves::add);
            square1.shiftRank(-1).ifPresent(moves::add);
        });
        for (Square move : moves) {
            board.get(move).ifPresent(piece -> {// implement more restrictions here
                if (piece.getColor() == color || piece.getType() == Pieces.KING) {
                    moves.remove(move);
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
