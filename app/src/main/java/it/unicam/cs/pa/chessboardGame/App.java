package it.unicam.cs.pa.chessboardGame;

import it.unicam.cs.pa.chessboardGame.games.Dama.damaBoard;
import it.unicam.cs.pa.chessboardGame.games.Dama.damaPlayer;
import it.unicam.cs.pa.chessboardGame.structure.game;
import it.unicam.cs.pa.chessboardGame.structure.pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matteo Brachetta
 * @version 0.1
 */
public class App {

    public List<game> games;

    public App() {
        this.games = new ArrayList<game>();
    }

    public static void main(String[] args) {
        System.out.println("!-----------------------------!");
        System.out.println("| Welcome to Chessboard games |");
        System.out.println("games available:");
        new App().games.forEach((g) -> System.out.println("- " + g.getName()));
        System.out.println("!-----------------------------!");

    }

}
