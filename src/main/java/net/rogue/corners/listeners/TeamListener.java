package net.rogue.corners.listeners;

import net.rogue.corners.team.*;
import net.rogue.corners.team.events.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class TeamListener implements Listener {

    @EventHandler
    public void onTeamJoin(TeamJoinEvent event) {
        Team team = event.getTeam();
        Player player = event.getPlayer();
        team.setTeam(player, team.getColorTeam());
        switch (team.getColorTeam()) {
            case RED -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have joined: &cRed Team"));
            case GREEN -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have joined: &aGreen Team"));
            case BLUE -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have joined: &bBlue Team"));
            case YELLOW -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have joined: &eYellow Team"));
        }
    }
}
