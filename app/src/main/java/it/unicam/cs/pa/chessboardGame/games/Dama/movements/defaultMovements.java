package it.unicam.cs.pa.chessboardGame.games.Dama.movements;

import it.unicam.cs.pa.chessboardGame.games.Dama.damaPawn;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.movement;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.position;

/**
 * Default Movement of pawn
 *
 * @author Matteo Brachetta
 * @version 0.2
 */
public class defaultMovements implements movement {

    final int RIGHT = 1;
    final int LEFT = -1;
    final int TOP = 1;
    final int BOTTOM = -1;
    private final gameBoard gb;
    private final pawn pawn;

    protected int directionColumn;

    protected int directionRow;


    public defaultMovements(gameBoard gb, pawn pawn) {
        this.gb = gb;
        this.pawn = pawn;
    }


    @Override
    public void forwardRight() {
        if (((damaPawn) pawn).getType()) {
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
        if (((damaPawn) pawn).getType()) {
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
     */
    protected void checkBasicMove() {
        position currentPosition = this.gb.getPositionPawn(pawn.getId());
        position nextPosition = this.getNewPosition(currentPosition, directionColumn);
        if (this.gb.isFree(nextPosition))
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
    protected boolean notFriend(position positionToPawn) {
        try {
            return ((damaPawn) this.gb.getPawn(positionToPawn)).getType() != ((damaPawn) this.pawn).getType();
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * check capture pawn
     *
     * @param nextPosition position for check new possible capture
     */
    protected void capture(position nextPosition) {
        if (this.checkDama(nextPosition)) {
            if (this.gb.isFree(nextPosition))
                this.gb.updatePosition(nextPosition, pawn);
        } else {
            position nextPosition1 = this.getNewPosition(nextPosition, directionColumn);
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
    protected boolean checkDama(position positionToCheck) {
        if (((damaPawn) pawn).getType())
            return positionToCheck.getRow() == 8;
        else
            return positionToCheck.getRow() == 1;
    }

    /**
     * get new correct position, create and check new position after return
     *
     * @param currentPosition position for create new position
     * @param incrementColum  increment column for new position
     * @return new correct position
     */
    protected position getNewPosition(position currentPosition, int incrementColum) {
        position nextPosition = new position(currentPosition.getColumn() + incrementColum, currentPosition.getRow() + this.directionRow);
        return fixPosition(nextPosition);

    }

    /**
     * fix position when go out board, and update directionColumn
     *
     * @param positionToFix position to fix
     * @return fix position when fix else positionToFix
     */
    protected position fixPosition(position positionToFix) {
        if (positionToFix.getColumn() < 1) {
            positionToFix.setColumn(2);
            //invert directionColumn
            this.directionColumn = LEFT;
        }
        if (positionToFix.getColumn() > 8) {
            positionToFix.setColumn(7);
            //invert directionColumn
            this.directionColumn = RIGHT;
        }
        return positionToFix;
    }

}
