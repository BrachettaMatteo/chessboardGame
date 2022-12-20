package it.unicam.cs.pa.chessboardGame.games.Dama;

import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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


    public damaBoard() {
        this.id = UUID.randomUUID();
        this.board = new HashMap<>();
        this.eliminated = new HashMap<>();
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
        if (this.isFree(position) && this.pawnIsPresent(pawn.getId())) {
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

}