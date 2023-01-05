package it.unicam.cs.pa.chessboardGame.games.dama.movements;

import it.unicam.cs.pa.chessboardGame.games.dama.damaGame;
import it.unicam.cs.pa.chessboardGame.games.dama.damaPawn;
import it.unicam.cs.pa.chessboardGame.games.dama.damaPlayer;
import it.unicam.cs.pa.chessboardGame.structure.game;
import it.unicam.cs.pa.chessboardGame.structure.position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;


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
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().backRight());
        assertEquals(new position(7, 5), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertFalse(this.checkEliminationPawn(pawnBlack1));
    }

    @Test
    void backLeftMargin() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(2, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(1, 4), pawnBlack1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().backLeft());
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
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().backLeft());
        assertFalse(this.checkEliminationPawn(pawnWhite2));
    }

    @Test
    void backRightFriend() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite2);

        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().backRight());
        assertFalse(this.checkEliminationPawn(pawnWhite2));
    }

    @Test
    void multiCaptureBackRightTest() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(3, 6), damaPawnWhite1);
        gd.getBoard().addPawn(new position(4, 5), pawnBlack1);
        gd.getBoard().addPawn(new position(4, 3), pawnBlack2);
        damaPawnWhite1.getMovement().backRight();

        assertEquals(new position(3, 2), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertTrue(this.checkEliminationPawn(pawnBlack2));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(2, 4), damaPawnWhite1);
        gd.getBoard().addPawn(new position(3, 3), pawnBlack1);
        gd.getBoard().addPawn(new position(3, 1), pawnBlack2);
        damaPawnWhite1.getMovement().backRight();

        assertEquals(new position(4, 2), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertFalse(this.checkEliminationPawn(pawnBlack2));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(1, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(2, 4), pawnBlack1);
        gd.getBoard().addPawn(new position(4, 2), pawnBlack2);
        damaPawnWhite1.getMovement().backRight();
        assertEquals(new position(5, 1), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertTrue(this.checkEliminationPawn(pawnBlack2));

    }

    @Test
    @DisplayName("Random move")
    void randomMove() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(1, 1), damaPawnWhite1);
        damaPawnWhite1.getMovement().randomMove();
        assertEquals(new position(2, 2), this.getPositionPawn(damaPawnWhite1));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(1, 8), damaPawnWhite1);
        damaPawnWhite1.getMovement().randomMove();

        assertEquals(new position(2, 7), this.getPositionPawn(damaPawnWhite1));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(8, 8), damaPawnWhite1);
        damaPawnWhite1.getMovement().randomMove();
        assertEquals(new position(7, 7), this.getPositionPawn(damaPawnWhite1));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(8, 1), damaPawnWhite1);
        damaPawnWhite1.getMovement().randomMove();
        assertEquals(new position(7, 2), this.getPositionPawn(damaPawnWhite1));

        gd.getBoard().clearBoard();
        damaPawn pawnWhite3 = new damaPawn(0, this.gd.getBoard(), "*", playerWhiteTest, true);
        damaPawn pawnWhite4 = new damaPawn(0, this.gd.getBoard(), "*", playerWhiteTest, true);
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(4, 4), pawnBlack1);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite2);
        gd.getBoard().addPawn(new position(4, 6), pawnWhite3);
        gd.getBoard().addPawn(new position(6, 6), pawnWhite4);
        damaPawnWhite1.getMovement().randomMove();
        assertEquals(new position(3, 3), this.getPositionPawn(damaPawnWhite1));

        gd.getBoard().clearBoard();
        damaPawn pawnWhite5 = new damaPawn(0, this.gd.getBoard(), "*", playerWhiteTest, true);
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(4, 4), pawnWhite5);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite2);
        gd.getBoard().addPawn(new position(4, 6), pawnWhite3);
        gd.getBoard().addPawn(new position(6, 6), pawnWhite4);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().randomMove());

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(4, 4), pawnBlack1);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite2);
        gd.getBoard().addPawn(new position(4, 6), pawnWhite3);
        gd.getBoard().addPawn(new position(6, 6), pawnWhite4);
        gd.getBoard().addPawn(new position(4, 2), pawnBlack2);
        damaPawnWhite1.getMovement().randomMove();
        assertEquals(new position(5, 1), this.getPositionPawn(damaPawnWhite1));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertTrue(this.checkEliminationPawn(pawnBlack2));


        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(4, 4), pawnBlack1);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite2);
        gd.getBoard().addPawn(new position(4, 6), pawnWhite3);
        gd.getBoard().addPawn(new position(6, 6), pawnWhite4);
        gd.getBoard().addPawn(new position(2, 2), pawnBlack2);
        damaPawnWhite1.getMovement().randomMove();
        assertEquals(new position(1, 1), this.getPositionPawn(damaPawnWhite1));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertTrue(this.checkEliminationPawn(pawnBlack2));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(4, 4), pawnBlack1);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite2);
        gd.getBoard().addPawn(new position(4, 6), pawnWhite3);
        gd.getBoard().addPawn(new position(6, 6), pawnWhite4);
        gd.getBoard().addPawn(new position(3, 3), pawnBlack2);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().randomMove());
    }

    @Test
    void errorMove() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(3, 8), damaPawnWhite1);
        gd.getBoard().addPawn(new position(4, 7), pawnWhite2);

        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().backRight());
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().forwardRight());
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().forwardLeft());
        damaPawnWhite1.getMovement().backLeft();
        assertEquals(new position(2, 7), this.gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
    }

    /**
     * Returns the board position of the pawn
     *
     * @param pawn pawn to research position
     * @return the position of pawn, if not present generate exception method <code>this.gd.getBoard().getPositionPawn()</code>
     */
    private position getPositionPawn(damaPawn pawn) {
        return this.gd.getBoard().getPositionPawn(pawn.getId());
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