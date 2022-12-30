package it.unicam.cs.pa.chessboardGame.games.Dama.movements;

import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.movement;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.position;

public class blackMovements implements movement {
    private final gameBoard gb;
    private final pawn pawn;

    public blackMovements(gameBoard gb, pawn pawn) {
        this.gb = gb;
        this.pawn = pawn;
    }

    @Override
    public void forwardRight() {
        position position = this.gb.getPositionPawn(pawn.getId());
        position newposition = new position(position.getColumn() - 1, position.getRow() - 1);
        verifyPosition(newposition);
    }

    private void verifyPosition(position newposition) {
        if (this.gb.isFree(newposition))
            this.gb.updatePosition(newposition, pawn);
        else {
            position checkPosition = new position(newposition.getColumn() - -1, newposition.getRow() - 1);
            if (this.gb.isFree(checkPosition)) {
                this.gb.freePosition(newposition);
                this.gb.updatePosition(checkPosition, pawn);
            } else
                this.searchNewPosition(checkPosition, -1);
        }
    }

    private void searchNewPosition(position position, int decrementColumn) {

        position.setColumn(position.getColumn() - decrementColumn);
        position.setRow(position.getRow() - 1);
        System.out.println("rec position:" + position);
        position newposition = new position(position.getColumn() - decrementColumn, position.getRow() - 1);

        if (this.gb.isFree(position) && this.gb.isFree(newposition))
            this.gb.updatePosition(position, pawn);
        else this.searchNewPosition(newposition, decrementColumn);

    }

    @Override
    public void forwardLeft() {
        position position = this.gb.getPositionPawn(pawn.getId());
        System.out.println(position);
        position newposition = new position(position.getColumn() - 1, position.getRow() - 1);
        System.out.println(newposition);
        verifyPosition(newposition);
    }
}
