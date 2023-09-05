package it.unicam.cs.pa.chessboardGame.structure;

import java.util.Collection;
import java.util.List;

/**
 * Represent the game. The game is composites from board and {@code player}.
 *
 * @author Matteo Brachetta
 * @version 1.0
 */
public interface game {

    /**
     * Get the chessboard associated with the game.
     *
     * @return the board game.
     */
    gameBoard getBoard();

    /**
     * Setting the new the chessboard associated with the game.
     *
     * @param board new board to be associated with the game.
     * @throws NullPointerException if the {@code board} is {@code null}
     */
    void setBoard(gameBoard board);

    /**
     * Get identifier game.
     *
     * @return the game identifier.
     */
    String getId();

    /**
     * Get name game.
     *
     * @return the name of the game.
     */
    String getName();

    /**
     * Get live win {@code player}.
     *
     * @return the winning {@code player}.
     */
    player getLiveWin();

    /**
     * Setting players for game. Replaces old players.
     *
     * @param players new list of {@code player} to add game.
     * @throws NullPointerException     if the list of {@code player} is {@code null}
     * @throws IllegalArgumentException if the list of {@code player} is empty
     */
    void setPlayers(List<player> players);

    /**
     * Get list of player game.
     *
     * @return {@code Collection} content all players for game.
     */
    Collection<player> getPlayers();

    /**
     * Get {@code player} game.
     *
     * @param id player identifier
     * @return Returns the {@code player} present in the game.
     * @throws IllegalArgumentException if idPlayer is empty
     * @throws NullPointerException     if idPlayer is {@code null}
     * @throws IllegalArgumentException if {@code player} isn't contained
     */
    player getPlayer(String id);

    /**
     * Create new {@code player} for game.
     *
     * @param name {@code player} name.
     * @throws IllegalArgumentException If the {@code player} is already contained in the game.
     * @throws NullPointerException     if the {@code player} is {@code null}
     */
    void addPlayer(String name);

    /**
     * Get information for game.
     *
     * @return the information game.
     */
    String getInformationGame();

    /**
     * Restart game. Reset score {@code player} and board.
     */
    void restart();

    /**
     * Check and start game.
     *
     * @throws IllegalArgumentException if the game can't start
     */
    void start();

    /**
     * Get win {@code player}.
     *
     * @return the {@code player} win or {@code null}
     */
    player getWin();

    /**
     * Get list name movement for show in UI.
     * The movement accept are:{@code [forward,forwardRight,forwardLeft,back,backRight,backLeft,left,right].}
     *
     * @return list content all possible movement.
     */
    List<String> getNameAllPossibleMove();

}