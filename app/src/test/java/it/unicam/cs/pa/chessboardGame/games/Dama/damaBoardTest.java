package it.unicam.cs.pa.chessboardGame.games.Dama;

import it.unicam.cs.pa.chessboardGame.games.Dama.movements.classicMovement;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

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
class DamaBoardTest {
    private final position errorPositionBoard = new position(100, 100);
    private final position correctPosition = new position(5, 5);
    private final damaPlayer playerPawnWhite = new damaPlayer("white player test");
    private final damaPlayer playerPawnBlack = new damaPlayer("black player test");
    private final damaPawn dp = new damaPawn(0, new classicMovement(), "r", playerPawnBlack);
    private damaGame dg = new damaGame("test", playerPawnWhite, playerPawnBlack);

    @BeforeEach
    void setUp() {
        dg = new damaGame("test", playerPawnWhite, playerPawnBlack);
    }

    @DisplayName("Pawn to position Test")
    @Test
    void getPawn() {

        //The position referent on start game when black is top and player button

        assertEquals(playerPawnWhite, dg.getBoard().getPawn(new position(1, 3)).getOwner());
        assertEquals(playerPawnBlack, dg.getBoard().getPawn(new position(1, 7)).getOwner());

        assertNull(dg.getBoard().getPawn(new position(2, 1)));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().getPawn(errorPositionBoard));

    }

    @DisplayName("Pawn to identifier Test")
    @Test
    void testGetPawn() {
        for (pawn p : dg.getBoard().getPawns())
            assertEquals(p, dg.getBoard().getPawn(p.getId()));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().getPawn(""));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().getPawn(dp.getId()));

    }

    @DisplayName("Check position empty Test")
    @Test
    void isFree() {
        assertTrue(dg.getBoard().isFree(new position(4, 4)));
        assertFalse(dg.getBoard().isFree(new position(1, 1)));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().getPawn(errorPositionBoard));
    }

    @DisplayName("Update position Test")
    @Test
    void updatePosition() {
        damaPawn dpError = new damaPawn(0, new classicMovement(), "r", playerPawnBlack);

        dg.getBoard().addPawn(new position(4, 4), dp);

        assertTrue(dg.getBoard().updatePosition(new position(5, 5), dp));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().updatePosition(errorPositionBoard, dp));
        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().updatePosition(correctPosition, dpError));
    }

    @DisplayName("Add Pawn Test")
    @Test
    void addPawn() {
        //the pawn is insert if only position is free
        assertTrue(dg.getBoard().addPawn(correctPosition, dp));
        assertFalse(dg.getBoard().addPawn(correctPosition, dp));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().addPawn(errorPositionBoard, dp));
    }

    @DisplayName("Delete pawn to identifier Test")
    @Test
    void goDeletionPawn() {
        dg.getBoard().addPawn(correctPosition, dp);
        dg.getBoard().goDeletionPawn(dp.getId());
        assertFalse(dg.getBoard().getPawns().contains(dp));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().goDeletionPawn(dp.getId()));
    }

    @DisplayName("Delete pawn to position Test")
    @Test
    void testGoDeletionPawn() {
        dg.getBoard().addPawn(correctPosition, dp);
        dg.getBoard().goDeletionPawn(correctPosition);
        assertFalse(dg.getBoard().getPawns().contains(dp));

        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().goDeletionPawn(errorPositionBoard));
    }

    @DisplayName("Clear all pawn test")
    @Test
    void clearBoard() {
        dg.getBoard().addPawn(correctPosition, dp);

        dg.getBoard().clearBoard();
        assertTrue(dg.getBoard().getPawns().isEmpty());
    }

    @DisplayName("Position pawn Test")
    @Test
    void getPositionPawn() {
        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().getPositionPawn(dp.getId()));
        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().getPositionPawn(""));

        dg.getBoard().addPawn(correctPosition, dp);

        assertEquals(correctPosition, dg.getBoard().getPositionPawn(dp.getId()));

    }

    @DisplayName("Check position Test")
    @Test
    void freePosition() {
        assertThrows(IllegalArgumentException.class, () -> dg.getBoard().freePosition(errorPositionBoard));

        dg.getBoard().addPawn(correctPosition, dp);
        dg.getBoard().freePosition(correctPosition);

        assertNull(dg.getBoard().getPawn(correctPosition));

    }

    @DisplayName("Check pawn is present Test")
    @Test
    void pawnIsPresent() {
        assertFalse(dg.getBoard().pawnIsPresent(dp.getId()));

        dg.getBoard().addPawn(correctPosition, dp);

        assertTrue(dg.getBoard().pawnIsPresent(dp.getId()));
    }

    @DisplayName("IdBoard  Test")
    @Test
    void getId() {
        damaBoard db = new damaBoard(10, 10, playerPawnWhite, playerPawnWhite);

        assertNotEquals(db.getId(), dg.getBoard().getId());

        dg.setBoard(db);

        assertEquals(db.getId(), dg.getBoard().getId());
    }

    @DisplayName("Get list of pawns test")
    @Test
    void getPawns() {
        assertEquals(24, dg.getBoard().getPawns().size());
        assertFalse(dg.getBoard().getPawns().contains(null));

        dg.getBoard().addPawn(correctPosition, dp);
        assertEquals(25, dg.getBoard().getPawns().size());
        assertTrue(dg.getBoard().getPawns().contains(dp));
    }

    @DisplayName("Restart test")
    @Test
    void restart() {
        dg.getBoard().addPawn(correctPosition, dp);
        dg.getBoard().restart();

        List<pawn> lp = dg.getBoard().getPawns();
        assertEquals(24, lp.size());
        //check black pawn
        assertEquals(12, lp.stream().filter(pawn -> pawn.getOwner() == playerPawnBlack).toList().size());
        //check white pawn
        assertEquals(12, lp.stream().filter(pawn -> pawn.getOwner().equals(playerPawnWhite)).toList().size());

        //check black position
        for (int row = 8; row > 8 - 3; row--)
            if (row % 2 == 0) for (int column = 2; column <= 8; column += 2)
                assertFalse(dg.getBoard().isFree(new position(column, row)));
            else for (int column = 1; column <= 8; column += 2)
                assertFalse(dg.getBoard().isFree(new position(column, row)));

        //check white position
        for (int row = 1; row < 1 + 3; row++) {
            if (row % 2 == 0) for (int column = 2; column <= 8; column += 2)
                assertFalse(dg.getBoard().isFree(new position(column, row)));
            else for (int column = 1; column <= 8; column += 2)
                assertFalse(dg.getBoard().isFree(new position(column, row)));
        }

    }

    @DisplayName("Get eliminated test")
    @Test
    void getEliminated() {
        dg.getBoard().addPawn(correctPosition, dp);

        assertTrue(dg.getBoard().getEliminated().isEmpty());

        dg.getBoard().goDeletionPawn(dp.getId());
        assertEquals(1, dg.getBoard().getEliminated().size());
        assertTrue(dg.getBoard().getEliminated().contains(dp));
    }

}