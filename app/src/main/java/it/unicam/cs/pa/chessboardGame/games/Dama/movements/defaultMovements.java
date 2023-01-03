package it.unicam.cs.pa.chessboardGame.games.Dama.movements;

import it.unicam.cs.pa.chessboardGame.games.Dama.damaPawn;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.movement;
import it.unicam.cs.pa.chessboardGame.structure.position;

/**
 * Default Movement of pawn
 *
 * @author Matteo Brachetta
 * @version 0.3
 */
public class defaultMovements implements movement {
    final int MAX_CAPTURE_ONE_MOVE = 3;
    final int RIGHT = 1;
    final int LEFT = -1;
    final int TOP = 1;
    final int BOTTOM = -1;
    protected final gameBoard gb;
    protected final damaPawn pawn;

    protected int directionColumn;

    protected int directionRow;
    private int counterCapture;


    public defaultMovements(gameBoard gb, damaPawn pawn) {
        this.gb = gb;
        this.pawn = pawn;
        this.counterCapture = 0;
    }


    @Override
    public void forwardRight() {
        this.counterCapture = 0;
        if (pawn.getType()) {
            this.directionRow = TOP;
            this.directionColumn = RIGHT;
        } else {
            this.directionRow = BOTTOM;
            this.directionColumn = LEFT;
        }
        this.checkBasicMove();
    }

    @Override
    public void forwardLeft() {
        this.counterCapture = 0;
        if (pawn.getType()) {
            this.directionRow = TOP;
            this.directionColumn = LEFT;
        } else {
            this.directionRow = BOTTOM;
            this.directionColumn = RIGHT;
        }

        this.checkBasicMove();
    }

    /**
     * check basic movement and if necessary calls for capture
     *
     * @throws IllegalArgumentException if the movement not consent
     */
    protected void checkBasicMove() {
        position currentPosition = this.gb.getPositionPawn(pawn.getId());
        position nextPosition = this.getNewPosition(currentPosition, directionColumn);
        try {
            if (this.gb.isFree(nextPosition)) this.gb.updatePosition(nextPosition, this.pawn);
            if (this.notFriend(nextPosition)) {
                this.capture(nextPosition);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("movement error");
        }
    }

    /**
     * check the position content pawn not friends or friends, two pawns are friends when they have the same type
     *
     * @param positionToPawn position of pawn to be verified
     * @return true is not friend else false
     */
    protected boolean notFriend(position positionToPawn) {
        try {
            return ((damaPawn) this.gb.getPawn(positionToPawn)).getType() != this.pawn.getType();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * check capture pawn
     *
     * @param nextPosition position for check new possible capture
     */
    protected void capture(position nextPosition) {
        if (this.counterCapture <= MAX_CAPTURE_ONE_MOVE) {
            if (this.checkDama(nextPosition)) {
                if (this.gb.isFree(nextPosition)) {
                    this.pawn.setMovement(new damaMovement(this.gb, this.pawn));
                    this.gb.updatePosition(nextPosition, pawn);
                }
            } else if (this.notFriend(nextPosition)) {
                position nextPosition1 = this.getNewPosition(nextPosition, directionColumn);

                if (nextPosition1 != null && this.gb.isFree(nextPosition1)) {
                    this.gb.updatePosition(nextPosition, pawn);
                    this.gb.updatePosition(nextPosition1, pawn);
                    this.counterCapture++;
                    if (this.checkDama(nextPosition1)) this.pawn.setMovement(new damaMovement(this.gb, this.pawn));

                    position nextPosition2 = this.getNewPosition(nextPosition1, directionColumn);
                    if (nextPosition2 != null && this.notFriend(nextPosition2)) {
                        this.capture(nextPosition2);
                    } else this.checkPossibleCapture();
                }
            }
        }
    }

    /**
     * checks the near left and right position if it's possible call
     * <code> this.forwardRight()</code> or <code> this.forwardLeft();</code>
     */
    private void checkPossibleCapture() {
        position positionRight = this.getNewPosition(this.gb.getPositionPawn(pawn.getId()), RIGHT);
        position positionLeft = this.getNewPosition(this.gb.getPositionPawn(pawn.getId()), LEFT);
        if (this.notFriend(positionRight)) if (this.pawn.getType()) this.forwardRight();
        else this.forwardLeft();
        if (this.notFriend(positionLeft)) if (this.pawn.getType()) this.forwardLeft();
        else this.forwardRight();
    }


    /**
     * verify if the pawn is transform to dama
     *
     * @param positionToCheck position to check
     * @return true if pawn dama else false
     */
    protected boolean checkDama(position positionToCheck) {
        if (pawn.getType()) return positionToCheck.getRow() == 8;
        else return positionToCheck.getRow() == 1;
    }

    /**
     * get new correct position, create and check new position after return
     *
     * @param currentPosition position for create new position
     * @param incrementColum  increment column for new position
     * @return new correct position
     */
    protected position getNewPosition(position currentPosition, int incrementColum) {
        try {
            position nextPosition = new position(currentPosition.getColumn() + incrementColum, currentPosition.getRow() + this.directionRow);
            this.gb.isFree(nextPosition);
            return nextPosition;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void randomMove() {
        if (Math.random() <= 0.5) this.forwardLeft();
        else this.forwardRight();
    }

    @Override
    public boolean isAvailableToMove() {
        position currentPosition = this.gb.getPositionPawn(pawn.getId());
        if (this.pawn.getType())
            return this.checkPosition(currentPosition, LEFT, TOP) || this.checkPosition(currentPosition, RIGHT, TOP);
        else
            return this.checkPosition(currentPosition, LEFT, BOTTOM) || this.checkPosition(currentPosition, RIGHT, BOTTOM);

    }

    /**
     * create the position when parameter and check position is free
     *
     * @param p               position to start
     * @param directionColumn increment column for new position
     * @param directionRow    increment row for new position
     * @return <code>true</code> if the new position is empty else <code>false</code>
     */
    protected boolean checkPosition(position p, int directionColumn, int directionRow) {
        try {
            position nextPosition = new position(p.getColumn() + directionColumn, p.getRow() + directionRow);
            return this.gb.isFree(nextPosition);
        } catch (Exception e) {
            return false;
        }
    }
}