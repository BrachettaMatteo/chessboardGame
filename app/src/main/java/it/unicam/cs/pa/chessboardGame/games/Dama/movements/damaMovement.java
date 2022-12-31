package it.unicam.cs.pa.chessboardGame.games.Dama.movements;

import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.pawn;

/**
 * dama Movement of pawn
 *
 * @author Matteo Brachetta
 * @version 0.1
 */
public class damaMovement extends defaultMovements {

    public damaMovement(gameBoard gb, pawn pawn) {
        super(gb, pawn);
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
}


