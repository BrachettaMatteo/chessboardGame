package it.unicam.cs.pa.chessboardGame.games.Dama.movements;

import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.movement;
import it.unicam.cs.pa.chessboardGame.structure.position;

public class damaMovement implements movement {
    @Override
    public position forwardRight(position positionStart, gameBoard boardGame) {
        return movement.super.forwardRight(positionStart, boardGame);
    }

    @Override
    public position forwardLeft(position positionStart, gameBoard boardGame) {
        return movement.super.forwardLeft(positionStart, boardGame);
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
