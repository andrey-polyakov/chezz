package c.o.core.strategy;

import c.o.core.Square;
import c.o.core.board.Board;
import c.o.core.piece.Piece;

import java.util.*;

/**
 * Calculates a list of all possible paths that one knight piece in the starting position could take to reach
 * the ending position in 3 moves.
 * <br><br>
 * This piece is generic except for knight shortcut in threeMoveRoutes().
 */
public class XmKnight {
    private Board board;
    private Node startingNode;
    private Square endingPosition;
    private final int DESIRED_DEPTH = 3;
    //knight-specific shortcut
    private static Set<Character>edges = new HashSet<>();
    private static Set<Integer>edges2 = new HashSet<>();

    static {
        edges.add('a');
        edges.add('z');
        edges2.add(1);
        edges2.add(8);
    }
    //end of specific shortcut

    public XmKnight(Board board, Square startingPosition, Square endingPosition) {
        if (startingPosition.equals(endingPosition)) {
            throw new IllegalArgumentException("Ending position equals starting position");
        }
        this.board = board;
        this.endingPosition = endingPosition;
        Optional<Piece> piece = board.get(startingPosition);
        startingNode = new Node(piece.orElseThrow(
                () -> new IllegalArgumentException("No piece at starting position")).getSquare());
    }

    public Set<String> threeMoveRoutes() {
        //This is a shortcut for 3-turn setup
        //This is knight specific as it is impossible to reach from edge to edge in 3 turns
        Set<Integer> theseRanks = new HashSet<>();
        theseRanks.add(startingNode.position.getRank());
        theseRanks.add(endingPosition.getRank());
        if (edges2.containsAll(theseRanks)) {
            return Collections.emptySet();
        }
        Set<Character>theseEdges = new HashSet<>();
        theseEdges.add(endingPosition.getFile());
        theseEdges.add(startingNode.position.getFile());
        if (theseEdges.containsAll(edges)) {
            return Collections.emptySet();
        }
        //end of specific shortcut
        List<Node> result = buildGraph();
        Set<String> routes = new HashSet<>();
        for (Node node : result) {
            routes.add(node.prettyPrint());
        }
        return routes;
    }

    private List<Node> buildGraph() {
        Deque<Stackable> thisLevel = new LinkedList<>();
        Deque<Stackable> nextLevel = new LinkedList<>();
        thisLevel.add(new Stackable(board, startingNode));
        List<Node> result = new LinkedList<>();
        int depth = 1;
        do {
            while (!thisLevel.isEmpty() && depth <= DESIRED_DEPTH) {
                Stackable current = thisLevel.remove();
                for (Square move : current.getPiece().getPossibleMoves()) {
                    if (move.equals(startingNode.position)) {
                        continue;
                    }
                    if (move.equals(endingPosition)) {
                        result.add(current.node.addChild(move));
                    } else if (depth < DESIRED_DEPTH) {
                          Board newBoard = current.board.move(current.node.position, move);
                          nextLevel.add(new Stackable(newBoard, current.node.addChild(move)));
                    }
                }
            }
            depth++;
            thisLevel.addAll(nextLevel);
            nextLevel.clear();
        } while (!thisLevel.isEmpty());
        return result;
    }

    class Stackable {
        private Board board;
        private Node node;

        Stackable(Board board, Node node) {
            this.board = board;
            this.node = node;
        }

        Piece getPiece() {
            return board.get(node.position).get();
        }

        @Override
        public String toString() {
            return node.prettyPrint();
        }
    }

    class Node {
        private Square position;
        private Node motherNode;

        Node(Square position, Node motherNode) {
            this.position = position;
            this.motherNode = motherNode;
        }

        Node(Square self) {
            this.position = self;
        }

        Node addChild(Square position) {
            return new Node(position, this);
        }

        String prettyPrint() {
            StringBuilder sb = new StringBuilder();
            sb.append(position);
            Node previous = motherNode;
            while (previous != null) {
                sb.insert(0, previous.position + "->");
                previous = previous.motherNode;
            }
            return sb.toString();
        }

        @Override
        public String toString() {
            return "Node{" + position + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(position, node.position);
        }

        @Override
        public int hashCode() {
            return position.hashCode();
        }
    }
}
