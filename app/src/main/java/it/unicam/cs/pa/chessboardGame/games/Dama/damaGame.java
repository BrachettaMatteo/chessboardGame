package it.unicam.cs.pa.chessboardGame.games.Dama;

import it.unicam.cs.pa.chessboardGame.structure.*;

import java.util.List;
import java.util.UUID;

/**
 * 8x8 checkers:
 * Italian checkers - In ancient Rome, it was called "ludus dominarum" (master/sister game). It is safe to assume that Julius Caesar was also familiar with the game and its related pyramid-shaped defensive strategy, given the defensive strategy of the legions narrated in De bello Gallico.[source-less] It is played on an 8x8 checkerboard, similar to the chess board in chess, with the only difference being that it is rotated ninety degrees (the canton, i.e., the black corner square, goes to the bottom right). There are 12 checkers per player, and the player with the light pieces ("the white") starts. Pawns cannot eat the ladies, nor can they eat backwards. In the case of multiple chances to take, there is an obligation to take in the order of priority: where there are the most pieces, where there are the most valuable pieces (ladies) and where the most valuable pieces meet first. With the same number of pieces, if there is a possibility of taking with both pawn and checkers, it is obligatory to take with checkers. It is played mainly in Italy and some North African countries.
 *
 * @author Matteo Brachetta
 * @version 0.0
 */
public class damaGame implements game {
    private final UUID id;

    public damaGame() {
        this.id = UUID.randomUUID();
    }

    @Override
    public pawnBoard getBoard() {
        //TODO: Implement damaGame.getBoard
        return null;
    }

    @Override
    public void setBoard(pawnBoard newBoard) {
        //TODO: Implement damaGame.getBoard

    }

    @Override
    public String getId() {
        //TODO: Implement damaGame.getId
        return null;
    }

    @Override
    public String getName() {
        //TODO: Implement damaGame.getName
        return null;
    }

    @Override
    public player getWin() {
        //TODO: Implement damaGame.getWin
        return null;
    }

    @Override
    public void setPlayers(List<player> players) {
        //TODO: Implement damaGame.setPlayers

    }

    @Override
    public List<player> getPlayers() {
        //TODO: Implement damaGame.getPlayers
        return null;
    }

    @Override
    public player getPlayer(String idPlayer) {
        //TODO: Implement damaGame.getPlayer
        return null;
    }
}