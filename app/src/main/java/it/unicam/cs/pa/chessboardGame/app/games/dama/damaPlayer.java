package it.unicam.cs.pa.chessboardGame.app.games.dama;

import it.unicam.cs.pa.chessboardGame.structure.player;
import java.util.Objects;
import java.util.UUID;

public class damaPlayer implements player {
    private final UUID id;
    private final String name;
    private int score;

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


}
