package it.unicam.cs.pa.chessboardGame.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * test positionTest
 *
 * @author Matteo Brachetta
 * @version 0.1
 */
class PositionTest {
    private final int row = 2;
    private final int column = 2;
    position positionTest;

    @BeforeEach
    void setUp() {
        positionTest = new position(column, row);
    }

    @DisplayName("Get row Test")
    @Test
    void getRow() {
        assertEquals(row, positionTest.getRow());
    }

    @DisplayName("Set row Test")
    @Test
    void setRow() {
        assertThrows(IllegalArgumentException.class, () -> positionTest.setRow(-1));
        int newRow = 10;
        positionTest.setRow(newRow);
        assertEquals(newRow, positionTest.getRow());
    }

    @DisplayName("Get column Test")
    @Test
    void getColumn() {
        assertEquals(column, positionTest.getColumn());
    }

    @DisplayName("Set column Test")
    @Test
    void setColumn() {
        assertThrows(IllegalArgumentException.class, () -> positionTest.setColumn(-1));
        int newColumn = 10;
        positionTest.setColumn(newColumn);
        assertEquals(newColumn, positionTest.getColumn());
    }

    @DisplayName("ToString Test")
    @Test
    void testToString() {
        String aspect = "[" + row + " - " + column +
                "]";
        assertEquals(aspect, positionTest.toString());
    }

    @DisplayName("Equals Test")
    @Test
    void testEquals() {
        position differentPosition = new position(10, 1);
        position equalPosition = new position(column, row);

        assertNotEquals(differentPosition, positionTest);
        assertEquals(equalPosition, positionTest);
    }

    @DisplayName("Compare To Test")
    @Test
    void compareTo() {
        //check column
        position majorPosition = new position(10, 10);
        position minorPosition = new position(1, 0);
        assertEquals(1, positionTest.compareTo(minorPosition));
        assertEquals(-1, positionTest.compareTo(majorPosition));

        //check row
        position minorNearPosition = new position(column, row - 1);
        position majorNearPosition = new position(column, row + 1);
        assertEquals(1, positionTest.compareTo(minorNearPosition));
        assertEquals(-1, positionTest.compareTo(majorNearPosition));

        //check equal
        assertEquals(0, positionTest.compareTo(new position(column, row)));
    }
}