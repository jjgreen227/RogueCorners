package net.rogue.corners;

import lombok.*;
import org.bukkit.plugin.java.*;

public class Corners extends JavaPlugin {

    @Getter private static Corners instance;

    @Override
    public void onEnable() {
        instance = this;

    }
}
