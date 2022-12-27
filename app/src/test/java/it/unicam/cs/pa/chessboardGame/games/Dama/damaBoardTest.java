package it.unicam.cs.pa.chessboardGame.games.Dama;

import it.unicam.cs.pa.chessboardGame.games.Dama.movements.classicMovement;
import it.unicam.cs.pa.chessboardGame.structure.position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * generate automatic name test
 *
 * @author Matteo Brachetta
 * @version 0.0
 */
class damaBoardTest {

    @DisplayName("pawn to position Test")
    @Test
    void getPawn() {
        damaPlayer player = new damaPlayer("test1");
        damaBoard db = new damaBoard(8, 8, player, null);

    }

    @DisplayName("pawn to identifier Test")
    @Test
    void testGetPawn() {
    }

    @DisplayName("check position empty Test")
    @Test
    void isFree() {
    }

    @DisplayName("update position Test")
    @Test
    void updatePosition() {

    }

    @DisplayName("add Pawn Test")
    @Test
    void addPawn() {
    }

    @DisplayName("delete pawn to identifier Test")
    @Test
    void goDeletionPawn() {
    }

    @DisplayName("delete pawn to position Test")
    @Test
    void testGoDeletionPawn() {
    }

    @DisplayName("clear all pawn test")
    @Test
    void clearBoard() {
    }

    @DisplayName("position pawn Test")
    @Test
    void getPositionPawn() {
    }

    @DisplayName("check position Test")
    @Test
    void freePosition() {
    }

    @DisplayName("check pawn is present Test")
    @Test
    void pawnIsPresent() {
    }

    @DisplayName("idBoard  Test")
    @Test
    void getId() {
    }
}