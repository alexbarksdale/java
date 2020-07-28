package me.alex.StarTool;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class StarEvents implements Listener {
    public List<String> list = new ArrayList<String>();

    // Access things in Main with and through 'plugin'
//    static Main plugin;
//    public StarEvents(Main instance) {
//        plugin = instance;
//    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = (Player) e.getPlayer();
        if (player.getInventory().getItemInMainHand().getType().equals(Material.TRIDENT)) {
            if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                // Right click
                if (e.getAction() == Action.RIGHT_CLICK_AIR) {
                    if (!list.contains(player.getName())) {
                        list.add(player.getName());
                        return;
                    }
                }

                // Left click
                if (e.getAction() == Action.LEFT_CLICK_AIR) {
                    player.launchProjectile(Fireball.class);
                }
            }
        }

        list.remove(player.getName());
    }

    @EventHandler
    public void onLand(ProjectileHitEvent e) {
        if (e.getEntityType() == EntityType.TRIDENT) {
            if (e.getEntity().getShooter() instanceof Player) {
                Player player = (Player) e.getEntity().getShooter();
                if (list.contains(player.getName())) {
                    // Spawn zombies
                    Location loc = e.getEntity().getLocation();
                    loc.setY(loc.getY() + 1);
                    loc.getWorld().spawnEntity(loc, EntityType.DROWNED);
                }
            }
        }
    }
}
