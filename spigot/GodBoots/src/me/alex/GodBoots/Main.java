package me.alex.GodBoots;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("godboots") && sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("godboots.use")) {
                player.sendMessage("No permission!");
                return false;
            }

            // Inventory is full
            if (player.getInventory().firstEmpty() == -1) {
                Location loc = player.getLocation();
                World world = player.getWorld();

                world.dropItemNaturally(loc, getItem());
                player.sendMessage("Dropped an item near you!");
                return true;
            }

            player.getInventory().addItem(getItem());
            player.sendMessage("Item received!");
            return true;
        } else {
            sender.sendMessage("Unable to use command in console!");
        }

        return false;
    }

    public ItemStack getItem() {
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = boots.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "God Boots");
        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Special boots");
        meta.setLore(lore);

        meta.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setUnbreakable(true);

        boots.setItemMeta(meta);

        return boots;
    }

    @EventHandler
    public void onJump(PlayerMoveEvent e) {
        Player player = (Player) e.getPlayer();

        if (player.getInventory().getBoots() != null) {
            if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("God Boots")){
                // Make sure there is a lore because people can use an anvil to change the name
                if (player.getInventory().getBoots().getItemMeta().hasLore()) {
                    if (e.getFrom().getY() < e.getTo().getY() &&
                            player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
                        player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onFall(EntityDamageEvent e) {
       if (e.getEntity() instanceof Player) {
           Player player = (Player) e.getEntity();
           if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
               if (player.getInventory().getBoots() != null) {
                   if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("God Boots")){
                       // Make sure there is a lore because people can use an anvil to change the name
                       if (player.getInventory().getBoots().getItemMeta().hasLore()) {
                           e.setCancelled(true);
                       }
                   }
               }
           }
       }
    }
}
