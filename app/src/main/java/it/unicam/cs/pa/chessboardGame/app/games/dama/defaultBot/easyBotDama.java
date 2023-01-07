package it.unicam.cs.pa.chessboardGame.app.games.dama.defaultBot;

import it.unicam.cs.pa.chessboardGame.app.games.dama.damaPlayer;

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

}