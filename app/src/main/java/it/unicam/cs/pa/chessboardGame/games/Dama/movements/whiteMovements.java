package it.unicam.cs.pa.chessboardGame.games.Dama.movements;

import it.unicam.cs.pa.chessboardGame.games.Dama.damaPawn;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.movement;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.position;

public class whiteMovements implements movement {
    private final gameBoard gb;
    private final pawn pawn;
    /**
     * indicate direction, -1 left, 1 right
     */
    private int direction;

    public whiteMovements(gameBoard gb, pawn pawn) {
        this.gb = gb;
        this.pawn = pawn;
    }

    @Override
    public void forwardRight() {
        this.direction = 1;
        this.checkBasicMove();

    }

    @Override
    public void forwardLeft() {
        this.direction = -1;
        this.checkBasicMove();
    }

    /**
     * check basic movement and if necessary calls for capture
     */
    private void checkBasicMove() {
        position currentPosition = this.gb.getPositionPawn(pawn.getId());
        position nextPosition = this.getNewPosition(currentPosition, direction);
        position nextPosition1 = this.getNewPosition(nextPosition, direction);
        if (this.gb.isFree(nextPosition) && this.gb.isFree(nextPosition1))
            this.gb.updatePosition(nextPosition, this.pawn);
        if (this.notFriend(nextPosition))
            this.capture(nextPosition);

    }

    /**
     * check the position content pawn not friends or friends, two pawns are friends when they have the same type
     *
     * @param positionToPawn position of pawn to be verified
     * @return true is not friend else false
     */
    private boolean notFriend(position positionToPawn) {
        try {
            return !((damaPawn) this.gb.getPawn(positionToPawn)).getType();
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * check capture pawn
     *
     * @param nextPosition position for check new possible capture
     */
    private void capture(position nextPosition) {
        if (this.checkDama(nextPosition))
            this.gb.updatePosition(nextPosition, pawn);
        else {
            position nextPosition1 = this.getNewPosition(nextPosition, direction);
            if (this.gb.isFree(nextPosition))
                if (this.gb.isFree(nextPosition1))
                    this.gb.updatePosition(nextPosition, pawn);
            if (this.notFriend(nextPosition)) {
                this.gb.updatePosition(nextPosition, this.pawn);
                capture(nextPosition1);
            }
        }
    }

    /**
     * verify if the pawn is transform to dama
     *
     * @param positionToCheck position to check
     * @return true if pawn dama else false
     */
    private boolean checkDama(position positionToCheck) {
        if (positionToCheck.getRow() == 8)
            return this.gb.isFree(positionToCheck);
        return false;
    }

    /**
     * get new correct position, create and check new position after return
     *
     * @param currentPosition position for create new position
     * @param incrementColum  increment column for new position
     * @return new correct position
     */
    private position getNewPosition(position currentPosition, int incrementColum) {
        position nextPosition = new position(currentPosition.getColumn() + incrementColum, currentPosition.getRow() + 1);
        return fixPosition(nextPosition);

    }

    /**
     * fix position when go out board, and update direction
     *
     * @param positionToFix position to fix
     * @return fix position when fix else positionToFix
     */
    private position fixPosition(position positionToFix) {
        if (positionToFix.getColumn() < 1) {
            positionToFix.setColumn(2);
            //invert direction
            this.direction *= -1;
        }
        if (positionToFix.getColumn() > 8) {
            positionToFix.setColumn(7);
            //invert direction
            this.direction *= -1;
        }
        return positionToFix;
    }
}