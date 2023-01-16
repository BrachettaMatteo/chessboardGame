package it.unicam.cs.pa.chessboardGame.app.games.dama.movements;

import it.unicam.cs.pa.chessboardGame.app.games.dama.damaPawn;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.movement;
import it.unicam.cs.pa.chessboardGame.structure.position;

/**
 * Default Movement of pawn
 *
 * @author Matteo Brachetta
 * @version 1.0
 */
public class defaultMovements implements movement {
    final int MAX_CAPTURE_ONE_MOVE = 3;
    final int RIGHT = 1;
    final int LEFT = -1;
    final int TOP = 1;
    final int BOTTOM = -1;
    /**
     * Indicate the <code>gameBoard</code> will content pawn.
     */
    protected final gameBoard gb;
    /**
     * Indicate the <code>pawn</code> to apply the move
     */
    protected final damaPawn pawn;
    /**
     * Indicate the direction of column, The direction is <code>RIGHT</code> or <code>LEFT</code>
     */
    protected int directionColumn;
    /**
     * Indicate the direction of row, The direction is <code>TOP</code> or <code>BOTTOM</code>
     */
    protected int directionRow;
    /**
     * Counts the captures made by the pawn
     */
    private int counterCapture;

    /**
     * Construction for <code>default movement</code>
     *
     * @param gb   <code>gameBoard</code> to which the pawn belongs.
     * @param pawn pawn to update movement.
     */

    public defaultMovements(gameBoard gb, damaPawn pawn) {
        this.gb = gb;
        this.pawn = pawn;
        this.counterCapture = 0;
    }


    @Override
    public void forwardRight() {
        if (!this.checkDamaPawn()) this.setDirection(RIGHT);
        if (this.correctMove()) {
            this.checkBasicMove();
        } else throw new IllegalArgumentException("movement not correct");

    }

    /**
     * Setting direction.
     *
     * @param directionColumn reference direction move.
     */
    private void setDirection(int directionColumn) {
        if (pawn.getType()) {
            this.directionColumn = directionColumn;
            this.directionRow = TOP;
        } else {
            this.directionColumn = Math.negateExact(directionColumn);
            this.directionRow = BOTTOM;

        }
    }


    @Override
    public void forwardLeft() {
        if (!this.checkDamaPawn()) this.setDirection(LEFT);
        if (this.correctMove()) {
            this.checkBasicMove();
        } else throw new IllegalArgumentException("movement not correct");


    }

    /**
     * Check the position is correct for move.
     *
     * @return {@code true} if position is correct else {@code false}.
     */
    protected boolean correctMove() {
        position current = this.gb.getPositionPawn(this.pawn.getId());
        position verifyPosition = new position(current.getColumn() + this.directionColumn, current.getRow() + this.directionRow);
        try {
            if (this.gb.isFree(verifyPosition)) return true;
            else if (this.notFriend(verifyPosition)) return true;

        } catch (IllegalArgumentException e) {
            return false;
        }
        return false;
    }


