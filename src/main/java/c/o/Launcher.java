package c.o;

import c.o.core.Square;
import c.o.core.board.concrete.OneKnightBoard;
import c.o.core.strategy.XmKnight;

import java.util.Set;

public class Launcher {

    public static void main(String[] argv) {
        if (argv.length != 2) {
            System.out.println("Usage: x1 x2, where x1 & x2 are chess squares, e.g A1");
            return;
        }
        Square start = Square.valueOf(argv[0]);
        Square end = Square.valueOf(argv[1]);
        Set<String> routes = new XmKnight(new OneKnightBoard(start), start, end).threeMoveRoutes();
        for (String route : routes) {
            System.out.println(route);
        }
    }
}
