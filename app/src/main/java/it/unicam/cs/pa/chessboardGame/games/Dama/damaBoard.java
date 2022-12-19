package it.unicam.cs.pa.chessboardGame.games.Dama;

import it.unicam.cs.pa.chessboardGame.structure.*;

import java.util.UUID;

/**
 * @author Matteo Brachetta
 * @version 0.0
 */
public class damaBoard implements pawnBoard {
    private final UUID id;

    public damaBoard() {
        this.id = UUID.randomUUID();
    }

    @Override
    public void getPawn(position position) {
        //TODO: implement damaBoard.getPawn
    }

    @Override
    public pawn getPawn(String idPawn) {
        //TODO: implement damaBoard.getPawn
        return null;
    }

    @Override
    public boolean isFree(position position) {
        //TODO: implement damaBoard.isFree
        return false;
    }

    @Override
    public boolean updatePosition(position position, pawn pawn) {
        //TODO: implement damaBoard.updatePosition
        return false;
    }

    @Override
    public boolean addPawn(position position, pawn pawn) {
        //TODO: implement damaBoard.addPawn
        return false;
    }

    @Override
    public void goDeletionPawn(String idPawn) {
        //TODO: implement damaBoard.goDeletionPawn

    }

    @Override
    public void goDeletionPawn(position position) {
        //TODO: implement damaBoard.goDeletionPawn

    }

    @Override
    public void clearBoard() {
        //TODO: implement damaBoard.clearBoard
    }
}