package it.unicam.cs.pa.chessboardGame.app.games.dama;


import it.unicam.cs.pa.chessboardGame.app.games.dama.movements.damaMovement;
import it.unicam.cs.pa.chessboardGame.app.games.dama.movements.defaultMovements;
import it.unicam.cs.pa.chessboardGame.structure.*;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Matteo Brachetta
 * @version 1.0
 */
public class damaPawn implements pawn {

    private final UUID id;
    private final player owner;
    private final boolean type;
    private int hierarchy;
    private movement moves;
    private String symbol;
    private boolean life;
    private File img;

    /**
     * Constructor for <code>damaPawn</code>
     *
     * @param hierarchy hierarchy for pawn. It identifies the power respect other.
     * @param board     <code>gameBoard</code> which contains content.
     * @param symbol    symbol to identify the pawn.
     * @param owner     owner of pawn.
     * @param isWhite   identify the player is white, {@code true} if pawn is white else {@code false}
     */
    public damaPawn(int hierarchy, gameBoard board, String symbol, player owner, boolean isWhite) {
        this.id = UUID.randomUUID();
        this.hierarchy = hierarchy;
        this.symbol = symbol;
        this.type = isWhite;
        this.life = true;
        this.owner = owner;
        this.moves = new defaultMovements(board, this);
        if (isWhite)
            this.img = new File("img/pawn/default/whitePawn.png");
        else
            this.img = new File("img/pawn/default/blackPawn.png");

    }

    @Override
    public int getHierarchy() {
        return this.hierarchy;
    }

    @Override
    public void setHierarchy(int hierarchy) {
        if (hierarchy > 0)
            this.hierarchy = hierarchy;
        else
            throw new IllegalArgumentException("the hierarchy is too small");

    }

    @Override
    public void setMovement(movement movement) {
        if (movement == null)
            throw new NullPointerException("movement is null");
        this.moves = movement;
        if (movement instanceof damaMovement) {
            if (this.getType())
                this.symbol = "D";
            else this.symbol = "d";
        }


    }

    @Override
    public movement getMovement() {
        return this.moves;
    }

    @Override
    public String getId() {
        return this.id.toString();
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public boolean getLife() {
        return this.life;
    }

    @Override
    public void setLife(boolean life) {
        this.life = life;

    }

    @Override
    public player getOwner() {
        return this.owner;
    }

    @Override
    public String toString() {
        return this.symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof damaPawn damaPawn)) return false;
        return Objects.equals(id, damaPawn.id);
    }

    @Override
    public boolean getType() {
        return this.type;
    }

    @Override
    public boolean isAvailableToMove() {
        return this.moves.isAvailableToMove();

    }

    /**
     * Get image of pawn
     *
     * @return image for pawn
     */
    @Override
    public Image getImage() {
        return new Image(this.img.getPath());
    }

    /**
     * Setting image for pawn.
     *
     * @param img new image.
     */
    public void setImg(File img) {
        this.img = img;
    }
}