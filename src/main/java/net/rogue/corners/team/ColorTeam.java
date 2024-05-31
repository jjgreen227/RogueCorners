package net.rogue.corners.team;

import lombok.*;

@Getter
public enum ColorTeam {
    RED("Red"),
    GREEN("Green"),
    YELLOW("Yellow"),
    BLUE("Blue");

    private final String name;

    ColorTeam(String name) {
        this.name = name;
    }
}
