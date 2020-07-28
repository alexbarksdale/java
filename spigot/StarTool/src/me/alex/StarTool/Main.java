package me.alex.StarTool;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new StarEvents(), this);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("startool") && sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("startool.use")) {
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
        ItemStack trident = new ItemStack(Material.TRIDENT);
        ItemMeta meta = trident.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "Ancient Trident");
        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7(Right Click) &cSpawn Minions"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7(Left Click) &cShoot explosives"));
        meta.setLore(lore);

        // Makes the item come back to you
        meta.addEnchant(Enchantment.LOYALTY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setUnbreakable(true);

        trident.setItemMeta(meta);

        return trident;
    }
}
