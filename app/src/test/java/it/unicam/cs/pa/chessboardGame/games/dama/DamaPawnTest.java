package it.unicam.cs.pa.chessboardGame.games.dama;

import it.unicam.cs.pa.chessboardGame.games.dama.movements.damaMovement;
import it.unicam.cs.pa.chessboardGame.structure.movement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test for check functionality dama pawn
 *
 * @author Matteo Brachetta
 * @version 0.1
 */
class DamaPawnTest {
    private damaPawn basicDamaPawn;
    private final damaPlayer damaPlayer = new damaPlayer("player Test");
    private final int hierarchy = 0;
    private final String symbol = "T";

    private damaGame dg = new damaGame("test", damaPlayer, null);


    @BeforeEach
    void setUp() {
        dg = new damaGame("test", damaPlayer, null);
        basicDamaPawn = new damaPawn(hierarchy, null, symbol, damaPlayer, true);
    }

    @DisplayName("Get hierarchy Test")
    @Test
    void getHierarchy() {
        assertEquals(hierarchy, basicDamaPawn.getHierarchy());
    }

    @DisplayName("Set hierarchy Test")
    @Test
    void setHierarchy() {
        int newHierarchy = 10;
        basicDamaPawn.setHierarchy(newHierarchy);
        assertEquals(newHierarchy, basicDamaPawn.getHierarchy());
        assertThrows(IllegalArgumentException.class, () -> basicDamaPawn.setHierarchy(-1));
    }

    @DisplayName("Set movement Test")
    @Test
    void setMovement() {
        movement newMove = new damaMovement(dg.getBoard(), basicDamaPawn);
        basicDamaPawn.setMovement(newMove);

        assertEquals(newMove, basicDamaPawn.getMovement());
        assertThrows(NullPointerException.class, () -> basicDamaPawn.setMovement(null));
    }

    @DisplayName("Get movement Test")
    @Test
    void getMovement() {
        movement newMove = new damaMovement(dg.getBoard(), basicDamaPawn);
        basicDamaPawn.setMovement(newMove);

        assertEquals(newMove, basicDamaPawn.getMovement());
    }

    @DisplayName("Get symbol Test")
    @Test
    void getSymbol() {
        assertEquals(symbol, basicDamaPawn.getSymbol());
    }

    @DisplayName("Get life Test")
    @Test
    void getLife() {
        assertTrue(basicDamaPawn.getLife());
    }

    @DisplayName("Set life Test")
    @Test
    void setLife() {
        basicDamaPawn.setLife(true);
        assertTrue(basicDamaPawn.getLife());
    }

    @DisplayName("Get owner Test")
    @Test
    void getOwner() {
        assertEquals(damaPlayer, basicDamaPawn.getOwner());
    }

    @DisplayName(" equalsTest")
    @Test
    void Equals() {
        assertEquals(basicDamaPawn, basicDamaPawn);
        damaPawn notEqualPawn = new damaPawn(hierarchy, dg.getBoard(), symbol, damaPlayer, true);
        assertNotEquals(notEqualPawn, basicDamaPawn);
    }
}