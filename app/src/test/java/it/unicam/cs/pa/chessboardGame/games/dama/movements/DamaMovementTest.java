package it.unicam.cs.pa.chessboardGame.games.dama.movements;

import it.unicam.cs.pa.chessboardGame.games.dama.damaGame;
import it.unicam.cs.pa.chessboardGame.games.dama.damaPawn;
import it.unicam.cs.pa.chessboardGame.games.dama.damaPlayer;
import it.unicam.cs.pa.chessboardGame.structure.game;
import it.unicam.cs.pa.chessboardGame.structure.position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test of dama pawn
 *
 * @author Matteo Brachetta
 * @version 0.1
 */
class DamaMovementTest {

    private final damaPlayer playerWhiteTest = new damaPlayer("player white Test");
    private final damaPlayer playerBlackTest = new damaPlayer("player black Test");
    private damaPawn damaPawnWhite;
    private damaPawn pawnWhite1;
    private damaPawn pawnWhite2;
    private damaPawn pawnWhite3;

    private damaPawn damaPawnBlack;
    private damaPawn pawnBlack1;
    private damaPawn pawnBlack2;
    private damaPawn pawnBlack3;

    private game gd = new damaGame("...", playerWhiteTest, playerBlackTest);

    @BeforeEach
    void setUp() {
        gd = new damaGame("...", playerWhiteTest, playerBlackTest);
        damaPawnWhite = new damaPawn(0, gd.getBoard(), "*", playerWhiteTest, true);
        damaPawnWhite.setMovement(new damaMovement(gd.getBoard(), damaPawnWhite));
        pawnWhite1 = new damaPawn(0, gd.getBoard(), "*", playerWhiteTest, true);
        pawnWhite2 = new damaPawn(0, gd.getBoard(), "*", playerWhiteTest, true);
        pawnWhite3 = new damaPawn(0, gd.getBoard(), "*", playerWhiteTest, true);

        damaPawnBlack = new damaPawn(0, gd.getBoard(), "•", playerBlackTest, false);
        damaPawnBlack.setMovement(new damaMovement(gd.getBoard(), damaPawnBlack));
        pawnBlack1 = new damaPawn(0, gd.getBoard(), "•", playerBlackTest, false);
        pawnBlack2 = new damaPawn(0, gd.getBoard(), "•", playerBlackTest, false);
        pawnBlack3 = new damaPawn(0, gd.getBoard(), "•", playerBlackTest, false);
    }

