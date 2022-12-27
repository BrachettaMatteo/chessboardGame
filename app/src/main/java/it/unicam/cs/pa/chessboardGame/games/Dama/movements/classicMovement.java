package it.unicam.cs.pa.chessboardGame.games.Dama.movements;

import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.movement;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.position;

public class classicMovement implements movement {

    @Override
    public void forwardRight(gameBoard boardGame, pawn pawn) {
        System.out.println(pawn.getId());
        position position = boardGame.getPositionPawn(pawn.getId());
        position newposition = new position(position.getRow() + 1, position.getColumn() + 1);
        verifyPosition(newposition, boardGame, pawn, 1);

    }

    private void verifyPosition(position newposition, gameBoard boardGame, pawn pawn, int increment) {

        if (boardGame.isFree(newposition))
            boardGame.updatePosition(newposition, pawn);
        else
            this.searchNewPosition(newposition, boardGame, pawn, increment);
    }

    private void searchNewPosition(position position, gameBoard boardGame, pawn pawn, int increment) {

        position.setColumn(position.getColumn() + increment);
        position.setRow(position.getRow() + increment);

        position newposition = new position(position.getColumn() + increment, position.getRow() + increment);
        if (boardGame.isFree(position) && boardGame.isFree(newposition))
            boardGame.updatePosition(position, pawn);
        else this.searchNewPosition(newposition, boardGame, pawn, increment);

    }

    @Override
    public position forwardLeft(position positionStart, gameBoard boardGame) {
        return null;
    }
}
