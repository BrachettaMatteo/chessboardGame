package it.unicam.cs.pa.chessboardGame.app.dama;

import it.unicam.cs.pa.chessboardGame.app.games.dama.damaPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test of dama Player
 *
 * @author Matteo Brachetta
 * @version 1.0
 */
class DamaPlayerTest {
    private static damaPlayer dp = new damaPlayer("test player");

    @BeforeEach
    void setUp() {
        dp = new damaPlayer("test player");
    }

    @DisplayName("Get score Test")
    @Test
    void getScore() {
        assertEquals(0, dp.getScore());
        int newScore = 100;
        dp.addScore(newScore);
        assertEquals(newScore, dp.getScore());
    }

    @DisplayName("Add score Test")
    @Test
    void addScore() {
        int newScore = 100;
        int errorScore = -1;
        dp.addScore(newScore);
        assertEquals(newScore, dp.getScore());

        assertThrows(IllegalArgumentException.class, () -> dp.addScore(errorScore));
    }

    @DisplayName("Remove score Test")
    @Test
    void removeScore() {
        int correctScore = 10;
        int errorScore = -1;
        dp.addScore(correctScore * 2);
        dp.removeScore(correctScore);
        assertEquals(correctScore, dp.getScore());

        assertThrows(IllegalArgumentException.class, () -> dp.removeScore(errorScore));

    }

    @DisplayName("Get name Test")
    @Test
    void getName() {
        assertEquals("test player", dp.getName());
    }
}