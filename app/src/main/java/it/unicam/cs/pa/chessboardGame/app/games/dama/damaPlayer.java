package it.unicam.cs.pa.chessboardGame.app.games.dama;

import it.unicam.cs.pa.chessboardGame.structure.gameBoard;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.player;
import org.apache.commons.lang3.StringUtils;


import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <code>player</code> for damaGame
 */
public class damaPlayer implements player {
    private final UUID id;
    private final String name;
    private int score;

    /**
     * Constructor for <code>damaPlayer</code>
     *
     * @param name name of player
     */
    public damaPlayer(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.score = 0;
    }

    @Override
    public String getId() {
        return this.id.toString();
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void addScore(int score) {
        if (score > 0)
            this.score = score;
        else throw new IllegalArgumentException("error score");

    }

    @Override
    public void removeScore(int score) {
        if (score < 0)
            throw new IllegalArgumentException("the score is less 0");
        if (score > this.score)
            this.score = 0;

        this.score -= score;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof damaPlayer player)) return false;

        if (!Objects.equals(id, player.id)) return false;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public void executeMove(pawn pawnToMove, String move) {
        if (pawnToMove == null) throw new NullPointerException("the position is empty");
        if (pawnToMove.getOwner() == this) {
            move = StringUtils.deleteWhitespace(move).toUpperCase();
            switch (move) {
                case "FORWARDLEFT" -> pawnToMove.getMovement().forwardLeft();
                case "FORWARDRIGHT" -> pawnToMove.getMovement().forwardRight();
                case "BACKRIGHT" -> pawnToMove.getMovement().backRight();
                case "BACKLEFT" -> pawnToMove.getMovement().backLeft();
                default -> throw new IllegalArgumentException("move not correct");
            }
        } else throw new IllegalArgumentException("The pawn isn't correct");


    }

    @Override
    public void executeAutomaticMove(gameBoard board) {
        if (board == null) throw new NullPointerException("bard is null");
        List<pawn> lp = board.getPawnToMove(this.getId());
        int rand = ThreadLocalRandom.current().nextInt(0, lp.size());
        lp.get(rand).getMovement().randomMove();
    }
}
