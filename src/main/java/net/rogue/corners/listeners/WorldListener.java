package net.rogue.corners.listeners;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.world.*;

public class WorldListener implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Wither && entity instanceof Villager) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        World world = event.getWorld();
        LivingEntity entity = (LivingEntity) world.getLivingEntities();
        if (entity instanceof Villager) {
            return;
        }
        if (entity instanceof Animals && entity instanceof Monster) {
            entity.remove();
        }
    }
}
