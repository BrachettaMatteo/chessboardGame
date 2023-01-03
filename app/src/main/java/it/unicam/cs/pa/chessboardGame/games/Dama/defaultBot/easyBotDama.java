package it.unicam.cs.pa.chessboardGame.games.Dama.defaultBot;

import it.unicam.cs.pa.chessboardGame.games.Dama.damaPlayer;

import java.util.UUID;

/**
 * Easy bot, the move is randomly generated.
 *
 * @author Matteo Brachetta
 * @version 0.0
 */
public class easyBotDama extends damaPlayer {

    private final UUID id;

    public easyBotDama() {
        super("easyBot");
        this.id = UUID.randomUUID();
    }

    @Override
    public String getId() {
        return this.id.toString();
    }

    @Override
    public int getScore() {
        //TODO: implement easyBotDama.getScore
        return 0;
    }

    @Override
    public void addScore(int score) {
        //TODO: implement easyBotDama.addScore

    }

    @Override
    public void removeScore(int score) {
        //TODO: implement easyBotDama.removeScore

    }


}