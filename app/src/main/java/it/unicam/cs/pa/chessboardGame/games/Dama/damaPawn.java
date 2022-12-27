package it.unicam.cs.pa.chessboardGame.games.Dama;


import it.unicam.cs.pa.chessboardGame.structure.movement;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.player;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Matteo Brachetta
 * @version 0.1
 */
public class damaPawn implements pawn {

    private final UUID id;
    private final player owner;
    private int hierarchy;
    private movement moves;
    private final String symbol;
    private boolean life;

    public damaPawn(int hierarchy, movement movement, String symbol, player owner) {
        this.id = UUID.randomUUID();
        this.hierarchy = hierarchy;
        this.moves = movement;
        this.symbol = symbol;
        this.life = true;
        this.owner = owner;
    }

    @Override
    public int getHierarchy() {
        return this.hierarchy;
    }

    @Override
    public void setHierarchy(int hierarchy) {
        if (hierarchy > 0)
            this.hierarchy = hierarchy;
        throw new IllegalArgumentException("the hierarchy is too small");

    }

    @Override
    public void setMovement(movement movement) {
        if (movement == null)
            throw new NullPointerException("movement is null");
        this.moves = movement;

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

}