package it.unicam.cs.pa.chessboardGame.app.dama;

import it.unicam.cs.pa.chessboardGame.app.games.dama.damaGame;
import it.unicam.cs.pa.chessboardGame.app.games.dama.damaPlayer;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Simulation game test. This test starts the game and moves the players' pawns in random moves, and returns victory to the {@code player}.
 * This Test show all move.
 *
 * @author Matteo Brachetta
 * @version 1.0
 */
public class GameDamaExecuteTest {
    damaPlayer damaPlayerWhite = new damaPlayer("white player");
    damaPlayer damaPlayerBlack = new damaPlayer("black player");

    damaGame dg = new damaGame("dama", damaPlayerWhite, damaPlayerBlack);
    ArrayList<player> order = new ArrayList<>(dg.getPlayers());
    ArrayList<Integer> numberGenerate = new ArrayList<>();

    @Test
    @DisplayName("simulation and show game to random move")
    void simulationGame() {
        System.out.println("-----------------------");
        System.out.println("START");
        System.out.println(dg.getBoard());
        System.out.println("-----------------------");

        while (dg.getWin() == null) {
            for (player p : order)
                this.requestPawnToGame(p);
            System.out.println(dg.getBoard());
        }
        System.out.println("-----------------------");
        System.out.println("ENS");
        System.out.println("WIN: " + this.dg.getWin());
        System.out.println("-----------------------");

    }

    /**
     * Action for call get random {@code pawn} to {@code player}
     *
     * @param p {@code player} to turn game
     */
    private void requestPawnToGame(player p) {
        List<pawn> listPawnToMove = new ArrayList<>(dg.getBoard().getPawnToMove(p.getId()));
        if (listPawnToMove.isEmpty())
            return;
        int randomPawn = ThreadLocalRandom.current().nextInt(0, listPawnToMove.size());
        numberGenerate.add(randomPawn);
        listPawnToMove.get(randomPawn).getMovement().randomMove();

    }
}
