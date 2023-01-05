package it.unicam.cs.pa.chessboardGame.games.dama.movements;

import it.unicam.cs.pa.chessboardGame.games.dama.damaPawn;
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
        if (!(this.pawn.getMovement() instanceof damaMovement))
            this.setDirection(RIGHT);

        if (this.correctMove()) {
            this.checkBasicMove();
        } else throw new IllegalArgumentException("movement not correct");

    }

    /**
     * Setting direction
     *
     * @param directionRow reference direction move
     */
    private void setDirection(int directionRow) {
        if (pawn.getType()) {
            if (directionRow == RIGHT) {
                this.directionRow = TOP;
                this.directionColumn = RIGHT;
            } else {
                this.directionRow = TOP;
                this.directionColumn = LEFT;
            }
        } else if (directionRow == LEFT) {
            this.directionRow = BOTTOM;
            this.directionColumn = RIGHT;
        } else {
            this.directionRow = BOTTOM;
            this.directionColumn = LEFT;
        }
    }


    @Override
    public void forwardLeft() {
        if (!(this.pawn.getMovement() instanceof damaMovement))
            this.setDirection(LEFT);
        if (this.correctMove()) {
            this.checkBasicMove();
        } else throw new IllegalArgumentException("movement not correct");


    }

    /**
     * check the position is correct for move
     *
     * @return true if position is correct else false.
     */
    private boolean correctMove() {
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
     * check basic movement and if necessary calls for capture
     *
     * @throws IllegalArgumentException if the movement not consent
     */
    protected void checkBasicMove() {
        this.counterCapture = 0;
        position currentPosition = this.gb.getPositionPawn(pawn.getId());
        position nextPosition = this.getNewPosition(currentPosition, directionColumn);
        try {
            if (this.checkDama(nextPosition)) {
                this.gb.updatePosition(nextPosition, this.pawn);
            }
            if (this.gb.isFree(nextPosition)) this.gb.updatePosition(nextPosition, this.pawn);
            else if (this.notFriend(nextPosition)) {
                position nextPosition1 = this.getNewPosition(nextPosition, directionColumn);
                if (this.gb.isFree(nextPosition1)) this.capture(nextPosition);
                else throw new IllegalArgumentException("this movement isn't perms");
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
                    if (this.checkDama(nextPosition1))
                        this.pawn.setMovement(new damaMovement(this.gb, this.pawn));

                    this.gb.updatePosition(nextPosition1, pawn);
                    this.counterCapture++;
                    this.checkAnotherCapture();
                }
            }
        }
    }

    /**
     * check if possible capture other pawn
     */
    private void checkAnotherCapture() {
        position currentPosition = this.gb.getPositionPawn(this.pawn.getId());
        position rigthPosition = this.getNewPosition(currentPosition, RIGHT);
        position leftPosition = this.getNewPosition(currentPosition, LEFT);
        try {
            if (rigthPosition != null && !this.gb.isFree(rigthPosition) && this.notFriend(rigthPosition)) {

                position next1Right = this.getNewPosition(rigthPosition, RIGHT);

                if (next1Right != null && this.gb.isFree(next1Right)) {
                    this.choiceMove(RIGHT);
                    return;
                }

            }
            if (leftPosition != null && !this.gb.isFree(leftPosition) && this.notFriend(leftPosition)) {

                position next1 = this.getNewPosition(leftPosition, LEFT);
                System.out.println("Another: l-> " + leftPosition + " nl->" + next1);
                if (next1 != null && this.gb.isFree(next1)) {
                    this.choiceMove(LEFT);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void choiceMove(int left) {
        if (this.pawn.getType()) {
            if (this.checkDamaPawn()) {
                this.directionColumn = left;
                System.out.println("pawn->" + this.gb.getPositionPawn(this.pawn.getId()));
                this.checkBasicMove();
            } else if (left == RIGHT)
                this.forwardRight();
            else this.forwardLeft();
        } else {
            if (this.checkDamaPawn()) {
                if (left == RIGHT)
                    this.directionColumn = RIGHT;
                if (left == LEFT)
                    this.directionColumn = LEFT;
                System.out.println("pawn->" + this.gb.getPositionPawn(this.pawn.getId()));
                this.checkBasicMove();
            } else if (left == RIGHT)
                this.forwardLeft();
            else this.forwardRight();
        }
    }


    /**
     * determinated if pawn is dama or not
     *
     * @return true the pawn is dama else false
     */
    private boolean checkDamaPawn() {
        return this.pawn.getMovement() instanceof damaMovement;
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
            System.out.println("Control generate pos: cP->" + currentPosition + " nP->" + nextPosition + " iR->" + this.directionRow);
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
                    System.out.println("execute right move");
                } catch (Exception e) {
                    this.forwardLeft();
                    System.out.println("execute left move");
                }
            } else try {
                this.forwardLeft();
                System.out.println("execute left move");
            } catch (Exception e) {
                this.forwardRight();
                System.out.println("execute right move");
            }
        } else throw new IllegalArgumentException("error  default random Movement");

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
