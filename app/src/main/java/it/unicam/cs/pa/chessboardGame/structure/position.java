package it.unicam.cs.pa.chessboardGame.structure;

/**
 * Represent coordinate of chessboard
 *
 * @author Matteo Brachetta
 * @version 0.0
 */
public class position implements Comparable<position> {

    /**
     * Represent the position in row
     */
    private int row;
    /**
     * Represent the position in column
     */
    private int column;

    public position(int column, int row) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    /**
     * Setting new position row
     *
     * @param row new position row
     * @throws IllegalArgumentException row is < 0
     */
    public void setRow(int row) {
        if (row < 0)
            throw new IllegalArgumentException("row isn't <0");
        this.row = row;
    }

    /**
     * get column
     *
     * @return column
     * @throws IllegalArgumentException row is < 0
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Setting new position column
     *
     * @param column new position column
     * @throws IllegalArgumentException column is < 0
     */
    public void setColumn(int column) {
        if (column < 0)
            throw new IllegalArgumentException("column is < 0");
        this.column = column;
    }

    @Override
    public String toString() {
        return "[" + row + " - " + column +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof position position)) return false;

        if (row != position.row) return false;
        return column == position.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

    @Override
    public int compareTo(position o) {
        if (this.column > o.column)
            return 1;
        if (this.column == o.column)
            if (this.row > o.row)
                return 1;
        return this.column == o.column && this.row == o.row ? 0 : -1;
    }
}