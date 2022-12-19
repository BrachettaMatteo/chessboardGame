package it.unicam.cs.pa.chessboardGame.defaultBot;

import it.unicam.cs.pa.chessboardGame.structure.*;

import java.util.UUID;

/**
 * Easy bot, the move is randomly generated.
 *
 * @author Matteo Brachetta
 * @version 0.0
 */
public class easyBot implements player {

    private final UUID id;

    public easyBot() {
        this.id = UUID.randomUUID();
    }

    @Override
    public String getId() {
        //TODO: implement easyBot.getId
        return null;
    }

    @Override
    public int getScore() {
        //TODO: implement easyBot.getScore
        return 0;
    }

    @Override
    public void addScore(int score) {
        //TODO: implement easyBot.addScore

    }

    @Override
    public void removeScore(int score) {
        //TODO: implement easyBot.removeScore

    }

    @Override
    public String getName() {
        //TODO: implement easyBot.getName
        return null;
    }
}