package c.o.core.board;

import c.o.core.Colors;
import c.o.core.Square;
import c.o.core.exception.RulesViolationException;
import c.o.core.piece.Piece;
import c.o.core.piece.Pieces;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Platform for enforcing chess rules.
 */
public abstract class AbstractBoard implements Board {
    protected Map<Square, Piece> pieces = new HashMap<>();

    @Override
    public abstract Board move(Square from, Square to);

    @Override
    public Optional<Piece> get(Square square) {
        return Optional.ofNullable(pieces.get(square));
    }

    @Override
    public abstract Piece put(Square square, Pieces piece, Colors color) throws RulesViolationException;

    @Override
    public List<Piece> getBlackPieces() {
        Collection<Piece> p = pieces.values();
        return p.stream()
                .filter(item -> item.getColor().equals(Colors.BLACK))
                .collect(Collectors.toList());
    }

    @Override
    public List<Piece> getWhitePieces() {
        Collection<Piece> p = pieces.values();
        return p.stream()
                .filter(item -> item.getColor().equals(Colors.WHITE))
                .collect(Collectors.toList());
    }

}
