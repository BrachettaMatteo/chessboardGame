package it.unicam.cs.pa.chessboardGame.structure;

/**
 * Represent all possible movement of pawn. If the pawn not implement all movement implement only possible movement
 *
 * @author Matteo Brachetta
 * @version 0.1.1
 */
public interface movement {

    /**
     * move the pawn to forward-center. if the pawn isn't forward not implement return null
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     */
    default void forward() {
        throw new UnsupportedOperationException("movement is not allowed");
    }


    /**
     * move the pawn to forward-right. if the pawn isn't forward not implement return null
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     */
    /*default void forwardRight(gameBoard boardGame, pawn pawn) {
        throw new UnsupportedOperationException("movement is not allowed");
    }*/
    default void forwardRight() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * move the pawn to forward-left. if the pawn isn't forward not implement return null
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     */
    default void forwardLeft() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * move the pawn to back-center. if the pawn isn't forward not implement return null
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     */
    default void back() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * move the pawn to back-right. if the pawn isn't forward not implement return null
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     */
    default void backRight() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * move the pawn to back-left. if the pawn isn't forward not implement return null
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     */
    default void backLeft() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * move the pawn to left. if the pawn isn't forward not implement return null
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     */
    default void left() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * move the pawn to right. if the pawn isn't forward not implement return null
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     */
    default void right() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

}