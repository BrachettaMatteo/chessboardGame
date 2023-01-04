package it.unicam.cs.pa.chessboardGame.games.dama;

import it.unicam.cs.pa.chessboardGame.games.dama.defaultBot.easyBotDama;
import it.unicam.cs.pa.chessboardGame.games.dama.movements.damaMovement;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.position;

import java.util.*;

public class damaCLI {
    protected final damaGame dg;
    protected final damaBoard board;
    protected final ArrayList<damaPlayer> order;

    protected final damaPlayer player;
    private final String symbol;
    private final Scanner scanner;

    public damaCLI(damaGame damaGame) {
        this.dg = damaGame;
        this.board = (damaBoard) dg.getBoard();
        this.order = new ArrayList<>(dg.getPlayers().stream().map(el -> (damaPlayer) el).toList());
        this.player = this.order.stream().filter(damaPlayer -> !(damaPlayer instanceof easyBotDama)).findFirst().orElse(null);
        this.symbol = Objects.requireNonNull(this.board.getPawns().stream().filter(pawn -> pawn.getOwner() == this.player).findFirst().orElse(null)).getSymbol();
        scanner = new Scanner(System.in);
        this.startGame();
    }

    private void startGame() {
        System.out.println(this.getLegend());
        System.out.println("START GAME");
        while (this.dg.getWin() == null) {
            for (damaPlayer currentPlayer : this.order)
                this.requestPawnMove(currentPlayer);

        }
        System.out.println("END GAME");
    }

    private void requestPawnMove(damaPlayer currentPlayer) {
        Random rand = new Random();
        List<pawn> listPawnToMove = new ArrayList<>(this.board.getPawnToMove(currentPlayer.getId()));
        System.out.println("turn->" + currentPlayer.getName());
        System.out.println(this.board);
        if (currentPlayer instanceof easyBotDama) {
            System.out.println("bot move ...");
            listPawnToMove.get(rand.nextInt(listPawnToMove.size())).getMovement().randomMove();
        } else {
            pawn selectPawn = this.requestPawnInput(listPawnToMove);
            boolean correctMove = false;
            while (!correctMove)
                correctMove = this.requestMovementPawn(selectPawn);
        }
    }

    private boolean requestMovementPawn(pawn selectPawn) {
        damaPawn dp = (damaPawn) selectPawn;
        System.out.println("insert movement");
        String move = scanner.next();
        switch (move) {
            case "L":
                try {
                    dp.getMovement().forwardLeft();
                    return true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }

            case "R":
                try {
                    dp.getMovement().forwardRight();
                    return true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            case "BR": {
                if (dp.getMovement() instanceof damaMovement) {
                    dp.getMovement().backRight();
                    return true;
                }
            }
            case "BL":
                if (dp.getMovement() instanceof damaMovement) {
                    dp.getMovement().backLeft();
                    return true;

                }
            default:
                return false;
        }
    }


    /**
     * instruction for insert adn verify input
     *
     * @return the correct pawn select
     */
    private pawn requestPawnInput(List<pawn> possiblePawnSelect) {
        pawn selectPawn = null;
        for (pawn p : possiblePawnSelect) {
            System.out.print(this.board.getPositionPawn(p.getId()) + " ");
        }
        System.out.println();
        while (!possiblePawnSelect.contains(selectPawn)) {

            try {
                int col, row;
                System.out.println("insert col");
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.printf("\"%s\" is not a valid column position.\nInsert col:", input);
                }
                col = scanner.nextInt();
                System.out.println("insert row");
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.printf("\"%s\" is not a valid column position.\nInsert row:\n", input);
                }

                row = scanner.nextInt();
                position positionSelect = new position(col, row);
                if (this.board.getPawn(positionSelect).getOwner() == player) {
                    if (possiblePawnSelect.contains(this.board.getPawn(positionSelect))) {
                        selectPawn = this.board.getPawn(positionSelect);
                        System.out.println(positionSelect);
                    } else
                        System.out.println("the pawn can't move");

                } else
                    System.out.println("position not correct");
            } catch (Exception e) {
                //do nothing
                System.out.println("error position");
            }

        }
        return selectPawn;
    }

    /**
     * show legend to move and example
     *
     * @return legend to movement and example
     */
    private String getLegend() {
        return """
                !-----------------------------!
                Legend move\s
                L move to left\s
                R move to right\s
                BR move to bottomRight\s
                BL move to bottomRight\s
                Example movement: [column,row] L \s
                !-----------------------------! \s
                symbol pawn ->""" + this.symbol;
    }
}