package it.unicam.cs.pa.chessboardGame.app.dama;

import it.unicam.cs.pa.chessboardGame.app.games.dama.damaBoard;
import it.unicam.cs.pa.chessboardGame.app.games.dama.damaGame;
import it.unicam.cs.pa.chessboardGame.app.games.dama.damaPlayer;
import it.unicam.cs.pa.chessboardGame.structure.player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test of dama Game
 *
 * @author Matteo Brachetta
 * @version 0.2
 */
class DamaGameTest {
    private final damaPlayer playerPawnWhite = new damaPlayer("white player test");
    private final damaPlayer playerPawnBlack = new damaPlayer("black player test");
    private final damaPlayer playerTest = new damaPlayer("Player test");
    private damaGame dg;

    @BeforeEach
    void setUp() {
        dg = new damaGame("italian dama", playerPawnWhite, playerPawnBlack);
    }

    @DisplayName("Get Board Test")
    @Test
    void getBoard() {
        assertNotNull(dg.getBoard());
        damaGame damaGame = new damaGame("Test");
        assertNull(damaGame.getBoard());
    }

    @DisplayName("Set Board Test")
    @Test
    void setBoard() {
        damaBoard db = new damaBoard(8, 8, playerPawnWhite, playerPawnBlack);

        dg.setBoard(db);
        assertEquals(db, dg.getBoard());

        assertThrows(NullPointerException.class, () -> dg.setBoard(null));
    }

    @DisplayName("Get name Test")
    @Test
    void getName() {
        assertEquals("Dama", dg.getName());
    }

    @DisplayName("Get live win Test")
    @Test
    void getLiveWin() {
        playerPawnBlack.addScore(100);
        playerPawnWhite.addScore(90);

        ArrayList<player> lp = new ArrayList<>();
        lp.add(playerPawnWhite);
        lp.add(playerPawnBlack);
        dg.setPlayers(lp);

        assertEquals(playerPawnBlack, dg.getLiveWin());

    }

    @DisplayName("Set players Test")
    @Test
    void setPlayers() {
        List<player> lp = new ArrayList<>();
        assertThrows(NullPointerException.class, () -> dg.setPlayers(null));
        assertThrows(IllegalArgumentException.class, () -> dg.setPlayers(lp));

        lp.add(playerTest);
        lp.add(playerPawnWhite);
        dg.setPlayers(lp);

        assertEquals(2, dg.getPlayers().size());
        assertTrue(dg.getPlayers().contains(playerTest));
        assertTrue(dg.getPlayers().contains(playerPawnWhite));
    }

    @DisplayName("Get players Test")
    @Test
    void getPlayers() {
        assertEquals(2, dg.getPlayers().size());
        assertTrue(dg.getPlayers().contains(playerPawnBlack));
        assertTrue(dg.getPlayers().contains(playerPawnWhite));

    }

    @DisplayName("Get Player test")
    @Test
    void getPlayer() {
        assertEquals(playerPawnBlack, dg.getPlayer(playerPawnBlack.getId()));
        assertEquals(playerPawnWhite, dg.getPlayer(playerPawnWhite.getId()));

        assertThrows(NullPointerException.class, () -> dg.getPlayer(null));
        assertThrows(IllegalArgumentException.class, () -> dg.getPlayer(""));
        assertThrows(IllegalArgumentException.class, () -> dg.getPlayer(playerTest.getId()));
    }

    @DisplayName("Add player test")
    @Test
    void addPlayer() {
        dg.addPlayer(playerTest);

        assertEquals(3, dg.getPlayers().size());
        assertTrue(dg.getPlayers().contains(playerTest));
    }

    @DisplayName("Get information test")
    @Test
    void getInformationGame() {
        String desc = "italian dama";
        damaGame dg = new damaGame(desc, playerPawnWhite, playerPawnBlack);
        assertEquals(desc, dg.getInformationGame());
    }

    @DisplayName("Restart test")
    @Test
    void restart() {
        playerPawnBlack.addScore(100);
        playerPawnWhite.addScore(90);
        ArrayList<player> lp = new ArrayList<>();
        lp.add(playerPawnWhite);
        lp.add(playerPawnBlack);
        dg.setPlayers(lp);

        dg.restart();

        for (player dp : dg.getPlayers())
            assertEquals(0, dp.getScore());

    }
}