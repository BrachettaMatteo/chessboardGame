package it.unicam.cs.pa.chessboardGame.structure;

/**
 * Represent all possible movement of pawn
 *
 * @author Matteo Brachetta
 * @version 0.0
 */
public interface movement {

    /**
     * move the pawn to forward.
     * if the pawn isn't forward not implement
     */
    void forward();

    /**
     * move pawn to back.
     * if the pawn isn't forward not implement
     */
    void back();

    /**
     * move the pawn to left.
     * if the pawn isn't forward not implement.
     */
    void left();

    /**
     * move the pawn to right.
     * if the pawn isn't forward not implement
     */
    void right();

}