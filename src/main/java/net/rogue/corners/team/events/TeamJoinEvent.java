package net.rogue.corners.team.events;

import lombok.*;
import net.rogue.corners.team.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

@SuppressWarnings("NullableProblems")
@Getter
@Setter
public class TeamJoinEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private ColorTeam colorTeam;
    private Team team; //TODO: Returns null MUST FIX
    private Player player;

    public TeamJoinEvent(Player player, ColorTeam colorTeam) {
        this.player = player;
        this.colorTeam = colorTeam;
        this.team = new Team(this.player, this.colorTeam); //TODO: Returns null MUST FIX
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
