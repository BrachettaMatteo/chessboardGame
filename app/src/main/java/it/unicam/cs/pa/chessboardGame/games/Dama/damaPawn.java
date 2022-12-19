package it.unicam.cs.pa.chessboardGame.games.Dama;

import it.unicam.cs.pa.chessboardGame.structure.*;

import java.util.UUID;

/**
 * @author Matteo Brachetta
 * @version 0.0
 */
public class damaPawn implements pawn {
    private final UUID id;

    public damaPawn() {
        this.id = UUID.randomUUID();
    }

    @Override
    public int getHierarchy() {
        //TODO: implement damaPawn.getHierarchy
        return 0;
    }

    @Override
    public void setHierarchy(int hierarchy) {
        //TODO: implement damaPawn.setHierarchy

    }

    @Override
    public void setMovement(movement movement) {
        //TODO: implement damaPawn.setMovement

    }

    @Override
    public movement getMovement() {
        //TODO: implement damaPawn.getMovement
        return null;
    }

    @Override
    public String getId() {
        //TODO: implement damaPawn.getId
        return null;
    }

    @Override
    public String getName() {
        //TODO: implement damaPawn.getName
        return null;
    }

    @Override
    public boolean getLife() {
        //TODO: implement damaPawn.getLife
        return false;
    }

    @Override
    public void setLife(boolean life) {
        //TODO: implement damaPawn.setLife

    }

    @Override
    public player getOwner() {
        //TODO: implement damaPawn.getOwner
        return null;
    }
}