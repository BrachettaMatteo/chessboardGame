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
     * Get chessboard.
     *
     * @return the board game.
     */
    gameBoard getBoard();

    /**
     * Setting the new board for game.
     *
     * @param newBoard new board for game
     * @throws NullPointerException if the {@code board} is {@code null}
     */
    void setBoard(gameBoard newBoard);

    /**
     * Get identifier game.
     *
     * @return  game identify.
     */
    String getId();

    /**
     * Get name game.
     *
     * @return name game.
     */
    String getName();

    /**
     * Get live win player.
     *
     * @return player win player;
     */
    player getLiveWin();

    /**
     * Setting player for game
     *
     * @param players new players
     * @throws NullPointerException     if the list of player is {@code null}
     * @throws IllegalArgumentException if the list of player is empty
     */
    void setPlayers(List<player> players);

    /**
     * Get list of player game.
     *
     * @return {@code Collection} content all players for game.
     */
    Collection<player> getPlayers();

    /**
     * Get Player game.
     *
     * @param idPlayer player identifier
     * @return player player game
     * @throws IllegalArgumentException if idPlayer is empty
     * @throws NullPointerException     if idPlayer is {@code null}
     * @throws IllegalArgumentException if player isn't contained
     */
    player getPlayer(String idPlayer);

    /**
     * Add new player for game.
     *
     * @param namePlayer new player
     * @throws IllegalArgumentException If the player is already contained in the game
     * @throws NullPointerException     if the player is {@code null}
     */
    void addPlayer(String namePlayer);

    /**
     * Get information for game.
     *
     * @return the information game.
     */
    String getInformationGame();

    /**
     * Restart game, reset score player and restart chessboard.
     */
    void restart();

    /**
     * Check and start game
     *
     * @throws IllegalArgumentException if the game can't start
     */
    void start();

    /**
     * Get win player.
     *
     * @return the player win or {@code null}
     */
    player getWin();

    /**
     * Get list name movement for show in UI.
     * The movement accept are:{@code forward, forwardRight, forwardLeft,back,backRight,backLeft,left,right.}
     *
     * @return list content all possible movement.
     */
    List<String> getNameAllPossibleMove();

}