    /**
     * Check basic movement and if necessary calls for capture.
     *
     * @throws IllegalArgumentException if the movement not consent
     */
    protected void checkBasicMove() {
        this.counterCapture = 0;
        position currentPosition = this.gb.getPositionPawn(pawn.getId());
        position nextPosition = this.getNewPosition(currentPosition, directionColumn);
        try {
            if (this.checkDama(nextPosition)) {
                this.pawn.setMovement(new damaMovement(this.gb, this.pawn));
                this.gb.updatePosition(nextPosition, this.pawn);
            }
            if (this.gb.isFree(nextPosition)) this.gb.updatePosition(nextPosition, this.pawn);
            else if (this.notFriend(nextPosition)) {
                position nextPosition1 = this.getNewPosition(nextPosition, directionColumn);
                if (this.gb.isFree(nextPosition1))
                    this.capture(nextPosition);
                else throw new IllegalArgumentException("this movement isn't perms");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("movement error");
        }
    }

    /**
     * Check the position content pawn not friends or friends, two pawns are friends when they have the same type.
     *
     * @param positionToPawn position of pawn to be verified
     * @return {@code true} is not friend else {@code false}
     */
    protected boolean notFriend(position positionToPawn) {
        try {
            return this.gb.getPawn(positionToPawn).getType() != this.pawn.getType();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check and execute the capture. If possible, it  performs consecutive captures
     * for max <code>MAX_CAPTURE_ONE_MOVE</code>.
     * If the position final is position for dama it upgrades pawn to dama pawn.
     *
     * @param nextPosition position for check new possible capture
     */
    protected void capture(position nextPosition) {
        if (this.counterCapture <= MAX_CAPTURE_ONE_MOVE) {
            if (this.checkDama(nextPosition)) {
                if (this.gb.isFree(nextPosition)) {
                    //update pawn to damaPawn
                    this.pawn.setMovement(new damaMovement(this.gb, this.pawn));

                    this.gb.updatePosition(nextPosition, pawn);

                }
            } else if (this.notFriend(nextPosition)) {
                position nextPosition1 = this.getNewPosition(nextPosition, directionColumn);

                if (nextPosition1 != null && this.gb.isFree(nextPosition1)) {
                    this.gb.updatePosition(nextPosition, pawn);
                    if (this.checkDama(nextPosition1)) this.pawn.setMovement(new damaMovement(this.gb, this.pawn));
                    this.gb.updatePosition(nextPosition1, pawn);
                    this.counterCapture++;
                    this.checkAnotherCapture();
                }
            }
        }
    }

    /**
     * Check if capture is possible for the nearby location, if it is possible to call actions for capture.
     */
    private void checkAnotherCapture() {
        position currentPosition = this.gb.getPositionPawn(this.pawn.getId());
        position rigthPosition = this.getNewPosition(currentPosition, RIGHT);
        position leftPosition = this.getNewPosition(currentPosition, LEFT);
        try {
            if (rigthPosition != null && !this.gb.isFree(rigthPosition) && this.notFriend(rigthPosition)) {
                position nextRight1 = this.getNewPosition(rigthPosition, RIGHT);
                if (nextRight1 != null && this.gb.isFree(nextRight1)) {
                    this.choiceMove(RIGHT);
                    return;
                }
            }
            if (leftPosition != null && !this.gb.isFree(leftPosition) && this.notFriend(leftPosition)) {
                position nextLeft1 = this.getNewPosition(leftPosition, LEFT);
                if (nextLeft1 != null && this.gb.isFree(nextLeft1)) {
                    this.choiceMove(LEFT);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Choice action for capture based on the direction row and colum.
     * It calls the correction action to move.
     *
     * @param direction direction of the horizontal, column
     */
    private void choiceMove(int direction) {
        if (this.pawn.getType()) {
            if (this.checkDamaPawn()) {
                this.directionColumn = direction;
                this.checkBasicMove();
            } else if (direction == RIGHT) this.forwardRight();
            else this.forwardLeft();
        } else {
            if (this.checkDamaPawn()) {
                if (direction == RIGHT) this.directionColumn = RIGHT;
                if (direction == LEFT) this.directionColumn = LEFT;
                this.checkBasicMove();
            } else if (direction == RIGHT) this.forwardLeft();
            else this.forwardRight();
        }
    }


    /**
     * Check the pawn is dama or not. It uses the <code>this.pawn</code>.
     *
     * @return {@code true} the pawn is dama else {@code false}
     */
    private boolean checkDamaPawn() {
        return this.pawn.getMovement() instanceof damaMovement;
    }

    /**
     * Check the position is position for upgrade the pawn in dama.
     *
     * @param positionToCheck position for check
     * @return {@code true} if pawn dama else {@code false}
     */
    protected boolean checkDama(position positionToCheck) {
        if (pawn.getType()) return positionToCheck.getRow() == 8;
        else return positionToCheck.getRow() == 1;
    }

    /**
     * Get new correct position, create and check new position after return.
     *
     * @param currentPosition refer position for create new position
     * @param incrementColum  increment column for new position
     * @return new correct position or {@code null} if the position not correct
     */
    protected position getNewPosition(position currentPosition, int incrementColum) {
        try {
            position nextPosition = new position(currentPosition.getColumn() + incrementColum, currentPosition.getRow() + this.directionRow);
            //check position is exist
            this.gb.isFree(nextPosition);
            return nextPosition;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void randomMove() {
        if (this.isAvailableToMove()) {
            if (Math.random() <= 0.5) {
                try {
                    this.forwardRight();
                } catch (Exception e) {
                    this.forwardLeft();
                }
            } else try {
                this.forwardLeft();
            } catch (Exception e) {
                this.forwardRight();

            }
        } else throw new IllegalArgumentException("error  default random movement");

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
     * Create the position when parameter and check position is free.
     *
     * @param p               position to start
     * @param directionColumn increment column for new position
     * @param directionRow    increment row for new position
     * @return {@code true} if the new position is empty else {@code false}
     */
    protected boolean checkPosition(position p, int directionColumn, int directionRow) {
        try {
            position nextPosition = new position(p.getColumn() + directionColumn, p.getRow() + directionRow);
            if (this.gb.isFree(nextPosition)) return true;
            if (this.notFriend(nextPosition)) {
                position nextPosition1 = new position(nextPosition.getColumn() + directionColumn, nextPosition.getRow() + directionRow);
                return this.gb.isFree(nextPosition1);
            }
            return this.gb.isFree(nextPosition) || this.notFriend(nextPosition);
        } catch (Exception e) {
            return false;
        }
    }
}
