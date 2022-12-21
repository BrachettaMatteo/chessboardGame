package it.unicam.cs.pa.chessboardGame.structure;

/**
 * Represent all possible movement of pawn. If the pawn not implement all movement implement only possible movement
 *
 * @author Matteo Brachetta
 * @version 0.1
 */
public interface movement {

    /**
     * move the pawn to forward-center. if the pawn isn't forward not implement return null
     *
     * @return null if action not permission else position
     */
    default position forward(position positionStart, gameBoard boardGame) {
        return null;
    }


    /**
     * move the pawn to forward-right. if the pawn isn't forward not implement return null
     *
     * @return null if action not permission else position
     */
    default position forwardRight(position positionStart, gameBoard boardGame) {
        return null;
    }

    /**
     * move the pawn to forward-left. if the pawn isn't forward not implement return null
     *
     * @return null if action not permission else position
     */
    default position forwardLeft(position positionStart, gameBoard boardGame) {
        return null;
    }

    /**
     * move the pawn to back-center. if the pawn isn't forward not implement return null
     *
     * @return null if action not permission else position
     */
    default position back(position positionStart, gameBoard boardGame) {
        return null;
    }

    /**
     * move the pawn to back-right. if the pawn isn't forward not implement return null
     *
     * @return null if action not permission else position
     */
    default position backRight(position positionStart, gameBoard boardGame) {
        return null;
    }

    /**
     * move the pawn to back-left. if the pawn isn't forward not implement return null
     *
     * @return null if action not permission else position
     */
    default position backLeft(position positionStart, gameBoard boardGame) {
        return null;
    }

    /**
     * move the pawn to left. if the pawn isn't forward not implement return null
     *
     * @return null if action not permission else position
     */
    default position left(position positionStart, gameBoard boardGame) {
        return null;
    }

    /**
     * move the pawn to right. if the pawn isn't forward not implement return null
     *
     * @return null if action not permission else position
     */
    default position right(position positionStart, gameBoard boardGame) {
        return null;
    }

}