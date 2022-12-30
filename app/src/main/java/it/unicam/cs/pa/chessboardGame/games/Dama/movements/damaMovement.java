package it.unicam.cs.pa.chessboardGame.games.Dama.movements;

import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.movement;
import it.unicam.cs.pa.chessboardGame.structure.position;

public class damaMovement implements movement {
    @Override
    public void forwardRight() {
        //TODO: implement damaMovement.forwardRight
    }

    @Override
    public void forwardLeft() {
        //TODO: implement damaMovement.forwardLeft
    }

    @Override
    public position backRight(position positionStart, gameBoard boardGame) {
        return movement.super.backRight(positionStart, boardGame);
    }

    @Override
    public position backLeft(position positionStart, gameBoard boardGame) {
        return movement.super.backLeft(positionStart, boardGame);
    }
}
