package it.unicam.cs.pa.chessboardGame.games.dama;


import it.unicam.cs.pa.chessboardGame.games.dama.movements.damaMovement;
import it.unicam.cs.pa.chessboardGame.games.dama.movements.defaultMovements;
import it.unicam.cs.pa.chessboardGame.structure.*;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Matteo Brachetta
 * @version 0.1.1
 */
public class damaPawn implements pawn {

    private final UUID id;
    private final player owner;
    private final boolean type;
    private int hierarchy;
    private movement moves;
    private String symbol;
    private boolean life;

    public damaPawn(int hierarchy, gameBoard board, String symbol, player owner, boolean isWhite) {
        this.id = UUID.randomUUID();
        this.hierarchy = hierarchy;
        this.symbol = symbol;
        this.type = isWhite;
        this.life = true;
        this.owner = owner;
        this.moves = new defaultMovements(board, this);
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
        if (movement instanceof damaMovement)
            this.symbol = "D";


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

    public boolean getType() {
        return this.type;
    }

    @Override
    public boolean isAvailableToMove() {
        return this.moves.isAvailableToMove();

    }

    public void setSymbol(String d) {
        this.symbol = d;
    }
}