    @DisplayName("BackRight WhitePawn test")
    @Test
    void backRightWhitePawn() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);
        damaPawnWhite.getMovement().backRight();
        assertEquals(new position(6, 4), this.getPositionPawn(damaPawnWhite));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(6, 4), pawnBlack1);
        damaPawnWhite.getMovement().backRight();
        assertEquals(new position(7, 3), this.getPositionPawn(damaPawnWhite));
        assertTrue(this.checkEliminationPawn(pawnBlack1));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().backRight());

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(6, 4), pawnBlack1);
        gd.getBoard().addPawn(new position(7, 3), pawnBlack2);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().backRight());
    }

    @DisplayName("BackRight BlackPawn test")
    @Test
    void backRightBlackPawn() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnBlack);
        gd.getBoard().addPawn(new position(4, 6), pawnWhite1);
        damaPawnBlack.getMovement().backRight();
        assertEquals(new position(3, 7), this.getPositionPawn(damaPawnBlack));
        assertTrue(this.checkEliminationPawn(pawnWhite1));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 2), damaPawnBlack);
        gd.getBoard().addPawn(new position(4, 3), pawnWhite1);
        gd.getBoard().addPawn(new position(3, 4), pawnWhite2);
        assertThrows(IllegalArgumentException.class, () -> damaPawnBlack.getMovement().backRight());
    }


    @DisplayName("BackLeft MultipleCapture WhitePawn test")
    @Test
    void backLeftMultipleCaptureWhite() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(7, 7), damaPawnWhite);
        gd.getBoard().addPawn(new position(6, 6), pawnBlack1);
        gd.getBoard().addPawn(new position(4, 4), pawnBlack2);
        damaPawnWhite.getMovement().backLeft();

        assertEquals(new position(3, 3), this.getPositionPawn(damaPawnWhite));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertTrue(this.checkEliminationPawn(pawnBlack2));
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(7, 7), damaPawnWhite);
        gd.getBoard().addPawn(new position(6, 6), pawnBlack1);
        gd.getBoard().addPawn(new position(6, 4), pawnBlack2);
        gd.getBoard().addPawn(new position(6, 2), pawnBlack3);
        damaPawnWhite.getMovement().backLeft();
        assertEquals(new position(5, 1), this.getPositionPawn(damaPawnWhite));
    }

    @DisplayName("BackLeft MultipleCapture BlackPawn test")
    @Test
    void backLeftMultipleCaptureBlack() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(2, 2), damaPawnBlack);
        gd.getBoard().addPawn(new position(3, 3), pawnWhite1);
        gd.getBoard().addPawn(new position(5, 5), pawnWhite2);
        damaPawnBlack.getMovement().backLeft();
        assertEquals(new position(6, 6), this.getPositionPawn(damaPawnBlack));
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(2, 2), damaPawnBlack);
        gd.getBoard().addPawn(new position(3, 3), pawnWhite1);
        gd.getBoard().addPawn(new position(3, 5), pawnWhite2);
        gd.getBoard().addPawn(new position(3, 7), pawnWhite3);

        damaPawnBlack.getMovement().backLeft();

        assertEquals(new position(4, 8), this.getPositionPawn(damaPawnBlack));
        assertTrue(this.checkEliminationPawn(pawnWhite1));
        assertTrue(this.checkEliminationPawn(pawnWhite2));
        assertTrue(this.checkEliminationPawn(pawnWhite3));


        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(2, 2), damaPawnBlack);
        gd.getBoard().addPawn(new position(3, 3), pawnWhite1);
        gd.getBoard().addPawn(new position(5, 5), pawnWhite2);
        gd.getBoard().addPawn(new position(5, 7), pawnWhite3);
        damaPawnBlack.getMovement().backLeft();

        assertEquals(new position(4, 8), this.getPositionPawn(damaPawnBlack));
        assertTrue(this.checkEliminationPawn(pawnWhite1));
        assertTrue(this.checkEliminationPawn(pawnWhite2));
        assertTrue(this.checkEliminationPawn(pawnWhite3));
    }

    @DisplayName("BackRight MultipleCapture BlackPawn test")
    @Test
    void backRightMultipleCaptureBlack() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 2), damaPawnBlack);
        gd.getBoard().addPawn(new position(4, 3), pawnWhite1);
        gd.getBoard().addPawn(new position(4, 5), pawnWhite2);
        gd.getBoard().addPawn(new position(4, 7), pawnWhite3);
        damaPawnBlack.getMovement().backRight();

        assertEquals(new position(3, 8), this.getPositionPawn(damaPawnBlack));
        assertTrue(this.checkEliminationPawn(pawnWhite1));
        assertTrue(this.checkEliminationPawn(pawnWhite2));
        assertTrue(this.checkEliminationPawn(pawnWhite3));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(7, 7), damaPawnWhite);
        gd.getBoard().addPawn(new position(6, 6), pawnBlack1);
        gd.getBoard().addPawn(new position(6, 4), pawnBlack2);
        gd.getBoard().addPawn(new position(6, 2), pawnBlack3);
        damaPawnWhite.getMovement().backLeft();
        assertEquals(new position(5, 1), this.getPositionPawn(damaPawnWhite));

    }

    @DisplayName("BackRight MultipleCapture WhitePawn test")
    @Test
    void backRightMultipleCaptureWhite() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(2, 7), damaPawnWhite);
        gd.getBoard().addPawn(new position(3, 6), pawnBlack1);
        gd.getBoard().addPawn(new position(5, 4), pawnBlack2);
        damaPawnWhite.getMovement().backRight();

        assertEquals(new position(6, 3), this.getPositionPawn(damaPawnWhite));

        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertTrue(this.checkEliminationPawn(pawnBlack2));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(2, 7), damaPawnWhite);
        gd.getBoard().addPawn(new position(3, 6), pawnBlack1);
        gd.getBoard().addPawn(new position(3, 4), pawnBlack2);
        gd.getBoard().addPawn(new position(3, 2), pawnBlack3);
        this.damaPawnWhite.getMovement().backRight();

        assertEquals(new position(4, 1), this.getPositionPawn(damaPawnWhite));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertTrue(this.checkEliminationPawn(pawnBlack2));
        assertTrue(this.checkEliminationPawn(pawnBlack3));
    }

    @DisplayName("BackRight Margin WhitePawn test")
    @Test
    void backRightMarginWhite() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(7, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(8, 4), pawnBlack1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().backRight());
        assertEquals(new position(7, 5), this.getPositionPawn(damaPawnWhite));
        assertFalse(this.checkEliminationPawn(pawnBlack1));
    }

    @DisplayName("BackRight Margin BlackPawn test")
    @Test
    void backRightMarginBlack() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(2, 3), damaPawnBlack);
        gd.getBoard().addPawn(new position(1, 4), pawnWhite1);

        assertThrows(IllegalArgumentException.class, () -> damaPawnBlack.getMovement().backRight());
        assertFalse(this.checkEliminationPawn(pawnWhite1));

    }

    @DisplayName("BackLeft Margin WhitePawn test")
    @Test
    void backLeftMarginWhite() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(2, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(1, 4), pawnBlack1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().backLeft());
        assertEquals(new position(2, 5), this.getPositionPawn(damaPawnWhite));
        assertFalse(this.checkEliminationPawn(pawnBlack1));
    }

    @DisplayName("BackLeft Margin BlackPawn test")
    @Test
    void backLeftMarginBlack() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(7, 3), damaPawnBlack);
        gd.getBoard().addPawn(new position(8, 4), pawnWhite1);

        assertThrows(IllegalArgumentException.class, () -> damaPawnBlack.getMovement().backLeft());
        assertFalse(this.checkEliminationPawn(pawnWhite1));
    }

    @DisplayName("BackLeft WhitePawn test")
    @Test
    void backLeftWhite() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);

        damaPawnWhite.getMovement().backLeft();
        assertEquals(new position(4, 4), this.getPositionPawn(damaPawnWhite));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(4, 4), pawnBlack1);
        damaPawnWhite.getMovement().backLeft();

        assertEquals(new position(3, 3), this.getPositionPawn(damaPawnWhite));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(4, 4), pawnWhite1);

        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().backLeft());
        assertFalse(this.checkEliminationPawn(pawnWhite1));
        assertEquals(new position(5, 5), this.getPositionPawn(damaPawnWhite));
    }

    @DisplayName("BackLeft BlackPawn test")
    @Test
    void backLeftBlack() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 5), damaPawnBlack);

        damaPawnBlack.getMovement().backLeft();
        assertEquals(new position(6, 6), this.getPositionPawn(damaPawnBlack));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnBlack);
        gd.getBoard().addPawn(new position(6, 6), pawnBlack1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnBlack.getMovement().backLeft());
        assertEquals(new position(5, 5), this.getPositionPawn(damaPawnBlack));
        assertFalse(this.checkEliminationPawn(pawnBlack1));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnBlack);
        gd.getBoard().addPawn(new position(6, 6), pawnWhite1);
        damaPawnBlack.getMovement().backLeft();
        assertEquals(new position(7, 7), this.getPositionPawn(damaPawnBlack));
        assertTrue(this.checkEliminationPawn(pawnWhite1));

    }

    @DisplayName("BackLeft move Friend PawnWhite")
    @Test
    void backLeftFriendWhite() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(4, 4), pawnWhite1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().backLeft());
        assertFalse(this.checkEliminationPawn(pawnWhite1));
        assertEquals(new position(5, 5), this.getPositionPawn(damaPawnWhite));
    }

    @DisplayName("BackLeft move Friend PawnBlack")
    @Test
    void backLeftFriendBlack() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 5), damaPawnBlack);
        gd.getBoard().addPawn(new position(6, 6), pawnBlack1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().backLeft());
        assertFalse(this.checkEliminationPawn(pawnBlack1));
        assertEquals(new position(5, 5), this.getPositionPawn(damaPawnBlack));
    }

    @DisplayName("BackRight move Friend PawnWhite")
    @Test
    void backRightFriendWhite() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite1);

        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().backRight());
        assertFalse(this.checkEliminationPawn(pawnWhite1));
        assertEquals(new position(5, 5), this.getPositionPawn(damaPawnWhite));
    }

    @DisplayName("BackRight move Friend PawnBlack")
    @Test
    void backRightFriendBlack() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 5), damaPawnBlack);
        gd.getBoard().addPawn(new position(4, 6), pawnBlack1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnBlack.getMovement().backRight());
        assertFalse(this.checkEliminationPawn(pawnBlack1));
        assertEquals(new position(5, 5), this.getPositionPawn(damaPawnBlack));
    }

    @DisplayName("MultiCapture BackRight PawnWhite Test")
    @Test
    void multiCaptureBackRightTestWhite() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(3, 6), damaPawnWhite);
        gd.getBoard().addPawn(new position(4, 5), pawnBlack1);
        gd.getBoard().addPawn(new position(4, 3), pawnBlack2);
        damaPawnWhite.getMovement().backRight();

        assertEquals(new position(3, 2), this.getPositionPawn(damaPawnWhite));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertTrue(this.checkEliminationPawn(pawnBlack2));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(2, 4), damaPawnWhite);
        gd.getBoard().addPawn(new position(3, 3), pawnBlack1);
        gd.getBoard().addPawn(new position(3, 1), pawnBlack2);
        damaPawnWhite.getMovement().backRight();

        assertEquals(new position(4, 2), this.getPositionPawn(damaPawnWhite));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertFalse(this.checkEliminationPawn(pawnBlack2));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(1, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(2, 4), pawnBlack1);
        gd.getBoard().addPawn(new position(4, 2), pawnBlack2);
        damaPawnWhite.getMovement().backRight();
        assertEquals(new position(5, 1), this.getPositionPawn(damaPawnWhite));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertTrue(this.checkEliminationPawn(pawnBlack2));

    }

    @Test
    @DisplayName("Random move White Pawn")
    void randomMoveWhite() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(1, 1), damaPawnWhite);
        damaPawnWhite.getMovement().randomMove();
        assertEquals(new position(2, 2), this.getPositionPawn(damaPawnWhite));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(1, 8), damaPawnWhite);
        damaPawnWhite.getMovement().randomMove();

        assertEquals(new position(2, 7), this.getPositionPawn(damaPawnWhite));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(8, 8), damaPawnWhite);
        damaPawnWhite.getMovement().randomMove();
        assertEquals(new position(7, 7), this.getPositionPawn(damaPawnWhite));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(8, 1), damaPawnWhite);
        damaPawnWhite.getMovement().randomMove();
        assertEquals(new position(7, 2), this.getPositionPawn(damaPawnWhite));

        gd.getBoard().clearBoard();
        damaPawn pawnWhite3 = new damaPawn(0, this.gd.getBoard(), "*", playerWhiteTest, true);
        damaPawn pawnWhite4 = new damaPawn(0, this.gd.getBoard(), "*", playerWhiteTest, true);
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(4, 4), pawnBlack1);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite1);
        gd.getBoard().addPawn(new position(4, 6), pawnWhite3);
        gd.getBoard().addPawn(new position(6, 6), pawnWhite4);
        damaPawnWhite.getMovement().randomMove();
        assertEquals(new position(3, 3), this.getPositionPawn(damaPawnWhite));

        gd.getBoard().clearBoard();
        damaPawn pawnWhite5 = new damaPawn(0, this.gd.getBoard(), "*", playerWhiteTest, true);
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(4, 4), pawnWhite5);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite1);
        gd.getBoard().addPawn(new position(4, 6), pawnWhite3);
        gd.getBoard().addPawn(new position(6, 6), pawnWhite4);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().randomMove());

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(4, 4), pawnBlack1);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite1);
        gd.getBoard().addPawn(new position(4, 6), pawnWhite3);
        gd.getBoard().addPawn(new position(6, 6), pawnWhite4);
        gd.getBoard().addPawn(new position(4, 2), pawnBlack2);
        damaPawnWhite.getMovement().randomMove();
        assertEquals(new position(5, 1), this.getPositionPawn(damaPawnWhite));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertTrue(this.checkEliminationPawn(pawnBlack2));


        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(4, 4), pawnBlack1);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite1);
        gd.getBoard().addPawn(new position(4, 6), pawnWhite3);
        gd.getBoard().addPawn(new position(6, 6), pawnWhite4);
        gd.getBoard().addPawn(new position(2, 2), pawnBlack2);
        damaPawnWhite.getMovement().randomMove();
        assertEquals(new position(1, 1), this.getPositionPawn(damaPawnWhite));
        assertTrue(this.checkEliminationPawn(pawnBlack1));
        assertTrue(this.checkEliminationPawn(pawnBlack2));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite);
        gd.getBoard().addPawn(new position(4, 4), pawnBlack1);
        gd.getBoard().addPawn(new position(6, 4), pawnWhite1);
        gd.getBoard().addPawn(new position(4, 6), pawnWhite3);
        gd.getBoard().addPawn(new position(6, 6), pawnWhite4);
        gd.getBoard().addPawn(new position(3, 3), pawnBlack2);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().randomMove());
    }

    @Test
    @DisplayName("Random move Black Pawn")
    void randomMoveBlack() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(8, 8), damaPawnBlack);
        damaPawnBlack.getMovement().randomMove();
        assertEquals(new position(7, 7), this.getPositionPawn(damaPawnBlack));
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(1, 8), damaPawnBlack);
        damaPawnBlack.getMovement().randomMove();
        assertEquals(new position(2, 7), this.getPositionPawn(damaPawnBlack));
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(1, 1), damaPawnBlack);
        damaPawnBlack.getMovement().randomMove();
        assertEquals(new position(2, 2), this.getPositionPawn(damaPawnBlack));
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(1, 8), damaPawnBlack);

        damaPawnBlack.getMovement().randomMove();

        assertEquals(new position(2, 7), this.getPositionPawn(damaPawnBlack));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(4, 4), damaPawnBlack);
        gd.getBoard().addPawn(new position(5, 5), pawnBlack1);
        gd.getBoard().addPawn(new position(3, 3), pawnBlack2);
        gd.getBoard().addPawn(new position(3, 5), pawnBlack3);
        gd.getBoard().addPawn(new position(5, 3), pawnWhite1);
        damaPawnBlack.getMovement().randomMove();
        assertEquals(new position(6, 2), this.getPositionPawn(damaPawnBlack));
        assertTrue(this.checkEliminationPawn(pawnWhite1));
        assertFalse(this.checkEliminationPawn(pawnBlack1));
        assertFalse(this.checkEliminationPawn(pawnBlack2));
        assertFalse(this.checkEliminationPawn(pawnBlack3));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(1, 1), damaPawnBlack);
        gd.getBoard().addPawn(new position(2, 2), pawnBlack1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnBlack.getMovement().randomMove());

    }

    @DisplayName("Error Move White test")
    @Test
    void errorMoveWhite() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(8, 8), damaPawnWhite);
        gd.getBoard().addPawn(new position(7, 7), pawnWhite1);

        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().backRight());
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().forwardRight());
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().backLeft());
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().forwardLeft());
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite.getMovement().randomMove());

    }

    @DisplayName("Error Move Black test")
    @Test
    void errorMoveBlack() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(1, 1), damaPawnBlack);
        gd.getBoard().addPawn(new position(2, 2), pawnBlack1);

        assertThrows(IllegalArgumentException.class, () -> damaPawnBlack.getMovement().backRight());
        assertThrows(IllegalArgumentException.class, () -> damaPawnBlack.getMovement().forwardRight());
        assertThrows(IllegalArgumentException.class, () -> damaPawnBlack.getMovement().forwardLeft());
        assertThrows(IllegalArgumentException.class, () -> damaPawnBlack.getMovement().backLeft());
        assertThrows(IllegalArgumentException.class, () -> damaPawnBlack.getMovement().randomMove());
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