package it.unicam.cs.pa.chessboardGame.structure;

import java.util.List;

/**
 * Represent the game it's responsible to manage the game
 *
 * @author Matteo Brachetta
 * @version 0.0
 */
public interface game {

    /**
     * @return the board game
     */
    pawnBoard getBoard();

    /**
     * setting the new board for game
     *
     * @param newBoard new board for game
     */
    void setBoard(pawnBoard newBoard);

    /**
     * @return game identifier
     */
    String getId();

    /**
     * @return name game
     */
    String getName();

    /**
     * @return player win
     */
    player getWin();

    /**
     * setting player for game
     *
     * @param players new players
     */
    void setPlayers(List<player> players);

    /**
     * @return all players for game
     */
    List<player> getPlayers();

    /**
     * @param idPlayer player identifier
     * @return player
     */
    player getPlayer(String idPlayer);

}