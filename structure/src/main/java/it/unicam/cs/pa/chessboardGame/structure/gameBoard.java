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
     * @return dimension of chessboard
     */
    int getSize();

    /**
     * Get pawn in to position game
     *
     * @param position position when located pawn
     * @return the pawn present in the position
     * @throws IllegalArgumentException the position not present in board
     */
    pawn getPawn(position position);

    /**
     * Get pawn to identifier
     *
     * @param idPawn identifier pawn
     * @return pawn pawn
     * @throws IllegalArgumentException if the pawn not present in board
     * @throws IllegalArgumentException if the idPawn is empty
     */
    pawn getPawn(String idPawn);

    /**
     * Check the position is empty
     *
     * @param position position check is free
     * @return true if box is empty else false
     * @throws IllegalArgumentException if position not present in game
     */
    boolean isFree(position position);

    /**
     * Insert in select position the pawn
     *
     * @param position position update pawn
     * @param pawn     pawn to update
     * @return true the position update
     * @throws IllegalArgumentException if position not present in game
     * @throws IllegalArgumentException if pawn not present in game
     */
    boolean updatePosition(position position, pawn pawn);

    /**
     * Adding pawn to board game
     *
     * @param position position adding pawn
     * @param pawn     pawn to add
     * @return true if added else false
     * @throws IllegalArgumentException if position not present in game
     * @throws IllegalArgumentException if pawn present in game
     */
    boolean addPawn(position position, pawn pawn);

    /**
     * Dead pawn,transport to delete pawn. Pawn get identifier
     *
     * @param idPawn identifier pawn
     * @throws IllegalArgumentException if pawn not present in game
     */
    void goDeletionPawn(String idPawn);

    /**
     * Dead pawn, transport to delete pawn. Pawn get position
     *
     * @param position position for delete pawn
     * @throws IllegalArgumentException if pawn not present in game
     */
    void goDeletionPawn(position position);

    /**
     * Clear all component on board
     */
    void clearBoard();

    /**
     * Get board position  for pawn
     *
     * @param idPawn identifier pawn
     * @return position to pawn
     * @throws IllegalArgumentException if pawn not present
     * @throws IllegalArgumentException if identifier pawn is empty
     */
    position getPositionPawn(String idPawn);

    /**
     * Free position for empty chess.
     *
     * @param position position for free
     * @throws IllegalArgumentException if position not present
     */
    void freePosition(position position);

    /**
     * Check pawn is present
     *
     * @param idPawn identifier pawn
     * @return true if present else false
     */
    boolean pawnIsPresent(String idPawn);

    /**
     * Get identifier of board
     *
     * @return identifier board
     */
    String getId();

    /**
     * Get all pawn present on board
     *
     * @return list of pawn the board
     */
    List<pawn> getPawns();

    /**
     * Restart game, move pawn in init position.
     */
    void restart();

    /**
     * Get deleted pawn
     *
     * @return list content all pawn element.
     */
    List<pawn> getEliminated();

    /**
     * Get the list of pawns belonging to the player that can move
     *
     * @param idPlayer player identifier
     * @return list of player pawn available to move or <code>null</code> if the player hasn't pawns to move.
     * @throws IllegalArgumentException if the player identifier is empty.
     * @throws IllegalArgumentException If the player pawns does not belong to the board
     */
    List<pawn> getPawnToMove(String idPlayer);
}