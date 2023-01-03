package it.unicam.cs.pa.chessboardGame.games.Dama.movements.classicMovements;

import it.unicam.cs.pa.chessboardGame.games.Dama.damaGame;
import it.unicam.cs.pa.chessboardGame.games.Dama.damaPawn;
import it.unicam.cs.pa.chessboardGame.games.Dama.damaPlayer;
import it.unicam.cs.pa.chessboardGame.games.Dama.movements.damaMovement;
import it.unicam.cs.pa.chessboardGame.structure.game;
import it.unicam.cs.pa.chessboardGame.structure.position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of black pawn
 *
 * @author Matteo Brachetta
 * @version 0.2
 */
class BlackMovementsTest {
    private final damaPlayer playerBlackTest = new damaPlayer("player black Test");
    private damaPawn damaPawnBlack1;
    private damaPawn damaPawnBlack2;
    private final damaPlayer playerWhiteTest = new damaPlayer("player white Test");
    private damaPawn damaPawnWhite1;
    private damaPawn damaPawnWhite2;
    private damaPawn damaPawnWhite3;

    private game gd = new damaGame("...", playerWhiteTest, playerBlackTest);

    @BeforeEach
    void setUp() {
        gd = new damaGame("...", playerWhiteTest, playerBlackTest);
        damaPawnWhite1 = new damaPawn(0, gd.getBoard(), "*", playerWhiteTest, true);
        damaPawnWhite2 = new damaPawn(0, gd.getBoard(), "*", playerWhiteTest, true);
        damaPawnWhite3 = new damaPawn(0, gd.getBoard(), "*", playerWhiteTest, true);

        damaPawnBlack1 = new damaPawn(0, gd.getBoard(), "•", playerBlackTest, false);
        damaPawnBlack2 = new damaPawn(0, gd.getBoard(), "•", playerBlackTest, false);

    }


    @DisplayName("Simple forward right test")
    @Test
    void simpleForwardRightTest() {
        //check error move to overflow boardGame
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(1, 8), damaPawnBlack1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnBlack1.getMovement().forwardRight());
        assertEquals(new position(1, 8), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(4, 8), damaPawnBlack1);

        damaPawnBlack1.getMovement().forwardRight();

        assertEquals(new position(3, 7), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(4, 8), damaPawnBlack1);
        gd.getBoard().addPawn(new position(3, 7), damaPawnWhite1);
        damaPawnBlack1.getMovement().forwardRight();

