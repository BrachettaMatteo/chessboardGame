package it.unicam.cs.pa.chessboardGame.app;

import it.unicam.cs.pa.chessboardGame.app.games.dama.damaBoard;
import it.unicam.cs.pa.chessboardGame.app.games.dama.damaPlayer;
import it.unicam.cs.pa.chessboardGame.app.games.dama.defaultBot.easyBotDama;
import it.unicam.cs.pa.chessboardGame.structure.game;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * game for test
 */
public class testGame implements game {
    private final String name;
    private gameBoard board;

    private final List<damaPlayer> players;

    /**
     * Constructor for <code>testGame</code>
     *
     * @param name name of name
     */
    public testGame(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    /**
     * @return the board game
     */
    @Override
    public gameBoard getBoard() {
        return this.board;
    }

    /**
     * setting the new board for game
     *
     * @param newBoard new board for game
     * @throws NullPointerException if the board is null
     */
    @Override
    public void setBoard(gameBoard newBoard) {

    }

    /**
     * get identifier game
     *
     * @return game identifier
     */
    @Override
    public String getId() {
        return null;
    }

    /**
     * get playerName game
     *
     * @return playerName game
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * get live win player
     *
     * @return player win
     */
    @Override
    public player getLiveWin() {
        return null;
    }

    /**
     * setting player for game
     *
     * @param players new players
     * @throws NullPointerException     if the list of player is null
     * @throws IllegalArgumentException if the list of player is empty
     */
    @Override
    public void setPlayers(List<player> players) {

    }

    /**
     * get list of player game
     *
     * @return all players for game
     */
    @Override
    public Collection<player> getPlayers() {
        return null;
    }

    /**
     * get Player game
     *
     * @param idPlayer player identifier
     * @return player player game
     * @throws IllegalArgumentException if idPlayer is empty
     * @throws NullPointerException     if idPlayer is null
     * @throws IllegalArgumentException if player isn't contained
     */
    @Override
    public player getPlayer(String idPlayer) {
        return null;
    }

    /**
     * add new player for game
     *
     * @throws IllegalArgumentException If the player is already contained in the game
     * @throws NullPointerException     if the player is null
     */
    @Override
    public void addPlayer(String namePlayer) {
        if (players.isEmpty())
            throw new NullPointerException("players is null");
        if (this.players.stream().filter(damaPlayer -> damaPlayer.getName().equals(namePlayer)).toList().isEmpty()) {
            damaPlayer dp = new damaPlayer(namePlayer);
            this.players.add(dp);
        } else
            throw new IllegalArgumentException("players is already contained  ");

    }

    /**
     * get information for game
     *
     * @return the information game
     */
    @Override
    public String getInformationGame() {
        return null;
    }

    /**
     * restart game, restart board
     */
    @Override
    public void restart() {

    }

    /**
     * start game
     */
    @Override
    public void start() {
        if (!readyToStart()) {
            this.players.add(new easyBotDama());
            this.board = new damaBoard(8, 8, this.players.get(0), this.players.get(1));
        }
    }

    /**
     * check the board is ready to start. It checks the player
     *
     * @return <code>true</code> if the game is able to start else <code>false</code>
     */
    public boolean readyToStart() {
        if (players.stream().filter(damaPlayer -> damaPlayer instanceof easyBotDama).toList().size() < 2) {
            if (players.size() == 2) {
                this.board = new damaBoard(8, 8, players.get(0), players.get(1));
                return true;
            }
        }
        return false;
    }


    /**
     * return the win player
     *
     * @return the player win or null
     */
    @Override
    public player getWin() {
        return null;
    }

    /**
     * Get list playerName movement for show in UI.
     * <p>
     * The movement accept are:<code>[forward, forwardRight, forwardLeft,back,backRight,backLeft,left,right.]</code>
     *
     * @return list content all possible movement.
     */
    @Override
    public List<String> getNameAllPossibleMove() {
        String[] possibleMove = {"Right", "Left", "Back", "forward"};
        return List.of(possibleMove);
    }
}
