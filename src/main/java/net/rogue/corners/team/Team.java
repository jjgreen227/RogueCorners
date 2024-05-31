package net.rogue.corners.team;

import lombok.*;
import org.bukkit.entity.*;

import java.util.*;

@Getter
public class Team {

    private final ColorTeam colorTeam;
    private final Player player;
    private final List<Player> members;
    private final Map<Player, ColorTeam> colorTeamMap;

    public Team(Player player, ColorTeam colorTeam) {
        this.player = player;
        this.colorTeam = colorTeam;
        this.members = new ArrayList<>();
        this.colorTeamMap = new HashMap<>();
    }

    public void setTeam(Player player, ColorTeam team) {
        if (isInTeam(player)) {
            player.sendMessage("Player already in a team!");
        } else {
            this.members.add(player);
            this.colorTeamMap.put(player, team);
        }
    }

    public boolean isInTeam(Player player) {
        return this.members.contains(player) && this.colorTeamMap.containsKey(player);
    }

}