        assertEquals(new position(2, 6), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnWhite1));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(4, 8), damaPawnBlack1);
        gd.getBoard().addPawn(new position(3, 7), damaPawnBlack2);

        assertEquals(new position(4, 8), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
    }


    @DisplayName("Simple forward left test")
    @Test
    void simpleForwardLeftTest() {
        //check margin
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(8, 8), damaPawnBlack1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnBlack1.getMovement().forwardLeft());
        assertEquals(new position(8, 8), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(4, 8), damaPawnBlack1);
       damaPawnBlack1.getMovement().forwardLeft();

        assertEquals(new position(5, 7), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(4, 8), damaPawnBlack1);
        gd.getBoard().addPawn(new position(5, 7), damaPawnWhite1);
        damaPawnBlack1.getMovement().forwardLeft();

        assertEquals(new position(6, 6), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnWhite1));

        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(4, 8), damaPawnBlack1);
        gd.getBoard().addPawn(new position(5, 7), damaPawnBlack2);
        System.out.println(gd.getBoard());
        assertEquals(new position(4, 8), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
    }

    @Test
    @DisplayName("Multi forward margin Test")
    void MultiForwardMarginTest() {
        gd.getBoard().clearBoard();

        //margin left
        gd.getBoard().addPawn(new position(4, 8), damaPawnBlack1);
        gd.getBoard().addPawn(new position(3, 7), damaPawnWhite1);
        gd.getBoard().addPawn(new position(1, 5), damaPawnWhite2);
        damaPawnBlack1.getMovement().forwardRight();

        assertEquals(new position(2, 6), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnWhite1));
        assertFalse(this.checkEliminationPawn(damaPawnWhite2));


        gd.getBoard().clearBoard();

        //margin right
        gd.getBoard().addPawn(new position(5, 8), damaPawnBlack1);
        gd.getBoard().addPawn(new position(6, 7), damaPawnWhite1);
        gd.getBoard().addPawn(new position(8, 5), damaPawnWhite2);
        damaPawnBlack1.getMovement().forwardLeft();
        assertEquals(new position(7, 6), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnWhite1));
        assertFalse(this.checkEliminationPawn(damaPawnWhite2));

    }

    @Test
    @DisplayName("Multi forward right Test")
    void MultiForwardRightTest() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 8), damaPawnBlack1);
        gd.getBoard().addPawn(new position(4, 7), damaPawnWhite1);
        gd.getBoard().addPawn(new position(2, 5), damaPawnWhite2);
        System.out.println(gd.getBoard());
        damaPawnBlack1.getMovement().forwardRight();

        assertEquals(new position(1, 4), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnWhite1));
        assertTrue(this.checkEliminationPawn(damaPawnWhite2));

        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 8), damaPawnBlack1);
        gd.getBoard().addPawn(new position(4, 7), damaPawnWhite1);
        gd.getBoard().addPawn(new position(2, 5), damaPawnBlack2);
        System.out.println(gd.getBoard());
        damaPawnBlack1.getMovement().forwardRight();

        assertEquals(new position(3, 6), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnWhite1));

        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(6, 6), damaPawnBlack1);
        gd.getBoard().addPawn(new position(4, 4), damaPawnWhite1);
        gd.getBoard().addPawn(new position(2, 2), damaPawnWhite2);
        System.out.println(gd.getBoard());
        damaPawnBlack1.getMovement().forwardRight();

        assertEquals(new position(5, 5), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
        damaPawnBlack1.getMovement().forwardRight();
        assertEquals(new position(1, 1), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnWhite1));
        assertTrue(this.checkEliminationPawn(damaPawnWhite2));

        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(4, 4), damaPawnBlack1);
        gd.getBoard().addPawn(new position(3, 3), damaPawnWhite1);
        gd.getBoard().addPawn(new position(1, 1), damaPawnWhite2);
        System.out.println(gd.getBoard());
        damaPawnBlack1.getMovement().forwardRight();
        assertEquals(new position(2, 2), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnWhite1));
    }

    @Test
    @DisplayName("Multi forward left Test")
    void MultiForwardLeftTest() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(4, 5), damaPawnBlack1);
        gd.getBoard().addPawn(new position(5, 4), damaPawnWhite1);
        gd.getBoard().addPawn(new position(7, 2), damaPawnBlack2);
        damaPawnBlack1.getMovement().forwardLeft();

        assertEquals(new position(6, 3), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnWhite1));
    }

    @Test
    @DisplayName("Dama position is occupied")
    void damaPositionOccupied() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 4), damaPawnBlack1);
        gd.getBoard().addPawn(new position(6, 3), damaPawnWhite1);
        gd.getBoard().addPawn(new position(8, 1), damaPawnWhite2);

        damaPawnBlack1.getMovement().forwardLeft();

        assertEquals(new position(7, 2), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnWhite1));
        assertFalse(this.checkEliminationPawn(damaPawnWhite2));

        assertFalse(this.damaPawnBlack1.getMovement() instanceof damaMovement);

    }

    @Test
    @DisplayName("Simple margin test")
    void simpleMarginTest() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(1, 1), damaPawnWhite1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().forwardLeft());
        assertEquals(new position(1, 1), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(1, 8), damaPawnWhite1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().forwardRight());
        assertEquals(new position(1, 8), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));

    }

    @Test
    @DisplayName("Dama position isn't occupied")
    void damaPositionFree() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(4, 5), damaPawnBlack1);
        gd.getBoard().addPawn(new position(5, 4), damaPawnWhite1);
        gd.getBoard().addPawn(new position(7, 2), damaPawnWhite2);
        damaPawnBlack1.getMovement().forwardLeft();

        assertEquals(new position(8, 1), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnWhite1));
        assertTrue(this.checkEliminationPawn(damaPawnWhite2));
        assertTrue(this.damaPawnBlack1.getMovement() instanceof damaMovement);

    }

    @Test
    @DisplayName("test sequential capture")
    void testSequentialCapture() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(4, 8), damaPawnBlack1);
        gd.getBoard().addPawn(new position(3, 7), damaPawnWhite1);

        gd.getBoard().addPawn(new position(3, 5), damaPawnWhite2);


        gd.getBoard().addPawn(new position(3, 3), damaPawnWhite3);
        damaPawnBlack1.getMovement().forwardRight();
        assertEquals(new position(2, 2), gd.getBoard().getPositionPawn(damaPawnBlack1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnWhite1));
        assertTrue(this.checkEliminationPawn(damaPawnWhite2));
        assertTrue(this.checkEliminationPawn(damaPawnWhite3));


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
