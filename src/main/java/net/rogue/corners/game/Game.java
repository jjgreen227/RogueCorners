package net.rogue.corners.game;

import lombok.*;
import net.rogue.corners.team.*;

import java.util.*;

@Getter
@Setter
public class Game {

    private final GameStatus status;
    private List<Team> teams;

    public Game() {
        this.status = GameStatus.PREGAME;
        this.teams = new ArrayList<>();
    }

    public void start() {

    }

    public void end() {}

    public boolean isInSession() {
        return this.status == GameStatus.RUNNING;
    }

}
