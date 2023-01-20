package it.unicam.cs.pa.chessboardGame.app.games.dama.defaultBot;

import it.unicam.cs.pa.chessboardGame.app.games.dama.damaPlayer;
import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.pawn;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Easy bot, the move is randomly generated.
 *
 * @author Matteo Brachetta
 * @version 1.0
 */
public class easyBotDama extends damaPlayer {

    /**
     * Constructor for <code>easyBotDama</code>.
     */
    public easyBotDama() {
        super("easyBot");
    }


    @Override
    public void executeAutomaticMove(gameBoard board) {
        if (board == null) throw new NullPointerException("bard is null");
        List<pawn> lp = board.getPawnToMove(this.getId());
        int rand = ThreadLocalRandom.current().nextInt(0, lp.size());
        lp.get(rand).getMovement().randomMove();
    }
}