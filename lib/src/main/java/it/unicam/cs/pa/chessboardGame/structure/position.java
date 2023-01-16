package it.unicam.cs.pa.chessboardGame.structure;

/**
 * Represent coordinate of chessboard
 *
 * @author Matteo Brachetta
 * @version 1.0
 */
public class position implements Comparable<position> {

    /**
     * Represent the {@code position} in row.
     */
    private int row;
    /**
     * Represent the {@code position} in column.
     */
    private int column;

    /**
     * Construction for create new {@code position}.
     *
     * @param column colum of {@code position}.
     * @param row    row of {@code position}.
     */
    public position(int column, int row) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get row of {@code position}.
     *
     * @return row.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Setting new {@code position} row.
     *
     * @param row new {@code position} row.
     * @throws IllegalArgumentException row is less than 0.
     */
    public void setRow(int row) {
        if (row >= 0)
            this.row = row;
        else
            throw new IllegalArgumentException("row is less 0");

    }

    /**
     * Get column of {@code position}.
     *
     * @return column.
     * @throws IllegalArgumentException row is less than 0.
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Setting new {@code position} column.
     *
     * @param column new {@code position} column.
     * @throws IllegalArgumentException column is less than 0.
     */
    public void setColumn(int column) {
        if (column >= 0)
            this.column = column;
        else
            throw new IllegalArgumentException("column is < 0");

    }

    @Override
    public String toString() {
        return "[" + column + " - " + row +
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