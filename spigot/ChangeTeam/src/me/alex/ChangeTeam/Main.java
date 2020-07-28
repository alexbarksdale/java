package me.alex.ChangeTeam;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {
    public Inventory inv;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        createInv();
    }

    @Override
    public void onDisable() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("changeteam") && sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("changeteam.use")) {
                player.sendMessage("No permission!");
                return false;
            }

            player.openInventory(inv);
            return true;
        } else {
            sender.sendMessage("Unable to run command in console!");
        }
        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!e.getInventory().equals(inv)) return;
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;

        // Prevent changing inventory
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();

        ItemStack[] armor = player.getEquipment().getArmorContents();

        switch (e.getSlot()) {
            case 0:
                // Blue
                armor = changeColor(armor, Color.BLUE);
                player.sendMessage("Joined the blue team!");
                break;
            case 1:
                // Red
                armor = changeColor(armor, Color.RED);
                player.sendMessage("Joined the red team!");
                break;
            case 2:
                // Orange
                armor = changeColor(armor, Color.ORANGE);
                player.sendMessage("Joined the orange team!");
                break;
            default:
                player.closeInventory();

        }
        player.getEquipment().setArmorContents(armor);
    }

    public ItemStack[] changeColor(ItemStack[] items, Color color) {
        for (ItemStack item : items) {
            try {
                Material itemType = item.getType();
                if (itemType == Material.LEATHER_BOOTS ||
                    itemType == Material.LEATHER_LEGGINGS ||
                    itemType == Material.LEATHER_CHESTPLATE ||
                    itemType == Material.LEATHER_HELMET) {
                    LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
                    meta.setColor(color);
                    item.setItemMeta(meta);
                }
            } catch (Exception e) {
                System.out.println("Not wearing leather armor.");
            }
        }
        return items;
    }


    public void createInv() {
        inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Change Team");

        ItemStack item = new ItemStack(Material.BLUE_CONCRETE);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add("Click to join");
        meta.setLore(lore);

        // Blue Team
        meta.setDisplayName("Blue Team");
        item.setItemMeta(meta);
        inv.setItem(0, item);

        // Red Team
        item.setType(Material.RED_CONCRETE);
        meta.setDisplayName("Red Team");
        item.setItemMeta(meta);
        inv.setItem(1, item);

        // Orange Team
        item.setType(Material.ORANGE_CONCRETE);
        meta.setDisplayName("Orange Team");
        item.setItemMeta(meta);
        inv.setItem(2, item);

        // Close Menu
        item.setType(Material.BARRIER);
        meta.setDisplayName("Close Menu");
        lore.clear();
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(3, item);
    }
}
