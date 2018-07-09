package c.o.core;

import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SquareTest {

    @Test
    public void coordinateParsingHappyPathTest() {
        Square square = Square.valueOf("A1");
        assertEquals(1, square.getRank());
        assertEquals('a', square.getFile());
        square = Square.valueOf("H1");
        assertEquals(1, square.getRank());
        assertEquals('h', square.getFile());
    }

    @Test
    public void coordinateParsingHappyPath2Test() {
        Square square = Square.valueOf("A8");
        assertEquals(8, square.getRank());
        assertEquals('a', square.getFile());
        square = Square.valueOf("H8");
        assertEquals(8, square.getRank());
        assertEquals('h', square.getFile());
    }

    @Test(expected = IllegalArgumentException.class)
    public void coordinateParsingErrorTest() {
        Square.valueOf("A9");
    }

    @Test(expected = IllegalArgumentException.class)
    public void coordinateParsingError2Test() {
        Square.valueOf("B0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void coordinateParsingError3Test() {
        Square.valueOf("BB");
    }

    @Test(expected = IllegalArgumentException.class)
    public void coordinateParsingError4Test() {
        Square.valueOf("C");
    }

    @Test(expected = IllegalArgumentException.class)
    public void coordinateParsingError5Test() {
        Square.valueOf("Z4");
    }

    @Test
    public void takeRightTest() {
        Optional<Square> right = Square.valueOf("A1").shiftFile(3);
        assertTrue(right.isPresent());
        assertEquals(Square.valueOf("d1"), right.get());
        right = right.get().shiftFile(13);
        assertFalse(right.isPresent());
    }

    @Test
    public void takeLeftMinusThreeTest() {
        assertFalse(Square.valueOf("A1").shiftRank(-3).isPresent());
    }

    @Test
    public void takeRightMinusThreeTest() {
        assertFalse(Square.valueOf("A1").shiftFile(-3).isPresent());
    }

    @Test
    public void takeLeftFromHTest() {
        Optional<Square> left = Square.valueOf("H1").shiftFile(-7);
        assertTrue(left.isPresent());
        assertEquals(Square.valueOf("A1"), left.get());
        left = left.get().shiftFile(-1);
        assertFalse(left.isPresent());
    }

    @Test
    public void shiftRankTest() {
        Optional<Square> higher = Square.valueOf("d1").shiftRank(7);
        assertTrue(higher.isPresent());
        assertEquals(Square.valueOf("d8"), higher.get());
        higher = higher.get().shiftRank(1);
        assertFalse(higher.isPresent());
    }
}
