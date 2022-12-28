package it.unicam.cs.pa.chessboardGame.games.Dama;

import it.unicam.cs.pa.chessboardGame.structure.player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * test of dama Game
 *
 * @author Matteo Brachetta
 * @version 0.1
 */
class damaGameTest {
    private final damaPlayer playerPawnWhite = new damaPlayer("white player test");
    private final damaPlayer playerPawnBlack = new damaPlayer("black player test");
    private final damaPlayer playerTest = new damaPlayer("Player test");

    @DisplayName("get Board Test")
    @Test
    void getBoard() {
        damaGame dg = new damaGame("italian dama", playerPawnWhite, playerPawnBlack);

        assertNotNull(dg.getBoard());
    }

    @DisplayName("set Board Test")
    @Test
    void setBoard() {
        damaGame dg = new damaGame("italian dama", playerPawnWhite, playerPawnBlack);
        damaBoard db = new damaBoard(8, 8, playerPawnWhite, playerPawnBlack);

        dg.setBoard(db);

        assertEquals(db, dg.getBoard());
    }

    @DisplayName("get name Test")
    @Test
    void getName() {
        damaGame dg = new damaGame("italian dama", playerPawnWhite, playerPawnBlack);
        assertEquals("Dama", dg.getName());
    }

    @DisplayName("get live win Test")
    @Test
    void getLiveWin() {
        damaGame dg = new damaGame("italian dama", playerPawnWhite, playerPawnBlack);

        playerPawnBlack.addScore(100);
        playerPawnWhite.addScore(90);

        ArrayList<player> lp = new ArrayList<>();
        lp.add(playerPawnWhite);
        lp.add(playerPawnBlack);
        dg.setPlayers(lp);

        assertEquals(playerPawnBlack, dg.getLiveWin());

    }

    @DisplayName("set players Test")
    @Test
    void setPlayers() {
        damaGame dg = new damaGame("italian dama", playerPawnWhite, playerPawnBlack);
        List<player> lp = new ArrayList<>();
        lp.add(playerTest);
        lp.add(playerPawnWhite);
        dg.setPlayers(lp);

        assertEquals(2, dg.getPlayers().size());
        assertTrue(dg.getPlayers().contains(playerTest));
        assertTrue(dg.getPlayers().contains(playerPawnWhite));

    }

    @DisplayName("get players Test")
    @Test
    void getPlayers() {
        damaGame dg = new damaGame("italian dama", playerPawnWhite, playerPawnBlack);

        assertEquals(2, dg.getPlayers().size());
        assertTrue(dg.getPlayers().contains(playerPawnBlack));
        assertTrue(dg.getPlayers().contains(playerPawnWhite));

    }

    @DisplayName("get Player test")
    @Test
    void getPlayer() {
        damaGame dg = new damaGame("italian dama", playerPawnWhite, playerPawnBlack);

        assertEquals(playerPawnBlack, dg.getPlayer(playerPawnBlack.getId()));
        assertEquals(playerPawnWhite, dg.getPlayer(playerPawnWhite.getId()));

        assertThrows(NullPointerException.class, () -> dg.getPlayer(null));
        assertThrows(IllegalArgumentException.class, () -> dg.getPlayer(""));
        assertThrows(IllegalArgumentException.class, () -> dg.getPlayer(playerTest.getId()));
    }

    @DisplayName("add player test")
    @Test
    void addPlayer() {
        damaGame dg = new damaGame("italian dama", playerPawnWhite, playerPawnBlack);

        dg.addPlayer(playerTest);

        assertEquals(3, dg.getPlayers().size());
        assertTrue(dg.getPlayers().contains(playerTest));
    }

    @DisplayName("get information test")
    @Test
    void getInformationGame() {
        String desc = "italian dama";
        damaGame dg = new damaGame(desc, playerPawnWhite, playerPawnBlack);
        assertEquals(desc, dg.getInformationGame());
    }

    @DisplayName("restart test")
    @Test
    void restart() {
        damaGame dg = new damaGame("italian dama", playerPawnWhite, playerPawnBlack);

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