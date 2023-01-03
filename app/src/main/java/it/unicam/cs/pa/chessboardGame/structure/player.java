package it.unicam.cs.pa.chessboardGame.structure;

import java.util.List;

/**
 * player, he's responsible to squad pawn of game
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
     * @throws IllegalArgumentException the score is less than 0
     */
    void addScore(int score);

    /**
     * remove score for score player game
     *
     * @param score score for remove to total
     * @throws IllegalArgumentException the score is less 0
     */
    void removeScore(int score);

    /**
     * @return player name
     */
    String getName();

}