package it.unicam.cs.pa.chessboardGame.app.dama.movements.classicMovements;

import it.unicam.cs.pa.chessboardGame.app.games.dama.damaGame;
import it.unicam.cs.pa.chessboardGame.app.games.dama.damaPawn;
import it.unicam.cs.pa.chessboardGame.app.games.dama.damaPlayer;
import it.unicam.cs.pa.chessboardGame.app.games.dama.movements.damaMovement;
import it.unicam.cs.pa.chessboardGame.structure.game;
import it.unicam.cs.pa.chessboardGame.structure.position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of white {@code pawn}
 *
 * @author Matteo Brachetta
 * @version 1.0
 */
class WhiteMovementsTest {
    private final damaPlayer playerWhiteTest = new damaPlayer("player white Test");
    private damaPawn damaPawnWhite1;
    private damaPawn damaPawnWhite2;

    private final damaPlayer playerBlackTest = new damaPlayer("player black Test");
    private damaPawn damaPawnBlack1;
    private damaPawn damaPawnBlack2;

    private game gd = new damaGame("...", playerWhiteTest, playerBlackTest);

    @BeforeEach
    void setUp() {
        gd = new damaGame("...", playerWhiteTest, playerBlackTest);
        damaPawnWhite1 = new damaPawn(0, gd.getBoard(), "*", playerWhiteTest, true);
        damaPawnWhite2 = new damaPawn(0, gd.getBoard(), "*", playerWhiteTest, true);

        damaPawnBlack1 = new damaPawn(0, gd.getBoard(), "•", playerBlackTest, false);
        damaPawnBlack2 = new damaPawn(0, gd.getBoard(), "•", playerBlackTest, false);
    }

