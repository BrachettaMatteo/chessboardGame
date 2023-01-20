package it.unicam.cs.pa.chessboardGame.structure;

/**
 * {@code player}, he's responsible to squad {@code pawn} of game
 *
 * @author Matteo Brachetta
 * @version 1.0
 */
public interface player {

    /**
     * Get {@code player} identifier.
     *
     * @return {@code player} identifier.
     */
    String getId();

    /**
     * Get {@code player} score.
     *
     * @return the score of game
     */
    int getScore();

    /**
     * Increment {@code player} score.
     *
     * @param score score to add {@code player} score
     * @throws IllegalArgumentException the score is less than 0
     */
    void addScore(int score);

    /**
     * Remove score for score {@code player} game
     *
     * @param score score for remove to total
     * @throws IllegalArgumentException the score is less 0
     */
    void removeScore(int score);

    /**
     * Get {@code player} name.
     *
     * @return {@code player} name
     */
    String getName();

    /**
     * Executes the pawn move.
     *
     * @param pawnToMove pawn to request move
     * @param move       specific execute move.
     * @throws IllegalArgumentException if the movement isn't correct
     */
    void executeMove(pawn pawnToMove, String move);

    /**
     * Executes the pawn move.
     *
     * @param board pawn to request move
     */
    void executeAutomaticMove(gameBoard board);

}