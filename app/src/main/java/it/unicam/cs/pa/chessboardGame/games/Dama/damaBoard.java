package it.unicam.cs.pa.chessboardGame.games.Dama;

import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.player;
import it.unicam.cs.pa.chessboardGame.structure.position;
import it.unicam.cs.pa.chessboardGame.games.Dama.movements.classicMovement;

import java.util.*;


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
    private Map<position, pawn> board;
    /**
     * Map content all eliminated pawn
     */
    private Map<String, pawn> eliminated;


    public damaBoard(int column, int row, player player1, player player2) {
        this.id = UUID.randomUUID();
        this.board = new HashMap<>();
        this.eliminated = new HashMap<>();
        this.createChess(column, row);
        this.createPawnForPlayers(player1, player2);

    }

    private void createPawnForPlayers(player player1, player player2) {
        this.createWhitePawn(player1);
        this.createBlackPawn(player2);
    }

    private void createBlackPawn(player player) {

        for (int col = 1; col <= 3; col++)
            if (col % 2 == 0)
                for (int row = 2; row <= 8; row += 2)
                    this.addPawn(new position(col, row),
                            new damaPawn(0, new classicMovement(), "*", player));
            else for (int row = 1; row <= 8; row += 2)
                this.addPawn(new position(col, row),
                        new damaPawn(0, new classicMovement(), "*", player));

    }

    private void createWhitePawn(player player) {
        for (int col = 8; col >= 6; col--)
            if (col % 2 == 0)
                for (int row = 2; row <= 8; row += 2)
                    this.addPawn(new position(col, row),
                            new damaPawn(0, new classicMovement(), "•", player));
            else
                for (int row = 1; row <= 8; row += 2)
                    this.addPawn(new position(col, row),
                            new damaPawn(0, new classicMovement(), "•", player));

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
        if (board.containsKey(position)) {
            return this.board.get(position);
        }
        throw new IllegalArgumentException("position not correct");
    }

    @Override
    public pawn getPawn(String idPawn) {
        if (idPawn == null)
            throw new NullPointerException("the idPawn is null");
        if (this.pawnIsPresent(idPawn))
            return this.board.values().stream().filter(pawn -> pawn.getId().equals(idPawn)).findFirst().get();

        throw new IllegalArgumentException("the idPawn not present in board");


    }

    @Override
    public boolean isFree(position position) {
        if (this.board.containsKey(position))
            return this.board.get(position) == null;
        throw new IllegalArgumentException("position not present");
    }

    @Override
    public boolean updatePosition(position position, pawn pawn) {
        if (this.board.containsKey(position)) {
            this.freePosition(this.getPositionPawn(pawn.getId()));
            if (this.isFree(position))
                this.board.put(position, pawn);
            else {
                this.goDeletionPawn(this.board.get(position).getId());
                this.board.put(position, pawn);
            }
            return this.board.get(position) == pawn;
        }
        throw new IllegalArgumentException("position isn't correct");

    }

    @Override
    public boolean addPawn(position position, pawn pawn) {
        if (this.isFree(position)) {
            this.board.put(position, pawn);
            return this.board.containsKey(pawn);
        }
        return false;
    }

    @Override
    public void goDeletionPawn(String idPawn) {
        if (this.pawnIsPresent(idPawn)) {
            this.eliminated.put(idPawn, this.getPawn(idPawn));
            this.freePosition(this.getPositionPawn(idPawn));
        }
        throw new IllegalArgumentException("the pawn isn't present");
    }


    @Override
    public void goDeletionPawn(position position) {
        if (this.board.containsKey(position)) {
            pawn p = this.board.get(position);
            this.eliminated.put(p.getId(), p);
            this.freePosition(position);
        }
        throw new IllegalArgumentException("the position isn't correct");

    }

    @Override
    public void clearBoard() {
        this.board.keySet().forEach((position p) -> this.board.put(p, null));
    }

    @Override
    public position getPositionPawn(String idPawn) {
        if (idPawn == null) throw new NullPointerException("the idPawn is null");
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
        if (this.board.containsKey(position))
            this.board.put(position, null);
        throw new IllegalArgumentException("position isn't correct");
    }

    @Override
    public boolean pawnIsPresent(String idPawn) {

        return this.board.values().stream().noneMatch(pawn -> pawn.getId().equals(idPawn));
    }

    @Override
    public String getId() {
        return this.id.toString();
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        int c = 0;
        List<position> positionList = new ArrayList<>(this.board.keySet());
        Collections.sort(positionList);
        for (position p : positionList) {
            if (p.getColumn() != c)
                out.append("\n");
            String namePawn = "";
            if (this.board.get(p) == null) {
                namePawn = " ";
            } else {
                namePawn = this.board.get(p).toString();
            }
           String cell = "|" + namePawn + "|";
            out.append(cell);
            c = p.getColumn();
        }
        return out.toString();
    }


}