package it.unicam.cs.pa.chessboardGame.games.Dama.movements;

import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.movement;
import it.unicam.cs.pa.chessboardGame.structure.position;

public class classicMovement implements movement {
    @Override
    public position forwardRight(position positionStart, gameBoard boardGame) {
        position newposition = new position(positionStart.getRow() + 1, positionStart.getColumn() + 1);
        verifyPosition(newposition, boardGame);

        return newposition;

    }

    private void verifyPosition(position newposition, gameBoard boardGame) {
        int newRow = newposition.getRow();
        int newCol = newposition.getColumn();
        int dead = 0;
        while (!boardGame.isFree(newposition)) {
            newRow += 1;
            newCol += 1;
            newposition.setRow(newRow);
            newposition.setColumn(newCol);
            dead++;
        }
        while (dead != 1) {
            boardGame.goDeletionPawn(new position(newRow - 1, newCol - 1));
            dead--;
        }
    }

    @Override
    public position forwardLeft(position positionStart, gameBoard boardGame) {
        int newRow = positionStart.getRow() + -1;
        int newCol = positionStart.getColumn() + 1;

        return new position(newRow, newCol);
    }
}
