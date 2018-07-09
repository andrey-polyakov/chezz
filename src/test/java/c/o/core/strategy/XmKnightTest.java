package c.o.core.strategy;

import c.o.core.Square;
import c.o.core.board.concrete.OneKnightBoard;
import org.junit.Test;

import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class XmKnightTest {
    private Square a1 = Square.valueOf("a1");
    private Square e8 = Square.valueOf("e8");
    private Square h1 = Square.valueOf("h1");
    private Square f2 = Square.valueOf("f2");

    private Square d4 = Square.valueOf("d4");
    private Square d5 = Square.valueOf("d5");

    @Test(expected = IllegalArgumentException.class)
    public void invalidArgumentTest() {
        new XmKnight(new OneKnightBoard(d4), d4, d4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidArgument2Test() {
        new XmKnight(new OneKnightBoard(d4), h1, d5);
    }

    @Test
    public void middleTest() {
        XmKnight xmk = new XmKnight(new OneKnightBoard(d4), d4, d5);
        Set<String> result = xmk.threeMoveRoutes();
        assertTrue(result.contains("d4->e6->c7->d5"));
        assertTrue(result.contains("d4->f5->e3->d5"));
        assertTrue(result.contains("d4->b5->c7->d5"));
        assertTrue(result.contains("d4->c2->b4->d5"));

        assertTrue(result.contains("d4->e2->f4->d5"));
        assertTrue(result.contains("d4->c2->e3->d5"));
        assertTrue(result.contains("d4->b5->c3->d5"));
        assertTrue(result.contains("d4->c6->e7->d5"));

        assertTrue(result.contains("d4->e6->f4->d5"));
        assertTrue(result.contains("d4->f5->e7->d5"));
        assertTrue(result.contains("d4->e2->c3->d5"));
        assertTrue(result.contains("d4->c6->b4->d5"));
        assertEquals(12, result.size());
    }

    @Test
    public void notReachableTest() {
        XmKnight xmk = new XmKnight(new OneKnightBoard(e8), e8, a1);
        Set<String> result = xmk.threeMoveRoutes();
        assertEquals(0, result.size());
    }

    @Test
    public void cornerTest() {
        XmKnight xmk = new XmKnight(new OneKnightBoard(h1), h1, f2);
        Set<String> result = xmk.threeMoveRoutes();
        assertTrue(result.contains("h1->f2"));
        assertTrue(result.contains("h1->g3->e4->f2"));

        for (String s : result) {
            System.out.println(s);
        }
        assertEquals(2, result.size());
    }
}
