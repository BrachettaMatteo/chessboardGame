package it.unicam.cs.pa.chessboardGame.structure;

/**
 * Represents the pawn of the game. He is responsible for the proper functioning of the pawn.
 *
 * @author Matteo Brachetta
 * @version 0.0
 */
public interface pawn {

    /**
     * The pawn with a higher hierarchy wins over a pawn with a lower hierarchy.
     *
     * @return the hierarchy of the pawn
     */
    int getHierarchy();

    /**
     * setting hierarchy pawn
     *
     * @param hierarchy new hierarchy of pawn
     * @throws IllegalArgumentException the hierarchy is less than 0
     */
    void setHierarchy(int hierarchy);

    /**
     * update movement for pawn.
     *
     * @param movement new movement for pawn
     * @throws NullPointerException if movement is null
     */
    void setMovement(movement movement);

    /**
     * get pawn movement
     *
     * @return all movement for the pawn
     */
    movement getMovement();

    /**
     * get pawn identifier
     *
     * @return pawn identifier
     */
    String getId();

    /**
     * get name pawn
     *
     * @return pawn name
     */
    String getSymbol();

    /**
     * get life
     *
     * @return true if pawn is life else the pawn is dead and return false
     */
    boolean getLife();

    /**
     * setting life of pawn
     *
     * @param life true is life false is dead
     */
    void setLife(boolean life);

    /**
     * @return owner for pawn if not owner return null
     */
    player getOwner();

    /**
     * Determinate if pawn is is Available To Move, so if the pawn can make at least one move.
     *
     * @return <code>true</code> if the pawn can move else <code>false</code>.
     */

    boolean isAvailableToMove();

    /**
     * Get type of pawn. The pawn are two possible type true or false
     *
     * @return true if the positive type or false
     */
    boolean getType();
}