package it.unicam.cs.pa.chessboardGame.structure;

/**
 * Represent all possible movement of {@code pawn}. If the {@code pawn} not implement all movement implement only possible movement
 *
 * @author Matteo Brachetta
 * @version 1.0
 */
public interface movement {

    /**
     * Move the {@code pawn} to forward-center. If the {@code pawn} isn't forward not implement return {@code null}.
     *
     * @throws UnsupportedOperationException if the movement not supported for {@code pawn}
     * @throws IllegalArgumentException      If the movement cannot be executed
     */
    default void forward() {
        throw new UnsupportedOperationException("movement is not allowed");
    }


    /**
     * Move the {@code pawn} to forward-right. If the {@code pawn} isn't forward not implement return {@code null}
     *
     * @throws UnsupportedOperationException if the movement not supported for {@code pawn}
     * @throws IllegalArgumentException      If the movement cannot be executed
     */
    default void forwardRight() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * Move the {@code pawn} to forward-left. If the {@code pawn} isn't forward not implement return {@code null}
     *
     * @throws UnsupportedOperationException if the movement not supported for {@code pawn}
     * @throws IllegalArgumentException      If the movement cannot be executed
     */
    default void forwardLeft() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * Move the {@code pawn} to back-center. If the pawn isn't forward not implement return {@code null}
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     * @throws IllegalArgumentException      If the movement cannot be executed
     */
    default void back() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * Move the pawn to back-right. If the pawn isn't forward not implement return {@code null}
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     * @throws IllegalArgumentException      If the movement cannot be executed
     */
    default void backRight() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * Move the pawn to back-left. if the pawn isn't forward not implement return {@code null}
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     * @throws IllegalArgumentException      If the movement cannot be executed
     */
    default void backLeft() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * Move the pawn to left. if the pawn isn't forward not implement return {@code null}
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     * @throws IllegalArgumentException      If the movement cannot be executed
     */
    default void left() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * Move the pawn to right. If the pawn isn't forward not implement return {@code null}
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     * @throws IllegalArgumentException      If the movement cannot be executed
     */
    default void right() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * Move the pawn to new {@code position}. New {@code position} is select to random from possible moves.
     *
     * @throws UnsupportedOperationException if the movement not supported for pawn
     * @throws IllegalArgumentException      If not a single movement can be executed
     */
    default void randomMove() {
        throw new UnsupportedOperationException("movement is not allowed");
    }

    /**
     * Check the pawn is able to execute at least one movement.
     *
     * @return {@code true} if pawn move else {@code false} the pawn don't move
     */
    boolean isAvailableToMove();
}