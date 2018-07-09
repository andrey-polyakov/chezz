package c.o.core;

import java.util.Objects;
import java.util.Optional;

/**
 * Represent square of chess board.
 */
public class Square {

    private final int rank;
    private final char file;

    private Square(int rank, char file) {
        this.rank = rank;
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return rank == square.rank &&
                file == square.file;
    }

    @Override
    public int hashCode() {

        return Objects.hash(rank, file);
    }

    public int getRank() {
        return rank;
    }

    public char getFile() {
        return file;
    }

    @Override
    public String toString() {
        return file + "" + rank;
    }

    public static Square valueOf(String coordinates) {
        if (coordinates.length() != 2) {
            throw new IllegalArgumentException("Expected a string of size two");
        }
        char[] array = coordinates.toLowerCase().toCharArray();
        char file = array[0];
        if (file < 97 || file > 104) {
            throw new IllegalArgumentException("Invalid file");
        }
        int rank;
        if (array[1] > 48 && array[1] < 57) {
            rank = array[1] - 48;
        } else {
            throw new IllegalArgumentException("Invalid rank");
        }
        return new Square(rank, file);
    }

    /**
     * Shifts rank
     * @param squares
     * @return square which n squares relative to this
     */
    public Optional<Square> shiftRank(int squares) {
        if (rank + squares > 0 && rank + squares < 9) {
            return Optional.of(new Square(rank + squares, file));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Shifts file
     * @param n how many squares
     * @return square which n squares relative to this
     */
    public Optional<Square> shiftFile(int n) {
        if (file + n > 96 && file + n < 105) {
            return Optional.of(new Square(rank , (char) (file + n)));
        } else {
            return Optional.empty();
        }
    }
}
