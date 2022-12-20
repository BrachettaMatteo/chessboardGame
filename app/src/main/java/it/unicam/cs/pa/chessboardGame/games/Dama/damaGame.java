package it.unicam.cs.pa.chessboardGame.games.Dama;


import it.unicam.cs.pa.chessboardGame.structure.game;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Collection;


/**
 * 8x8 checkers:
 * Italian checkers - It is played on a 8x8 checkerboard, similar to the chess board in chess, with the only difference being that it is rotated ninety degrees (the canton, i.e., the black corner square, goes to the bottom right). There are 12 checkers per players, and the players with the light pieces ('the white') starts. Pawns cannot eat the ladies, nor can they eat backwards. In the case of multiple chances to take, there is an obligation to take in the order of priority: where there are the most pieces, where there are the most valuable pieces (ladies) and where the most valuable pieces meet first. With the same number of pieces, if there is a possibility of taking with both pawn and checkers, it is obligatory to take with checkers. It is played mainly in Italy and some North African countries.
 *
 * @author Matteo Brachetta
 * @version 0.1
 */
public class damaGame implements game {
    private final String information;
    private final UUID id;
    private final String name;

    private Map<String, player> players;
    private gameBoard board;

    public damaGame(damaBoard board, String descInformation) {
        this.id = UUID.randomUUID();
        this.name = "Dama";
        this.players = new HashMap<>();
        this.board = board;
        this.information = descInformation;
    }

    @Override
    public gameBoard getBoard() {
        return this.board;
    }

    @Override
    public void setBoard(gameBoard newBoard) {
        this.board = newBoard;
    }

    @Override
    public String getId() {
        return this.id.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public player getWin() {
        //TODO: Implement damaGame.getWin
        return null;
    }

    @Override
    public void setPlayers(List<player> players) {
        if (players == null)
            throw new NullPointerException("players is null");
        if (players.size() == 0)
            throw new IllegalArgumentException("players is empty");
        this.players.clear();
        players.forEach(player -> this.players.put(player.getId(), player));
    }

    @Override
    public Collection<player> getPlayers() {
        return this.players.values();
    }

    @Override
    public player getPlayer(String idPlayer) {
        if (idPlayer == null)
            throw new NullPointerException("players id is null");
        if (idPlayer.isEmpty())
            throw new IllegalArgumentException("players id is empty");
        return this.players.get(idPlayer);
    }

    @Override
    public void addPlayer(player player) {
        if (players == null)
            throw new NullPointerException("players is null");
        if (this.players.containsKey(player.getId()))
            throw new IllegalArgumentException("players is already contained  ");
        this.players.put(player.getId(), player);
    }

    @Override
    public String getInformationGame() {
        return this.information;
    }
}