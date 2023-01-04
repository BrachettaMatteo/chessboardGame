package it.unicam.cs.pa.chessboardGame.games.dama.movements;

import it.unicam.cs.pa.chessboardGame.games.dama.damaPawn;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;

/**
 * dama Movement of pawn
 *
 * @author Matteo Brachetta
 * @version 0.1
 */
public class damaMovement extends defaultMovements {

    public damaMovement(gameBoard gb, damaPawn pawn) {
        super(gb, pawn);
    }

    @Override
    public void forwardRight() {
        if (this.pawn.getType())
            this.directionColumn = super.RIGHT;
        else this.directionColumn = super.LEFT;
        super.directionRow = super.BOTTOM;
        super.forwardRight();
    }

    @Override
    public void forwardLeft() {
        if (this.pawn.getType())
            this.directionColumn = super.LEFT;
        else this.directionColumn = super.RIGHT;
        super.directionRow = super.BOTTOM;
        super.forwardLeft();
    }

    @Override
    public void backRight() {
        super.directionRow = super.BOTTOM;
        this.directionColumn = super.RIGHT;
        this.checkBasicMove();
    }

    @Override
    public void backLeft() {
        super.directionRow = super.BOTTOM;
        this.directionColumn = super.LEFT;
        this.checkBasicMove();
    }

    @Override
    public boolean isAvailableToMove() {
        if (super.isAvailableToMove())
            return true;
        else return super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), LEFT, BOTTOM) ||
                super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), RIGHT, BOTTOM);
    }

    @Override
    public void randomMove() {
    }
}



