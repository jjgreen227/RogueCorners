package net.rogue.corners;

import lombok.*;
import net.rogue.corners.listeners.*;
import org.bukkit.*;
import org.bukkit.plugin.java.*;

public class Corners extends JavaPlugin {

    @Getter private static Corners instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getConsoleSender().sendMessage("===================================");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("\t\tEnabling Rogue Corners");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("===================================");
        this.registerCommands();
        this.registerListeners();
    }

    private void registerCommands() {
    }

    private void registerListeners() {
       this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
       this.getServer().getPluginManager().registerEvents(new WorldListener(), this);
       this.getServer().getPluginManager().registerEvents(new TeamListener(), this);
    }

}
