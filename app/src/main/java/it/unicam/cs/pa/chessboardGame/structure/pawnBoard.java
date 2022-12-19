package it.unicam.cs.pa.chessboardGame.structure;

/**
 * Board for pawn, It responsible for correct mange pawn
 *
 * @author Matteo Brachetta
 * @version 0.0
 */
public interface pawnBoard {

    /**
     * @param position position when located pawn
     */
    void getPawn(position position);

    /**
     * @param idPawn identifier pawn
     * @return pawn pawn
     */
    pawn getPawn(String idPawn);

    /**
     * @param position position check is free
     * @return true if box is empty else false
     */
    boolean isFree(position position);

    /**
     * insert in select position the select pawn
     *
     * @param position position update pawn
     * @param pawn     pawn to update
     */
    boolean updatePosition(position position, pawn pawn);

    /**
     * adding pawn to board game
     *
     * @param position position adding pawn
     * @param pawn     pawn to add
     */
    boolean addPawn(position position, pawn pawn);

    /**
     * go the pawn to deletion pawn
     *
     * @param idPawn identifier pawn
     */
    void goDeletionPawn(String idPawn);

    /**
     * dead pawn, transport to delete pawn
     *
     * @param position position for delete pawn
     */
    void goDeletionPawn(position position);

    /**
     * clear all component on board
     */
    void clearBoard();

}