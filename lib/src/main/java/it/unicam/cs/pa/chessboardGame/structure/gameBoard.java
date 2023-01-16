package it.unicam.cs.pa.chessboardGame.structure;

import java.util.List;

/**
 * It represents the chessboard. It will contain all the {@code pawn} with their positions and eliminated {@code pawn}.
 *
 * @author Matteo Brachetta
 * @version 1.0
 */
public interface gameBoard {
    /**
     * Get size chessboard.
     *
     * @return size of chessboard.
     */
    int getSize();

    /**
     * Get {@code pawn} in to {@code position} game.
     *
     * @param position {@code position} when located {@code pawn}.
     * @return the {@code pawn} present in the {@code position}.
     * @throws IllegalArgumentException the {@code position} not present in board.
     */
    pawn getPawn(position position);

    /**
     * Get {@code pawn} to identifier.
     *
     * @param idPawn identifier {@code pawn}.
     * @return the {@code pawn} with id present on the board.
     * @throws IllegalArgumentException if the {@code pawn} not present in board.
     * @throws IllegalArgumentException if the {@code idPawn} is empty.
     */
    pawn getPawn(String idPawn);

    /**
     * Check the {@code position} is empty.
     *
     * @param position {@code position} to be checked if free.
     * @return {@code true} if box is empty else {@code false}.
     * @throws IllegalArgumentException if {@code position} not present in game.
     */
    boolean isFree(position position);

    /**
     * Insert in select {@code position} the {@code pawn}.
     *
     * @param position new {@code position} for the {@code pawn}.
     * @param pawn     pawn to move.
     * @return {@code true} the {@code position} update.
     * @throws IllegalArgumentException if {@code {@code position}} not present in game.
     * @throws IllegalArgumentException if {@code pawn} not present in game.
     */
    boolean updatePosition(position position, pawn pawn);

    /**
     * Adding {@code pawn} to board game.
     *
     * @param position {@code position} adding {@code pawn}.
     * @param pawn     {@code pawn} to add.
     * @return {@code true} if added else {@code false}.
     * @throws IllegalArgumentException if {@code position} not present in game.
     * @throws IllegalArgumentException if {@code pawn} present in game.
     */
    boolean addPawn(position position, pawn pawn);

    /**
     * eliminates the {@code pawn}.Get {@code pawn} to id
     *
     * @param idPawn identifier pawn.
     * @throws IllegalArgumentException if pawn not present in game.
     */
    void goDeletionPawn(String idPawn);

    /**
     * eliminates the {@code pawn}. Get {@code pawn} to {@code position}
     *
     * @param position {@code position} for delete pawn.
     * @throws IllegalArgumentException if pawn not present in game.
     */
    void goDeletionPawn(position position);

    /**
     * Clear all board. Delete all {@code pawn} to board.
     */
    void clearBoard();

    /**
     * Get board {@code position}  for {@code pawn}.
     *
     * @param idPawn identifier {@code pawn}.
     * @return {@code position} to {@code pawn}.
     * @throws IllegalArgumentException if {@code pawn}. not present.
     * @throws IllegalArgumentException if identifier {@code pawn}. is empty.
     */
    position getPositionPawn(String idPawn);

    /**
     * Free {@code position} for empty chess.
     *
     * @param position {@code position} for free
     * @throws IllegalArgumentException if {@code position} not present
     */
    void freePosition(position position);

    /**
     * Check {@code pawn} is present.
     *
     * @param idPawn identifier {@code pawn}.
     * @return {@code true} if present else {@code false}
     */
    boolean pawnIsPresent(String idPawn);

    /**
     * Get identifier of board.
     *
     * @return identifier board.
     */
    String getId();

    /**
     * Get all {@code pawn} present on board.
     *
     * @return list of {@code pawn} the board.
     */
    List<pawn> getPawns();

    /**
     * Restart game, move all {@code pawn} in init {@code position}.
     */
    void restart();

    /**
     * Get deleted {@code pawn}.
     *
     * @return list content all {@code pawn} element.
     */
    List<pawn> getEliminated();

    /**
     * Get the list of pawns belonging to the {@code player} that can move.
     *
     * @param idPlayer {@code player} identifier
     * @return list of {@code player} {@code pawn} available to move or {@code null} if the {@code player} hasn't pawns to move.
     * @throws IllegalArgumentException if the {@code player} identifier is empty.
     * @throws IllegalArgumentException If the {@code player} pawns does not belong to the board
     */
    List<pawn> getPawnToMove(String idPlayer);
}