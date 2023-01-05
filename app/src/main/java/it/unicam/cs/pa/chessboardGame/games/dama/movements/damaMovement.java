package it.unicam.cs.pa.chessboardGame.games.dama.movements;

import it.unicam.cs.pa.chessboardGame.games.dama.damaPawn;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;

import java.util.concurrent.ThreadLocalRandom;

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
        if (this.pawn.getType()) {
            this.directionColumn = super.RIGHT;
            super.directionRow = super.TOP;
        } else {
            this.directionColumn = super.LEFT;
            super.directionRow = super.BOTTOM;
        }

        super.forwardRight();
    }

    @Override
    public void forwardLeft() {
        if (this.pawn.getType()) {
            this.directionColumn = super.LEFT;
            super.directionRow = super.TOP;
        } else {
            this.directionColumn = super.RIGHT;
            super.directionRow = super.BOTTOM;
        }
        super.forwardLeft();
    }

    @Override
    public void backRight() {
        if (pawn.getType()) {
            super.directionRow = super.BOTTOM;
            this.directionColumn = super.RIGHT;
        } else {
            super.directionRow = super.TOP;
            this.directionColumn = super.LEFT;
        }

        this.checkBasicMove();
    }

    @Override
    public void backLeft() {
        if (pawn.getType()) {
            super.directionRow = super.BOTTOM;
            super.directionColumn = super.LEFT;
        } else {
            super.directionRow = super.TOP;
            super.directionColumn = super.RIGHT;
        }

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
        if (this.isAvailableToMove()) {
            // get random move
            int move = ThreadLocalRandom.current().nextInt(1, 4 + 1);

            if (move <= 2) {
                if (super.isAvailableToMove()) {
                    if (move == 1) {
                        if (this.checkForwardRightMove()) {
                            this.forwardRight();
                        } else this.randomMove();
                    }
                    if (move == 2) {
                        if (this.checkForwardLeftMove()) {
                            this.forwardLeft();
                        } else this.randomMove();
                    }
                } else {
                    this.randomMove();
                }

            } else {
                if (this.isAvailableToBottomMove()) {
                    if (move == 3) {
                        if (this.checkBackRightMove()) {
                            this.backRight();
                        } else this.randomMove();
                    }
                    if (move == 4) {
                        if (this.checkBackLeftMove()) {
                            this.backLeft();
                        } else this.randomMove();
                    }
                } else {
                    this.randomMove();
                }
            }
        } else throw new IllegalArgumentException("Random move cannot be executed");


    }

    private boolean checkBackLeftMove() {
        if (this.pawn.getType()) {
            return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), LEFT, BOTTOM);
        } else return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), RIGHT, TOP);
    }

    private boolean checkBackRightMove() {
        if (this.pawn.getType()) {
            return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), RIGHT, BOTTOM);
        } else return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), LEFT, TOP);
    }

    private boolean checkForwardRightMove() {
        if (this.pawn.getType()) {
            return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), RIGHT, TOP);
        } else return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), LEFT, BOTTOM);

    }

    /**
     * check the forwardLeft is possible move
     *
     * @return true if move is possible else false
     */
    private boolean checkForwardLeftMove() {
        if (this.pawn.getType())
            return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), LEFT, TOP);
        else return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), RIGHT, BOTTOM);
    }

    private boolean isAvailableToBottomMove() {
        if (this.pawn.getType()) {
            return super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), LEFT, BOTTOM) ||
                    super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), RIGHT, BOTTOM);
        } else
            return super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), LEFT, TOP) ||
                    super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), RIGHT, TOP);

    }

}



