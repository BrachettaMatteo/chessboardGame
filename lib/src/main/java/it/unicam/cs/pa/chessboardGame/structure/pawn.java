package it.unicam.cs.pa.chessboardGame.structure;

import javafx.scene.image.Image;
/**
 * Represents the {@code pawn} of the game. He is responsible for the proper functioning of the {@code pawn}.
 *
 * @author Matteo Brachetta
 * @version 1.0
 */
public interface pawn {

    /**
     * The {@code pawn} with a higher hierarchy wins over a {@code pawn} with a lower hierarchy.
     *
     * @return the hierarchy of the {@code pawn}
     */
    int getHierarchy();

    /**
     * setting hierarchy {@code pawn}
     *
     * @param hierarchy new hierarchy of {@code pawn}
     * @throws IllegalArgumentException the hierarchy is less than 0
     */
    void setHierarchy(int hierarchy);

    /**
     * update movement for {@code pawn}.
     *
     * @param movement new movement for {@code pawn}
     * @throws NullPointerException if movement is {@code null}
     */
    void setMovement(movement movement);

    /**
     * get {@code pawn} movement
     *
     * @return all movement for the {@code pawn}
     */
    movement getMovement();

    /**
     * get {@code pawn} identifier
     *
     * @return {@code pawn} identifier
     */
    String getId();

    /**
     * get name {@code pawn}
     *
     * @return {@code pawn} name
     */
    String getSymbol();

    /**
     * get life
     *
     * @return {@code true} if {@code pawn} is life else the {@code pawn} is dead and return {@code false}
     */
    boolean getLife();

    /**
     * setting life of {@code pawn}
     *
     * @param life {@code true} is life {@code false} is dead
     */
    void setLife(boolean life);

    /**
     * @return owner for {@code pawn} if not owner return {@code null}
     */
    player getOwner();

    /**
     * Determinate if pawn is  Available To Move, so if the pawn can make at least one move.
     *
     * @return {@code true} if the pawn can move else {@code false}.
     */

    boolean isAvailableToMove();

    /**
     * Get type of pawn. The pawn are two possible type {@code true} or {@code false}
     *
     * @return {@code true} if the positive type or {@code false}
     */
    boolean getType();

    /**
     * Get image of pawn
     *
     * @return image for pawn
     */
    Image getImage();
}