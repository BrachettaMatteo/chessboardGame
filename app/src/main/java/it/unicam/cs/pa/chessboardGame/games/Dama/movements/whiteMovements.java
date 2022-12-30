package it.unicam.cs.pa.chessboardGame.games.Dama.movements;

import it.unicam.cs.pa.chessboardGame.games.Dama.damaPawn;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.movement;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.position;

public class whiteMovements implements movement {
    private final gameBoard gb;
    private final pawn pawn;

    public whiteMovements(gameBoard gb, pawn pawn) {
        this.gb = gb;
        this.pawn = pawn;
    }

    @Override
    public void forwardRight() {
        position position = this.gb.getPositionPawn(pawn.getId());
        position newposition = new position(position.getColumn() + 1, position.getRow() + 1);
        verifyPosition(newposition, 1);
    }

    private void verifyPosition(position newposition, int incrementColumn) {
        position nextPosition = newposition;
        if (this.gb.isFree(newposition))
            this.gb.updatePosition(newposition, pawn);
        else {
            if (this.isNotFriend(newposition)) {
                nextPosition = new position(newposition.getColumn() + incrementColumn, newposition.getRow() + 1);
                if (this.gb.isFree(nextPosition)) {
                    this.gb.freePosition(newposition);
                    this.gb.updatePosition(nextPosition, pawn);
                    nextPosition = new position(newposition.getColumn() + incrementColumn, newposition.getRow() + 1);

                    if (!this.gb.isFree(nextPosition))
                        this.checkNextPosition();

                } else
                    this.searchNewPosition(nextPosition, incrementColumn, 1);
            }

        }


    }

    private void checkNextPosition() {
        position nextPosition = gb.getPositionPawn(pawn.getId());
        position rightPosition = new position(nextPosition.getColumn() + 1, nextPosition.getRow() + 1);
        position leftPosition = new position(nextPosition.getColumn() - 1, nextPosition.getRow() + 1);
        try {
            if (!gb.isFree(leftPosition)) {
                this.verifyPosition(leftPosition, 1);
                if (!gb.isFree(rightPosition))
                    this.verifyPosition(rightPosition, -1);
            } else if (!gb.isFree(rightPosition))
                this.verifyPosition(rightPosition, -1);
            else if (!gb.isFree(leftPosition))
                this.verifyPosition(leftPosition, 1);

        } catch (IllegalArgumentException e) {
            //  Block of code to handle errors
        }

    }


    private boolean isNotFriend(position newposition) {
        return !((damaPawn) this.gb.getPawn(newposition)).getType();
    }

    private void searchNewPosition(position position, int incrementColumn, int incrementRow) {
        position.setColumn(position.getColumn() + incrementColumn);
        position.setRow(position.getRow() + incrementRow);
        position newposition = new position(position.getColumn() + incrementColumn, position.getRow() + incrementRow);

        if (this.gb.isFree(position) && this.gb.isFree(newposition))
            this.gb.updatePosition(position, pawn);
        else this.searchNewPosition(newposition, incrementColumn, incrementRow);

    }

    @Override
    public void forwardLeft() {
        position position = this.gb.getPositionPawn(pawn.getId());
        position newposition = new position(position.getColumn() - 1, position.getRow() + 1);
        verifyPosition(newposition, -1);
    }
}
