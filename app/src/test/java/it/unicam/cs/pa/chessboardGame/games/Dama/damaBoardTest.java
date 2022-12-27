package it.unicam.cs.pa.chessboardGame.games.Dama;

import it.unicam.cs.pa.chessboardGame.defaultBot.easyBot;
import it.unicam.cs.pa.chessboardGame.games.Dama.movements.classicMovement;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test of dama board
 *
 * @author Matteo Brachetta
 * @version 0.1
 */
class damaBoardTest {
    position errorPositionBoard = new position(100, 100);
    position correctPosition = new position(5, 5);
    damaPlayer playerPawnWhite = new damaPlayer("white player test");
    damaPlayer playerPawnBlack = new damaPlayer("black player test");
    damaPawn dp = new damaPawn(0, new classicMovement(), "r", playerPawnBlack);


    @DisplayName("pawn to position Test")
    @Test
    void getPawn() {
        damaGame dg = new damaGame("test", playerPawnWhite, playerPawnBlack);

        //The position referent on start game when black is top and player button

        assertEquals(playerPawnWhite, dg.getBoard().getPawn(new position(1, 3)).getOwner());
        assertEquals(playerPawnBlack, dg.getBoard().getPawn(new position(1, 7)).getOwner());

        assertNull(dg.getBoard().getPawn(new position(2, 1)));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().getPawn(errorPositionBoard));

    }

    @DisplayName("pawn to identifier Test")
    @Test
    void testGetPawn() {
        damaGame dg = new damaGame("test", playerPawnWhite, playerPawnBlack);
        for (pawn p : dg.getBoard().getPawns())
            assertEquals(p, dg.getBoard().getPawn(p.getId()));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().getPawn(""));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().getPawn(dp.getId()));

    }

    @DisplayName("check position empty Test")
    @Test
    void isFree() {
        damaGame dg = new damaGame("test", new easyBot(), new easyBot());

        assertTrue(dg.getBoard().isFree(new position(4, 4)));
        assertFalse(dg.getBoard().isFree(new position(1, 1)));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().getPawn(errorPositionBoard));
    }

    @DisplayName("update position Test")
    @Test
    void updatePosition() {
        damaGame dg = new damaGame("test", new easyBot(), new easyBot());
        damaPawn dpError = new damaPawn(0, new classicMovement(), "r", playerPawnBlack);

        dg.getBoard().addPawn(new position(4, 4), dp);

        assertTrue(dg.getBoard().updatePosition(new position(5, 5), dp));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().updatePosition(errorPositionBoard, dp));
        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().updatePosition(correctPosition, dpError));
    }

    @DisplayName("add Pawn Test")
    @Test
    void addPawn() {
        damaGame dg = new damaGame("test", new easyBot(), new easyBot());

        //the pawn is insert if only position is free
        assertTrue(dg.getBoard().addPawn(correctPosition, dp));
        assertFalse(dg.getBoard().addPawn(correctPosition, dp));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().addPawn(errorPositionBoard, dp));
    }

    @DisplayName("delete pawn to identifier Test")
    @Test
    void goDeletionPawn() {
        damaGame dg = new damaGame("test", new easyBot(), new easyBot());

        dg.getBoard().addPawn(correctPosition, dp);
        dg.getBoard().goDeletionPawn(dp.getId());
        assertFalse(dg.getBoard().getPawns().contains(dp));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().goDeletionPawn(dp.getId()));
    }

    @DisplayName("delete pawn to position Test")
    @Test
    void testGoDeletionPawn() {
        damaGame dg = new damaGame("test", new easyBot(), new easyBot());

        dg.getBoard().addPawn(correctPosition, dp);
        dg.getBoard().goDeletionPawn(correctPosition);
        assertFalse(dg.getBoard().getPawns().contains(dp));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().goDeletionPawn(errorPositionBoard));
    }

    @DisplayName("clear all pawn test")
    @Test
    void clearBoard() {
        damaGame dg = new damaGame("test", new easyBot(), new easyBot());

        dg.getBoard().addPawn(correctPosition, dp);

        dg.getBoard().clearBoard();
        assertTrue(dg.getBoard().getPawns().isEmpty());
    }

    @DisplayName("position pawn Test")
    @Test
    void getPositionPawn() {
        damaGame dg = new damaGame("test", playerPawnWhite, playerPawnBlack);

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().getPositionPawn(dp.getId()));
        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().getPositionPawn(""));

        dg.getBoard().addPawn(correctPosition, dp);

        assertEquals(correctPosition, dg.getBoard().getPositionPawn(dp.getId()));

    }

    @DisplayName("check position Test")
    @Test
    void freePosition() {
        damaGame dg = new damaGame("test", playerPawnWhite, playerPawnBlack);

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().freePosition(errorPositionBoard));

        dg.getBoard().addPawn(correctPosition, dp);
        dg.getBoard().freePosition(correctPosition);

        assertNull(dg.getBoard().getPawn(correctPosition));

    }

    @DisplayName("check pawn is present Test")
    @Test
    void pawnIsPresent() {
        damaGame dg = new damaGame("test", playerPawnWhite, playerPawnBlack);

        assertFalse(dg.getBoard().pawnIsPresent(dp.getId()));

        dg.getBoard().addPawn(correctPosition, dp);

        assertTrue(dg.getBoard().pawnIsPresent(dp.getId()));
    }

    @DisplayName("idBoard  Test")
    @Test
    void getId() {
        damaGame dg = new damaGame("test", playerPawnWhite, playerPawnBlack);
        damaBoard db = new damaBoard(10, 10, playerPawnWhite, playerPawnWhite);

        assertNotEquals(db.getId(), dg.getBoard().getId());

        dg.setBoard(db);

        assertEquals(db.getId(), dg.getBoard().getId());
    }

    @DisplayName("get list of pawns test")
    @Test
    void getPawns() {
        damaGame dg = new damaGame("test", playerPawnWhite, playerPawnBlack);

        assertEquals(24, dg.getBoard().getPawns().size());
        assertFalse(dg.getBoard().getPawns().contains(null));

        dg.getBoard().addPawn(correctPosition, dp);
        assertEquals(25, dg.getBoard().getPawns().size());
        assertTrue(dg.getBoard().getPawns().contains(dp));
    }
}