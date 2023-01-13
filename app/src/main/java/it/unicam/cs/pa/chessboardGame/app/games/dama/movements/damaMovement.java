package it.unicam.cs.pa.chessboardGame.app.games.dama.movements;

import it.unicam.cs.pa.chessboardGame.app.games.dama.damaPawn;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;

import java.util.concurrent.ThreadLocalRandom;

/**
 * dama Movement of pawn
 *
 * @author Matteo Brachetta
 * @version 0.1.1
 */
public class damaMovement extends defaultMovements {
    /**
     * Construct for <code>damaMovement</code>.
     *
     * @param gb   gameBord that will contain pawn.
     * @param pawn pawn to which to apply the <code>damaMovement</code>.
     */
    public damaMovement(gameBoard gb, damaPawn pawn) {
        super(gb, pawn);
    }

    @Override
    public void forwardRight() {
        this.setDirection(TOP, RIGHT);
        super.forwardRight();
    }


    @Override
    public void forwardLeft() {
        this.setDirection(TOP, LEFT);
        super.forwardLeft();
    }

    @Override
    public void backRight() {
        this.setDirection(BOTTOM, RIGHT);
        if (super.correctMove())
            this.checkBasicMove();
        else throw new IllegalArgumentException("back-right error");
    }

    @Override
    public void backLeft() {
        this.setDirection(BOTTOM, LEFT);
        if (super.correctMove())
            this.checkBasicMove();
        else throw new IllegalArgumentException("back-left error");
    }

    @Override
    public boolean isAvailableToMove() {
        if (super.isAvailableToMove())
            return true;
        else {
            if (pawn.getType())
                return super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), LEFT, BOTTOM) ||
                        super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), RIGHT, BOTTOM);
            else return super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), LEFT, TOP) ||
                    super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), RIGHT, TOP);
        }

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

    /**
     * Check the backLeft is possible execute.
     *
     * @return <code>true</code> if the movement it'll execute else <code>false</code>
     */
    private boolean checkBackLeftMove() {
        if (this.pawn.getType()) {
            return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), LEFT, BOTTOM);
        } else return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), RIGHT, TOP);
    }

    /**
     * Check the backRight is possible execute.
     *
     * @return <code>true</code> if the movement it'll execute else <code>false</code>
     */
    private boolean checkBackRightMove() {
        if (this.pawn.getType()) {
            return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), RIGHT, BOTTOM);
        } else return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), LEFT, TOP);
    }

    /**
     * Check the forwardRight is possible move.
     *
     * @return <code>true</code> if the movement it'll execute else <code>false</code>
     */
    private boolean checkForwardRightMove() {
        if (this.pawn.getType()) {
            return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), RIGHT, TOP);
        } else return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), LEFT, BOTTOM);

    }

    /**
     * Check the forwardLeft is possible move.
     *
     * @return <code>true</code> if the movement it'll execute else <code>false</code>
     */
    private boolean checkForwardLeftMove() {
        if (this.pawn.getType())
            return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), LEFT, TOP);
        else return super.checkPosition(this.gb.getPositionPawn(this.pawn.getId()), RIGHT, BOTTOM);
    }

    /**
     * Check the back movement it'll execute.
     *
     * @return <code>true</code> if the movement it'll execute else <code>false</code>
     */
    private boolean isAvailableToBottomMove() {
        if (this.pawn.getType()) {
            return super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), LEFT, BOTTOM) ||
                    super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), RIGHT, BOTTOM);
        } else
            return super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), LEFT, TOP) ||
                    super.checkPosition(super.gb.getPositionPawn(super.pawn.getId()), RIGHT, TOP);
    }

    /**
     * Setting direction for movements.
     *
     * @param directionColum reference direction move (horizontal)
     * @param directionRow   reference direction move row (Vertical)
     */
    private void setDirection(int directionRow, int directionColum) {
        if (pawn.getType()) {
            this.directionColumn = directionColum;
            this.directionRow = directionRow;
        } else {
            this.directionColumn = Math.negateExact(directionColum);
            this.directionRow = Math.negateExact(directionRow);
        }
    }
}



