package it.unicam.cs.pa.chessboardGame.games.Dama;

import it.unicam.cs.pa.chessboardGame.games.Dama.defaultBot.easyBotDama;
import it.unicam.cs.pa.chessboardGame.structure.game;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.player;

import java.util.*;

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

    private final Map<String, damaPlayer> players;

    private gameBoard board;

    private final List<damaPlayer> orderPlayer;

    public damaGame(String descInformation, damaPlayer player1, damaPlayer player2) {

        if (player1 == null) player1 = new easyBotDama();
        if (player2 == null) player2 = new easyBotDama();

        this.id = UUID.randomUUID();
        this.name = "Dama";
        this.players = new HashMap<>();
        this.players.put(player1.getId(), player1);
        this.players.put(player2.getId(), player2);
        this.board = new damaBoard(8, 8, player1, player2);

        this.orderPlayer = new ArrayList<>();
        orderPlayer.add(player1);
        orderPlayer.add(player2);

        this.information = descInformation;
    }

    public damaGame(String descInformation) {
        this.id = UUID.randomUUID();
        this.name = "Dama";
        this.information = descInformation;
        this.players = new HashMap<>();
        this.orderPlayer = new ArrayList<>();
    }

    public void start() {
        if (readyToStart())
            new damaCLI(this);
        else
            throw new IllegalArgumentException("the game isn't ready to start");
    }

    /**
     * check player of game
     *
     * @return true if game is ready to start else false
     */
    private boolean readyToStart() {
        List<damaPlayer> players = new ArrayList<>(this.players.values());
        if (players.stream().filter(damaPlayer -> damaPlayer instanceof easyBotDama).toList().size() < 2) {
            if (players.size() == 2) {
                orderPlayer.addAll(players);
                this.board = new damaBoard(8, 8, players.get(0), players.get(1));
                return true;
            }
        }
        return false;
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
    public player getLiveWin() {
        return this.players.values().stream().max(Comparator.comparing(damaPlayer::getScore)).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void setPlayers(List<player> players) {
        if (players == null)
            throw new NullPointerException("players is null");
        if (players.size() == 0)
            throw new IllegalArgumentException("players is empty");
        this.players.clear();
        players.forEach(player -> this.players.put(player.getId(), (damaPlayer) player));
    }

    @Override
    public Collection<player> getPlayers() {
        return new ArrayList<>(this.players.values());
    }

    @Override
    public player getPlayer(String idPlayer) {
        if (idPlayer == null)
            throw new NullPointerException("players id is null");
        if (idPlayer.isEmpty())
            throw new IllegalArgumentException("players id is empty");
        if (this.players.containsKey(idPlayer))
            return this.players.get(idPlayer);
        else throw new IllegalArgumentException("the player isn't contained");
    }

    @Override
    public void addPlayer(player player) {
        if (players == null)
            throw new NullPointerException("players is null");
        if (this.players.containsKey(player.getId()))
            throw new IllegalArgumentException("players is already contained  ");
        this.players.put(player.getId(), (damaPlayer) player);
    }

    @Override
    public String getInformationGame() {
        return this.information;
    }

    @Override
    public void restart() {
        for (damaPlayer p : this.players.values())
            p.removeScore(p.getScore());
        this.board.clearBoard();
    }



    @Override
    public player getWin() {
        ArrayList<player> lp = new ArrayList<>(this.players.values());
        if (this.getBoard().getPawns().stream().filter(pawn -> pawn.getOwner() == lp.get(0)).toList().isEmpty())
            return lp.get(1);
        if (this.getBoard().getPawns().stream().filter(pawn -> pawn.getOwner() == lp.get(1)).toList().isEmpty())
            return lp.get(0);
        return null;
    }

    @Override
    public player nextPlayer() {
        player p = this.orderPlayer.get(1);
        Collections.reverse(this.orderPlayer);
        return p;
    }
}