package it.unicam.cs.pa.chessboardGame.app.games.dama;

import it.unicam.cs.pa.chessboardGame.app.games.dama.defaultBot.easyBotDama;
import it.unicam.cs.pa.chessboardGame.structure.game;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.player;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * 8x8 checkers:
 * Italian checkers - It is played on a 8x8 checkerboard, similar to the chess board in chess, with the only difference being that it is rotated ninety degrees (the canton, i.e., the black corner square, goes to the bottom right). There are 12 checkers per players, and the players with the light pieces ('the white') starts. Pawns cannot eat the ladies, nor can they eat backwards. In the case of multiple chances to take, there is an obligation to take in the order of priority: where there are the most pieces, where there are the most valuable pieces (ladies) and where the most valuable pieces meet first. With the same number of pieces, if there is a possibility of taking with both pawn and checkers, it is obligatory to take with checkers. It is played mainly in Italy and some North African countries.
 *
 * @author Matteo Brachetta
 * @version 1.0
 */
public class damaGame implements game {
    private final String information;
    private final UUID id;
    private final String name;

    private final List<damaPlayer> players;

    private gameBoard board;

    HashSet<String> pawnMove = new HashSet<>();
    int repeatBoard = 0;

    /**
     * Complete construction for <code>damaGame</code>.
     *
     * @param descInformation description of game.
     * @param player1         first {@code player}.
     * @param player2         second {@code player}.
     */
    public damaGame(String descInformation, damaPlayer player1, damaPlayer player2) {

        if (player1 == null) player1 = new easyBotDama();
        if (player2 == null) player2 = new easyBotDama();

        this.id = UUID.randomUUID();
        this.name = "Dama";
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);

        this.board = new damaBoard(8, 8, player1, player2);

        this.information = descInformation;
        this.pawnMove = new HashSet<>(this.board.getPawns().stream().
                map(pawn::getId).toList());
    }

    /**
     * Basic Construct for <code>damaGame</code>
     *
     * @param descInformation description game
     */
    public damaGame(String descInformation) {
        this.id = UUID.randomUUID();
        this.name = "Dama";
        this.information = descInformation;
        this.players = new ArrayList<>();
    }

    public void start() {
        if (!readyToStart()) {
            this.players.add(new easyBotDama());
            this.board = new damaBoard(8, 8, this.players.get(0), this.players.get(1));
        } else throw new IllegalArgumentException("The game can't start, error player");
    }

    /**
     * Check {@code player} of game
     *
     * @return {@code true} if game is ready to start else {@code false}
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

    @Override
    public gameBoard getBoard() {
        return this.board;
    }

    @Override
    public void setBoard(gameBoard newBoard) {
        if (newBoard == null) throw new NullPointerException("The board is null");
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
        return this.players.stream().max(Comparator.comparing(damaPlayer::getScore)).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void setPlayers(List<player> players) {
        if (players == null)
            throw new NullPointerException("players is null");
        if (players.size() == 0)
            throw new IllegalArgumentException("players is empty");
        this.players.clear();
        players.forEach(player -> this.players.add((damaPlayer) player));
    }

    @Override
    public Collection<player> getPlayers() {
        return new ArrayList<>(this.players);
    }

    @Override
    public player getPlayer(String idPlayer) {
        if (idPlayer == null)
            throw new NullPointerException("players id is null");
        if (idPlayer.isEmpty())
            throw new IllegalArgumentException("players id is empty");
        if (this.playerIsPresent(idPlayer)) {
            return this.players.stream().filter(damaPlayer -> damaPlayer.getId().equals(idPlayer)).toList().get(0);
        } else throw new IllegalArgumentException("the player isn't contained");
    }

    /**
     * Check the player is present with id.
     *
     * @param idPlayer identifier player
     * @return {@code true} if present else {@code false}
     */
    private boolean playerIsPresent(String idPlayer) {
        return !this.getPlayers().stream().filter(player -> player.getId().equals(idPlayer)).toList().isEmpty();
    }

    @Override
    public void addPlayer(String namePlayer) {
        if (players == null)
            throw new NullPointerException("players is null");
        if (this.players.stream().filter(damaPlayer -> damaPlayer.getName().equals(namePlayer)).toList().isEmpty()) {
            damaPlayer dp = new damaPlayer(namePlayer);
            this.players.add(dp);
        } else
            throw new IllegalArgumentException("players is already contained  ");

    }

    @Override
    public String getInformationGame() {
        return this.information;
    }

    @Override
    public void restart() {
        for (damaPlayer p : this.players)
            p.removeScore(p.getScore());
        this.board.clearBoard();
    }


    @Override
    public player getWin() {
        if (repeatBoard <= 40) {
            ArrayList<String> listBoard = new ArrayList<>(this.board.getPawns().stream().
                    map(pawn::getId).toList());
            if (this.pawnMove.size() == listBoard.size() && this.pawnMove.containsAll(listBoard)) {
                repeatBoard++;
            } else {
                this.pawnMove = new HashSet<>(listBoard);
                repeatBoard = 0;
            }
            ArrayList<player> lp = new ArrayList<>(this.players);
            if (this.getBoard().getPawns().stream().filter(pawn -> pawn.getOwner() == lp.get(0)).toList().isEmpty()) {
                return lp.get(1);
            }
            if (this.getBoard().getPawns().stream().filter(pawn -> pawn.getOwner() == lp.get(1)).toList().isEmpty()) {
                return lp.get(0);
            }
            if (this.board.getPawns().stream().filter(pawn -> pawn.getOwner() == lp.get(0)).filter(pawn::isAvailableToMove).toList().isEmpty()) {

                return lp.get(1);
            }
            if (this.getBoard().getPawnToMove(lp.get(0).getId()).isEmpty()) {

                return lp.get(0);
            }
            return null;
        } else
            return new damaPlayer("Tied");
    }

    @Override
    public List<String> getNameAllPossibleMove() {
        String[] possibleMove = {"forward Right", "forward Left", "backLeft", "backRight"};
        return List.of(possibleMove);
    }
}