package it.unicam.cs.pa.chessboardGame.structure;

import java.util.Collection;
import java.util.List;

/**
 * Represent the game it's responsible to manage the game
 *
 * @author Matteo Brachetta
 * @version 0.1
 */
public interface game {

    /**
     * @return the board game
     */
    gameBoard getBoard();

    /**
     * setting the new board for game
     *
     * @param newBoard new board for game
     * @throws NullPointerException if the board is null
     */
    void setBoard(gameBoard newBoard);

    /**
     * get identifier game
     *
     * @return game identifier
     */
    String getId();

    /**
     * get name game
     *
     * @return name game
     */
    String getName();

    /**
     * get live win player
     *
     * @return player win
     */
    player getLiveWin();

    /**
     * setting player for game
     *
     * @param players new players
     * @throws NullPointerException     if the list of player is null
     * @throws IllegalArgumentException if the list of player is empty
     */
    void setPlayers(List<player> players);

    /**
     * get list of player game
     *
     * @return all players for game
     */
    Collection<player> getPlayers();

    /**
     * get Player game
     *
     * @param idPlayer player identifier
     * @return player player game
     * @throws IllegalArgumentException if idPlayer is empty
     * @throws NullPointerException     if idPlayer is null
     * @throws IllegalArgumentException if player isn't contained
     */
    player getPlayer(String idPlayer);

    /**
     * add new player for game
     *
     * @param namePlayer new player
     * @throws IllegalArgumentException If the player is already contained in the game
     * @throws NullPointerException     if the player is null
     */
    void addPlayer(String namePlayer);

    /**
     * get information for game
     *
     * @return the information game
     */
    String getInformationGame();

    /**
     * restart game, restart board
     */
    void restart();

    /**
     * Start game
     *
     * @throws IllegalArgumentException if the game can't start
     */
    void start();

    /**
     * return the win player
     *
     * @return the player win or null
     */
    player getWin();

    /**
     * Get list name movement for show in UI.
     * <p>
     * The movement accept are:<code>[forward, forwardRight, forwardLeft,back,backRight,backLeft,left,right.]</code>
     *
     * @return list content all possible movement.
     */
    List<String> getNameAllPossibleMove();

}