    @DisplayName("Simple forward right test")
    @Test
    void simpleForwardRightTest() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(8, 1), damaPawnWhite1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().forwardRight());
        assertEquals(new position(8, 1), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));

        gd.getBoard().clearBoard();

        //move forward and the forward box is empty
        gd.getBoard().addPawn(new position(4, 4), damaPawnWhite1);
        damaPawnWhite1.getMovement().forwardRight();
        assertEquals(new position(5, 5), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));

        //move forward and the forward box is occupation to opponent
        gd.getBoard().addPawn(new position(6, 6), damaPawnBlack1);
        damaPawnWhite1.getMovement().forwardRight();
        assertEquals(new position(7, 7), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        //check eliminated
        assertTrue(this.checkEliminationPawn(damaPawnBlack1));

        //move forward and the forward box is occupation to friend {@code pawn}
        gd.getBoard().updatePosition(new position(4, 4), damaPawnWhite1);
        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite2);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().forwardRight());

        assertEquals(new position(4, 4), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
    }


    @DisplayName("Simple forward left test")
    @Test
    void simpleForwardLeftTest() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(1, 1), damaPawnWhite1);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().forwardLeft());
        assertEquals(new position(1, 1), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));

        gd.getBoard().clearBoard();

        //move forward and the forward box is empty
        gd.getBoard().addPawn(new position(4, 4), damaPawnWhite1);
        damaPawnWhite1.getMovement().forwardLeft();
        assertEquals(new position(3, 5), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));

        //move forward and the forward box is occupation to opponent
        gd.getBoard().addPawn(new position(2, 6), damaPawnBlack1);
        damaPawnWhite1.getMovement().forwardLeft();
        assertEquals(new position(1, 7), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnBlack1));

        //move forward and the forward box is occupation to friend pawn
        gd.getBoard().updatePosition(new position(4, 6), damaPawnWhite1);
        gd.getBoard().addPawn(new position(3, 7), damaPawnWhite2);
        assertThrows(IllegalArgumentException.class, () -> damaPawnWhite1.getMovement().forwardLeft());
        assertEquals(new position(4, 6), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
    }

    @Test
    @DisplayName("Multi forward margin Test")
    void MultiForwardMarginTest() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(4, 2), damaPawnWhite1);
        gd.getBoard().addPawn(new position(3, 3), damaPawnBlack1);
        gd.getBoard().addPawn(new position(1, 5), damaPawnBlack2);
        damaPawnWhite1.getMovement().forwardLeft();

        assertEquals(new position(2, 4), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));


        assertTrue(this.checkEliminationPawn(damaPawnBlack1));
        assertFalse(this.checkEliminationPawn(damaPawnBlack2));
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(5, 2), damaPawnWhite1);
        gd.getBoard().addPawn(new position(6, 3), damaPawnBlack1);
        gd.getBoard().addPawn(new position(8, 5), damaPawnBlack2);
        damaPawnWhite1.getMovement().forwardRight();

        assertEquals(new position(7, 4), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        //check eliminated
        assertTrue(this.checkEliminationPawn(damaPawnBlack1));
        assertFalse(this.checkEliminationPawn(damaPawnBlack2));

    }

    @Test
    @DisplayName("Multi forward right Test")
    void MultiForwardRightTest() {
        gd.getBoard().clearBoard();
        gd.getBoard().addPawn(new position(4, 2), damaPawnWhite1);
        gd.getBoard().addPawn(new position(5, 3), damaPawnBlack1);
        gd.getBoard().addPawn(new position(7, 5), damaPawnBlack2);
        this.damaPawnWhite1.getMovement().forwardRight();

        assertEquals(new position(8, 6), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnBlack1));
        assertTrue(this.checkEliminationPawn(damaPawnBlack2));

        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(4, 2), damaPawnWhite1);
        gd.getBoard().addPawn(new position(5, 3), damaPawnBlack1);
        gd.getBoard().addPawn(new position(7, 5), damaPawnWhite2);
        damaPawnWhite1.getMovement().forwardRight();
        assertEquals(new position(6, 4), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnBlack1));
        assertFalse(this.checkEliminationPawn(damaPawnWhite2));
    }

    @Test
    @DisplayName("Multi forward left Test")
    void MultiForwardLeftTest() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 2), damaPawnWhite1);
        gd.getBoard().addPawn(new position(4, 3), damaPawnBlack1);
        gd.getBoard().addPawn(new position(2, 5), damaPawnBlack2);
        damaPawnWhite1.getMovement().forwardLeft();

        assertEquals(new position(1, 6), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnBlack1));
        assertTrue(this.checkEliminationPawn(damaPawnBlack2));

        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 2), damaPawnWhite1);
        gd.getBoard().addPawn(new position(4, 3), damaPawnBlack1);
        gd.getBoard().addPawn(new position(2, 5), damaPawnWhite2);
        damaPawnWhite1.getMovement().forwardLeft();

        assertEquals(new position(3, 4), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));
        assertTrue(this.checkEliminationPawn(damaPawnBlack1));
        assertFalse(this.checkEliminationPawn(damaPawnWhite2));
    }

    @Test
    @DisplayName("Dama position is occupied")
    void damaPositionOccupied() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(5, 5), damaPawnWhite1);
        gd.getBoard().addPawn(new position(6, 6), damaPawnBlack2);
        gd.getBoard().addPawn(new position(8, 8), damaPawnBlack1);

        damaPawnWhite1.getMovement().forwardRight();

        assertEquals(new position(7, 7), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));

        assertTrue(this.checkEliminationPawn(damaPawnBlack2));
        assertFalse(this.checkEliminationPawn(damaPawnBlack1));
    }

    @Test
    @DisplayName("Dama position isn't occupied")
    void damaPositionFree() {
        gd.getBoard().clearBoard();

        gd.getBoard().addPawn(new position(4, 4), damaPawnWhite1);
        gd.getBoard().addPawn(new position(5, 5), damaPawnBlack2);
        gd.getBoard().addPawn(new position(7, 7), damaPawnBlack1);
        damaPawnWhite1.getMovement().forwardRight();

        assertEquals(new position(8, 8), gd.getBoard().getPositionPawn(damaPawnWhite1.getId()));

        assertTrue(this.checkEliminationPawn(damaPawnBlack1));
        assertTrue(this.checkEliminationPawn(damaPawnBlack2));
    }

    /**
     * Check {@code pawn} is eliminated
     *
     * @param pawnToCheck {@code pawn} to check eliminated
     * @return {@code true} if {@code pawn} is eliminated else {@code false}
     */
    private boolean checkEliminationPawn(damaPawn pawnToCheck) {
        return this.gd.getBoard().getEliminated().contains(pawnToCheck);
    }

    @Test
    void damaRandomMove() {
        gd.getBoard().clearBoard();
        damaPawnWhite1.setMovement(new damaMovement(gd.getBoard(), damaPawnWhite1));
        gd.getBoard().addPawn(new position(4, 4), damaPawnWhite1);
        damaPawnWhite1.getMovement().randomMove();

        damaPawnWhite1.getMovement().randomMove();
    }

}