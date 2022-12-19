package it.unicam.cs.pa.chessboardGame.structure;

/**
 * player, he's responsible to squad game, the squad pawn.
 *
 * @author Matteo Brachetta
 */
public interface player {

    /**
     * @return player identifier
     */
    String getId();

    /**
     * @return the score of game
     */
    int getScore();

    /**
     * increment player score game
     *
     * @param score score add total score
     */
    void addScore(int score);

    /**
     * remove score for score player game
     *
     * @param score score for remove to total
     */
    void removeScore(int score);

    /**
     * @return player name
     */
    String getName();

}