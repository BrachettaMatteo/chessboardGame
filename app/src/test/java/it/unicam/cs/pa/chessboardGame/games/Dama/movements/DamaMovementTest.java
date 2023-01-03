package it.unicam.cs.pa.chessboardGame.games.Dama.movements;

import it.unicam.cs.pa.chessboardGame.games.Dama.damaGame;
import it.unicam.cs.pa.chessboardGame.games.Dama.damaPawn;
import it.unicam.cs.pa.chessboardGame.games.Dama.damaPlayer;
import it.unicam.cs.pa.chessboardGame.structure.game;
import it.unicam.cs.pa.chessboardGame.structure.position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


/**
 * Test of dama pawn
 *
 * @author Matteo Brachetta
 * @version 0.1
 */
class DamaMovementTest {

    private final damaPlayer playerWhiteTest = new damaPlayer("player white Test");
    private damaPawn damaPawnWhite1;
    private damaPawn pawnWhite2;

    private final damaPlayer playerBlackTest = new damaPlayer("player black Test");
    private damaPawn pawnBlack1;
    private damaPawn pawnBlack2;

    private game gd = new damaGame("...", playerWhiteTest, playerBlackTest);

    @BeforeEach
    void setUp() {
        gd = new damaGame("...", playerWhiteTest, playerBlackTest);
        damaPawnWhite1 = new damaPawn(0, gd.getBoard(), "*", playerWhiteTest, true);
        damaPawnWhite1.setMovement(new damaMovement(gd.getBoard(), damaPawnWhite1));
        pawnWhite2 = new damaPawn(0, gd.getBoard(), "*", playerWhiteTest, true);

        pawnBlack1 = new damaPawn(0, gd.getBoard(), "•", playerBlackTest, false);
        pawnBlack2 = new damaPawn(0, gd.getBoard(), "•", playerBlackTest, false);
    }

    @Test
    void backRight() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite1);

        damaPawnWhite1.getMovement().backRight();
        assertEquals(new position(6, 4), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(6, 4), pawnBlack1);
        damaPawnWhite1.getMovement().backRight();
        assertEquals(new position(7, 3), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
    }

    @Test
    void backLeftMultipleCapture() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(7, 7), damaPawnWhite1);
        gd.getBoard().addPawn(new position(6, 6), pawnBlack1);
        gd.getBoard().addPawn(new position(4, 4), pawnBlack2);

        damaPawnWhite1.getMovement().backLeft();

        assertEquals(new position(3, 3), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertTrue(this.checkEliminationPawn(pawnBlack2));
    }

    @Test
    void backRightMultipleCapture() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(2, 7), damaPawnWhite1);
        gd.getBoard().addPawn(new position(3, 6), pawnBlack1);
        gd.getBoard().addPawn(new position(5, 4), pawnBlack2);
        damaPawnWhite1.getMovement().backRight();

        assertEquals(new position(6, 3), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertTrue(this.checkEliminationPawn(pawnBlack2));
    }

    @Test
    void backRightMargin() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(7, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(8, 4), pawnBlack1);
        damaPawnWhite1.getMovement().backRight();
        assertEquals(new position(7, 5), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertFalse(this.checkEliminationPawn(pawnBlack1));
    }

    @Test
    void backLeftMargin() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(2, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(1, 4), pawnBlack1);
        damaPawnWhite1.getMovement().backLeft();
        assertEquals(new position(2, 5), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertFalse(this.checkEliminationPawn(pawnBlack1));
    }

    @Test
    void backLeft() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite1);

        damaPawnWhite1.getMovement().backLeft();
        assertEquals(new position(4, 4), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(4, 4), pawnBlack1);
        damaPawnWhite1.getMovement().backLeft();
        assertEquals(new position(3, 3), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
    }


    @Test
    void backLeftFriend() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(4, 4), pawnWhite2);

        damaPawnWhite1.getMovement().backLeft();
        assertEquals(new position(5, 5), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertFalse(this.checkEliminationPawn(pawnWhite2));
    }

    @Test
    void backRightFriend() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite2);
        damaPawnWhite1.getMovement().backRight();
        assertEquals(new position(5, 5), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertFalse(this.checkEliminationPawn(pawnWhite2));
    }

    /**
     * Check pawn is eliminated
     *
     * @param pawnToCheck pawn to check eliminated
     * @return true if pawn is eliminated else false
     */
    private boolean checkEliminationPawn(damaPawn pawnToCheck) {
        return this.gd.getBoard().getEliminated().contains(pawnToCheck);
    }
}