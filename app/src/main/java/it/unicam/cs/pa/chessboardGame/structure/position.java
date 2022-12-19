package it.unicam.cs.pa.chessboardGame.structure;

/**
 * Represent coordinate of chessboard
 *
 * @author Matteo Brachetta
 * @version 0.0
 */
public class position {

    /**
     * Represent the position in row
     */
    private int row;
    /**
     * Represent the position in column
     */
    private int column;

    public void getRow() {
        // TODO - implement position.getRow
        throw new UnsupportedOperationException();
    }

    /**
     * Setting new position row
     *
     * @param row new position row
     */
    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    /**
     * Setting new position column
     *
     * @param column new position column
     */
    public void setColumn(int column) {
        this.column = column;
    }

}