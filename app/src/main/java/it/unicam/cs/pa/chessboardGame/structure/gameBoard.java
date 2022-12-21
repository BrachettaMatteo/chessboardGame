package it.unicam.cs.pa.chessboardGame.structure;

import java.util.List;

/**
 * Board for pawn, It responsible for correct mange pawn
 *
 * @author Matteo Brachetta
 * @version 0.0
 */
public interface gameBoard {


    /**
     * get pawn in to position game
     *
     * @param position position when located pawn
     * @throws IllegalArgumentException the position not present in board
     */
    pawn getPawn(position position);

    /**
     * get pawn to identifier
     *
     * @param idPawn identifier pawn
     * @return pawn pawn
     * @throws IllegalArgumentException if the pawn not present in board
     * @throws NullPointerException     if the idPawn is null
     */
    pawn getPawn(String idPawn);

    /**
     * check the position is empty
     *
     * @param position position check is free
     * @return true if box is empty else false
     * @throws IllegalArgumentException if position not present in game
     */
    boolean isFree(position position);

    /**
     * insert in select position the pawn
     *
     * @param position position update pawn
     * @param pawn     pawn to update
     * @return true the position update
     * @throws IllegalArgumentException if position not present in game
     */
    boolean updatePosition(position position, pawn pawn);

    /**
     * adding pawn to board game
     *
     * @param position position adding pawn
     * @param pawn     pawn to add
     * @throws IllegalArgumentException if position not present in game
     * @throws IllegalArgumentException if pawn present in game
     * @throws IllegalArgumentException if position is occupied
     */
    boolean addPawn(position position, pawn pawn);

    /**
     * dead pawn,transport to delete pawn. Pawn get identifier
     *
     * @param idPawn identifier pawn
     * @throws IllegalArgumentException if pawn not present in game
     */
    void goDeletionPawn(String idPawn);

    /**
     * dead pawn, transport to delete pawn. Pawn get position
     *
     * @param position position for delete pawn
     * @throws IllegalArgumentException if pawn not present in game
     */
    void goDeletionPawn(position position);

    /**
     * clear all component on board
     */
    void clearBoard();

    /**
     * get board position  for pawn
     *
     * @param idPawn identifier pawn
     * @return position to pawn
     * @throws IllegalArgumentException if pawn not present
     * @throws NullPointerException     if pawn is null
     * @throws IllegalArgumentException if identifier pawn is empty
     */
    position getPositionPawn(String idPawn);

    /**
     * free position for empty chees
     *
     * @param position position for free
     * @throws IllegalArgumentException if position not present
     */
    void freePosition(position position);

    /**
     * check pawn is present
     *
     * @param idPawn identifier pawn
     * @return true if present else false
     */
    boolean pawnIsPresent(String idPawn);

    /**
     * get identifier of board
     *
     * @return identifier board
     */
    String getId();

}