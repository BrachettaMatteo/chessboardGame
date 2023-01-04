package it.unicam.cs.pa.chessboardGame;

import it.unicam.cs.pa.chessboardGame.games.dama.damaGame;
import it.unicam.cs.pa.chessboardGame.games.dama.damaPlayer;
import it.unicam.cs.pa.chessboardGame.structure.game;
import it.unicam.cs.pa.chessboardGame.games.dama.defaultBot.easyBotDama;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Matteo Brachetta
 * @version 0.1
 */
public class App {

    public final List<game> games;

    public App() {
        this.games = new ArrayList<>();
    }

    public static void main(String[] args) {
        App app = new App();
        app.games.add(new damaGame("-"));
        System.out.println("!-----------------------------!");
        System.out.println("| Welcome to Chessboard games |");
        System.out.println("games available:");
        app.games.forEach((g) -> System.out.println("[" + (app.games.indexOf(g) + 1) + "] " + g.getName()));
        System.out.println("!-----------------------------!");
        System.out.println("Select game, insert number of game:");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.printf("\"%s\" is not a valid number.\nInsert correct nu,ber:", input);
        }
        int a = scanner.nextInt();
        while (a > app.games.size()) {
            System.out.println("error select insert correct number");
            a = scanner.nextInt();
        }

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
