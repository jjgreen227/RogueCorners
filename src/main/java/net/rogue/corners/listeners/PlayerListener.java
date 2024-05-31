package net.rogue.corners.listeners;

import com.google.common.base.*;
import net.rogue.corners.game.*;
import net.rogue.corners.team.*;
import net.rogue.corners.team.events.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class PlayerListener implements Listener {

    private final Game game = new Game();

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        if (this.game.isInSession()) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Game is in session. You must wait until game is over!");
            return;
        }
        event.allow();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // These events will execute as soon as a player joins

        Player player = event.getPlayer();

        // Check to see if the game is in session here as well.
        if (this.game.isInSession()) {
            // If it is then do nothing because @PlayerLoginEvent handles this.
            // But this is unsafe check, so we should do something later.
            return;
        }

        // Check to see if a player isn't full health or hunger
        if (player.getHealth() < 20.0 || player.getFoodLevel() < 20) {
            // Set it both to full
            player.setHealth(20.0);
            player.setFoodLevel(20);
        }
        // Give player the lobby items
        giveLobbyItems(player);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        if (item == null) return;
        ItemMeta meta = event.getItem().getItemMeta();
        Preconditions.checkNotNull(meta, "Cannot be null.");
        String prefix = ChatColor.AQUA + "Join " + ChatColor.GRAY + "- ";
        String red = prefix + ChatColor.RED + "Red Team";
        String green = prefix + ChatColor.GREEN + "Green Team";
        String blue = prefix + ChatColor.AQUA + "Blue Team";
        String yellow = prefix + ChatColor.YELLOW + "Yellow Team";
        if (!(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK))) {
            return;
        }
        if (meta.getDisplayName().equals(red)) {
            Bukkit.getServer().getPluginManager().callEvent(new TeamJoinEvent(player, ColorTeam.RED));
        } else if (meta.getDisplayName().equals(green)) {
            Bukkit.getServer().getPluginManager().callEvent(new TeamJoinEvent(player, ColorTeam.GREEN));
        } else if (meta.getDisplayName().equals(blue)) {
            Bukkit.getServer().getPluginManager().callEvent(new TeamJoinEvent(player, ColorTeam.BLUE));
        } else if (meta.getDisplayName().equals(yellow)) {
            Bukkit.getServer().getPluginManager().callEvent(new TeamJoinEvent(player, ColorTeam.YELLOW));
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (this.game.isInSession()) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (this.game.isInSession()) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (this.game.isInSession()) {
            return;
        }
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        if (event.getAction() == InventoryAction.HOTBAR_SWAP
                || event.getAction() == InventoryAction.PICKUP_ALL
                || event.getAction() == InventoryAction.PLACE_ALL
                || event.getAction() == InventoryAction.PICKUP_HALF
                || event.getAction() == InventoryAction.DROP_ONE_SLOT
                || event.getAction() == InventoryAction.DROP_ALL_SLOT
                || event.getAction() == InventoryAction.DROP_ALL_CURSOR
                || event.getAction() == InventoryAction.DROP_ONE_CURSOR) {
            event.setCancelled(true);
        }
    }

    private void giveLobbyItems(Player player) {
        player.getInventory().clear();
        ItemStack red = new ItemStack(Material.RED_WOOL);
        ItemStack green = new ItemStack(Material.LIME_WOOL);
        ItemStack blue = new ItemStack(Material.LIGHT_BLUE_WOOL);
        ItemStack yellow = new ItemStack(Material.YELLOW_WOOL);
        ItemMeta redMeta = red.getItemMeta();
        ItemMeta greenMeta = green.getItemMeta();
        ItemMeta blueMeta = blue.getItemMeta();
        ItemMeta yellowMeta = yellow.getItemMeta();
        Preconditions.checkNotNull(redMeta, "Cannot be null.");
        Preconditions.checkNotNull(greenMeta, "Cannot be null.");
        Preconditions.checkNotNull(blueMeta, "Cannot be null.");
        Preconditions.checkNotNull(yellowMeta, "Cannot be null.");
        String prefix = ChatColor.AQUA + "Join " + ChatColor.GRAY + "- ";
        redMeta.setDisplayName(prefix + ChatColor.RED + "Red Team");
        greenMeta.setDisplayName(prefix + ChatColor.GREEN + "Green Team");
        blueMeta.setDisplayName(prefix + ChatColor.AQUA + "Blue Team");
        yellowMeta.setDisplayName(prefix + ChatColor.YELLOW + "Yellow Team");
        red.setItemMeta(redMeta);
        green.setItemMeta(greenMeta);
        blue.setItemMeta(blueMeta);
        yellow.setItemMeta(yellowMeta);

        player.getInventory().setItem(1, red);
        player.getInventory().setItem(3, green);
        player.getInventory().setItem(5, blue);
        player.getInventory().setItem(7, yellow);
    }
}
