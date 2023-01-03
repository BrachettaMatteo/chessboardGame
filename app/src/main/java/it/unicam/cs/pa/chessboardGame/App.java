package it.unicam.cs.pa.chessboardGame;

import it.unicam.cs.pa.chessboardGame.games.Dama.damaBoard;
import it.unicam.cs.pa.chessboardGame.games.Dama.damaGame;
import it.unicam.cs.pa.chessboardGame.games.Dama.damaPlayer;
import it.unicam.cs.pa.chessboardGame.structure.game;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.games.Dama.defaultBot.easyBotDama;
import it.unicam.cs.pa.chessboardGame.structure.position;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
        App app = new App();
        app.games.add(new damaGame("-"));
        System.out.println("!-----------------------------!");
        System.out.println("| Welcome to Chessboard games |");
        System.out.println("games available:");
        app.games.forEach((g) -> System.out.println("[" + (app.games.indexOf(g) + 1) + "] " + g.getName()));
        System.out.println("!-----------------------------!");
        System.out.println("Select game, insert name of game:");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        System.out.println("select game:" + app.games.get(a - 1).getName());
        game game = app.games.get(a - 1);
        System.out.println("!-----------------------------!");
        System.out.println("create player:");
        String name = scanner.next();
        damaPlayer dp = new damaPlayer(name);
        damaPlayer bot = new easyBotDama();
        game.addPlayer(bot);
        game.addPlayer(dp);
        game.start();


    }

}
