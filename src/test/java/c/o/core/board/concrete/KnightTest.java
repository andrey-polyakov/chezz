package c.o.core.board.concrete;

import c.o.core.Square;
import c.o.core.piece.Piece;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class KnightTest {
    private Square d4 = Square.valueOf("d4");
    private Square a1 = Square.valueOf("a1");
    private Square h1 = Square.valueOf("h1");
    private Square h8 = Square.valueOf("h8");
    private Square a8 = Square.valueOf("a8");

    @Test
    public void possibleMovesD4Test() {
        Piece systemUnderTest = new OneKnightBoard(d4).get(d4).get();
        Set<Square> moves = systemUnderTest.getPossibleMoves();
        Set<Square> expected = stuffedSet(Square.valueOf("b3"), Square.valueOf("c2"),
                Square.valueOf("b5"), Square.valueOf("c6"), Square.valueOf("e2"),
                Square.valueOf("e6"), Square.valueOf("f3"), Square.valueOf("f5"));
        assertTrue(expected.containsAll(moves));
        assertEquals(expected.size(), moves.size());
    }

    @Test
    public void possibleMovesA1Test() {
        Piece systemUnderTest = new OneKnightBoard(a1).get(a1).get();
        Set<Square> moves = systemUnderTest.getPossibleMoves();
        Set<Square> expected = stuffedSet(Square.valueOf("b3"), Square.valueOf("c2"));
        assertTrue(expected.containsAll(moves));
        assertEquals(expected.size(), moves.size());
    }

    @Test
    public void possibleMovesA8Test() {
        Piece systemUnderTest = new OneKnightBoard(a8).get(a8).get();
        Set<Square> moves = systemUnderTest.getPossibleMoves();
        Set<Square> expected = stuffedSet(Square.valueOf("c7"), Square.valueOf("b6"));
        assertTrue(expected.containsAll(moves));
        assertEquals(expected.size(), moves.size());
    }

    @Test
    public void possibleMovesH1Test() {
        Piece systemUnderTest = new OneKnightBoard(h1).get(h1).get();
        Set<Square> moves = systemUnderTest.getPossibleMoves();
        Set<Square> expected = stuffedSet(Square.valueOf("g3"), Square.valueOf("f2"));
        assertTrue(expected.containsAll(moves));
        assertEquals(expected.size(), moves.size());
    }

    @Test
    public void possibleMovesH8Test() {
        Piece systemUnderTest = new OneKnightBoard(h8).get(h8).get();
        Set<Square> moves = systemUnderTest.getPossibleMoves();
        Set<Square> expected = stuffedSet(Square.valueOf("g6"), Square.valueOf("f7"));
        assertTrue(expected.containsAll(moves));
        assertEquals(expected.size(), moves.size());
    }

    private Set<Square> stuffedSet(Square... squares) {
        HashSet<Square> result = new HashSet<>();
        for (Square square : squares) {
            result.add(square);
        }
        return result;
    }
}
