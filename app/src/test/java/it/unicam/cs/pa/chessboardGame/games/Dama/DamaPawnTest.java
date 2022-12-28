package it.unicam.cs.pa.chessboardGame.games.Dama;

import it.unicam.cs.pa.chessboardGame.games.Dama.movements.classicMovement;
import it.unicam.cs.pa.chessboardGame.games.Dama.movements.damaMovement;
import it.unicam.cs.pa.chessboardGame.structure.movement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * pawn test
 *
 * @author Matteo Brachetta
 * @version 0.1
 */
class DamaPawnTest {
    private damaPawn basicDamaPawn;
    private final damaPlayer damaPlayer = new damaPlayer("player Test");
    private final int hierarchy = 0;
    private final String symbol = "T";
    private final movement defaultMove = new classicMovement();

    @BeforeEach
    void setUp() {
        basicDamaPawn = new damaPawn(hierarchy, defaultMove, symbol, damaPlayer);
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
        movement newMove = new damaMovement();
        basicDamaPawn.setMovement(newMove);

        assertEquals(newMove, basicDamaPawn.getMovement());
        assertThrows(NullPointerException.class, () -> basicDamaPawn.setMovement(null));
    }

    @DisplayName("Get movement Test")
    @Test
    void getMovement() {
        assertEquals(defaultMove, basicDamaPawn.getMovement());
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
        damaPawn notEqualPawn = new damaPawn(hierarchy, defaultMove, symbol, damaPlayer);
        assertNotEquals(notEqualPawn, basicDamaPawn);
    }
}