package it.unicam.cs.pa.chessboardGame.app.games.dama;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.player;
import it.unicam.cs.pa.chessboardGame.structure.position;

import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * @author Matteo Brachetta
 * @version 0.1
 */
public class damaBoard implements gameBoard {

    /**
     * board identifier
     */
    private final UUID id;
    /**
     * Map content all pawn and relative position
     */
    private final Map<position, pawn> board;
    /**
     * Map content all eliminated pawn
     */
    private final Map<String, pawn> eliminated;


    private final int size;

    private final player whitePlayer;
    private final player blackPlayer;


    public damaBoard(int column, int row, player player1, player player2) {
        this.id = UUID.randomUUID();
        this.board = new HashMap<>();
        this.eliminated = new HashMap<>();
        this.size = column;
        this.createChess(column, row);
        this.blackPlayer = player2;
        this.whitePlayer = player1;
        this.createPawnForPlayers();

    }

    @Override
    public List<pawn> getPawns() {
        return this.board.values().stream().filter(Objects::nonNull).toList();
    }

    private void createPawnForPlayers() {
        this.createWhitePawn();
        this.createBlackPawn();
    }

    private void createBlackPawn() {
        for (int row = 8; row > 8 - 3; row--) {
            String symbolBlack = "•";
            if (row % 2 == 0) for (int column = 2; column <= 8; column += 2)
                this.addPawn(new position(column, row), new damaPawn(0, this, symbolBlack, this.blackPlayer, false));
            else for (int column = 1; column <= 8; column += 2)
                this.addPawn(new position(column, row), new damaPawn(0, this, symbolBlack, this.blackPlayer, false));
        }
    }

    private void createWhitePawn() {
        for (int row = 1; row < 1 + 3; row++) {
            String symbolWhite = "*";
            if (row % 2 == 0) for (int column = 2; column <= 8; column += 2)
                this.addPawn(new position(column, row), new damaPawn(0, this, symbolWhite, this.whitePlayer, true));
            else for (int column = 1; column <= 8; column += 2)
                this.addPawn(new position(column, row), new damaPawn(0, this, symbolWhite, this.whitePlayer, true));
        }
    }

    /**
     * create a board column x row
     *
     * @param column column of board
     * @param row    row of board
     */
    private void createChess(int column, int row) {
        for (int c = 1; c <= column; c++)
            for (int r = 1; r <= row; r++)
                this.board.put(new position(c, r), null);
    }


    @Override
    public pawn getPawn(position position) {
        if (board.containsKey(position)) return this.board.get(position);
        throw new IllegalArgumentException("position not correct");
    }

    @Override
    public pawn getPawn(String idPawn) {
        if (idPawn.isEmpty()) throw new IllegalArgumentException("the idPawn is null");
        if (this.pawnIsPresent(idPawn))
            return this.getPawns().stream().filter(pawn -> pawn.getId().equals(idPawn)).findAny().orElse(null);
        else throw new IllegalArgumentException("the idPawn not present in board");


    }

    @Override
    public boolean isFree(position position) {
        if (this.board.containsKey(position)) return this.board.get(position) == null;
        throw new IllegalArgumentException("position not present");
    }

    @Override
    public boolean updatePosition(position position, pawn pawn) {
        if (this.pawnIsPresent(pawn.getId())) {
            if (this.board.containsKey(position)) {
                if (!this.isFree(position)) this.goDeletionPawn(position);

                this.freePosition(this.getPositionPawn(pawn.getId()));
                this.addPawn(position, pawn);
                return this.board.get(position) == pawn;
            } else throw new IllegalArgumentException("the position isn't correct");
        }
        throw new IllegalArgumentException("the pawn isn't present");
    }

    @Override
    public boolean addPawn(position position, pawn pawn) {
        if (this.pawnIsPresent(pawn.getId()))
            throw new IllegalArgumentException("The pawn is present");
        if (this.isFree(position)) {
            this.board.put(position, pawn);
            return this.getPawn(position) == pawn;
        }
        return false;
    }

    @Override
    public void goDeletionPawn(String idPawn) {
        if (this.pawnIsPresent(idPawn)) {
            this.eliminated.put(idPawn, this.getPawn(idPawn));
            this.freePosition(this.getPositionPawn(idPawn));
        } else throw new IllegalArgumentException("the pawn isn't present");
    }


    @Override
    public void goDeletionPawn(position position) {
        if (this.board.containsKey(position)) {
            pawn p = this.board.get(position);
            this.eliminated.put(p.getId(), p);
            this.freePosition(position);
        } else throw new IllegalArgumentException("the position isn't correct");

    }

    @Override
    public void clearBoard() {
        this.board.keySet().forEach((position p) -> this.board.put(p, null));
        this.eliminated.clear();
    }

    @Override
    public position getPositionPawn(String idPawn) {
        if (idPawn.isEmpty()) throw new IllegalArgumentException("the identifier pawn is empty");

        if (this.pawnIsPresent(idPawn)) {
            ArrayList<pawn> lp = new ArrayList<>(this.board.values());
            int index = lp.indexOf(this.getPawn(idPawn));
            return (position) this.board.keySet().toArray()[index];

        }
        throw new IllegalArgumentException("the pawn isn't present");
    }

    @Override
    public void freePosition(position position) {
        if (this.board.containsKey(position)) this.board.put(position, null);
        else throw new IllegalArgumentException("position isn't correct");
    }

    @Override
    public boolean pawnIsPresent(String idPawn) {
        return this.board.values().stream().filter(Objects::nonNull).toList().stream().anyMatch(pawn -> pawn.getId().equals(idPawn));
    }

    @Override
    public String getId() {
        return this.id.toString();
    }

    @Override
    public String toString() {
        List<position> positionList = new ArrayList<>(this.board.keySet());
        List<position> row = new ArrayList<>();
        int currentRow = Collections.max(positionList).getRow();
        StringBuilder out = new StringBuilder();
        while (currentRow >= Collections.min(positionList).getColumn()) {
            int finalI = currentRow;
            row.clear();
            row.addAll(positionList.stream().filter(position -> position.getRow() == finalI).sorted().toList());
            out.append("|");
            for (position r : row)
                out.append(this.getPawn(r) == null ? " |" : this.getPawn(r) + "|");

            out.append("\n");
            currentRow--;
        }

        return out.toString();
    }

    @Override
    public void restart() {
        this.clearBoard();
        this.createChess(this.size, this.size);
        this.createPawnForPlayers();
    }

    @Override
    public List<pawn> getEliminated() {
        return eliminated.values().stream().toList();
    }

    @Override
    public List<pawn> getPawnToMove(String idPlayer) {
        if (idPlayer.isEmpty()) throw new IllegalArgumentException("the player identifier is empty ");
        if (whitePlayer.getId().equals(idPlayer) || blackPlayer.getId().equals(idPlayer)) {
            List<pawn> pp = new ArrayList<>(this.getPawns().stream().filter(pawn -> pawn.getOwner().getId().equals(idPlayer)).toList());
            return pp.stream().filter(pawn::isAvailableToMove).toList();
        } else throw new IllegalArgumentException("The player does not belong to the board");
    }
}