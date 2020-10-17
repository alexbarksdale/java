package me.alex.kitgui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {
    public static Inventory kits;
    private final String invTitle = "Kit GUI";

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        createInv();
    }

    @Override
    public void onDisable() {
    }

    private void createInv() {
        Inventory inv = Bukkit.createInventory(null, 9, invTitle);
        ItemStack item = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.RED + "Noob kit");
        lore.add("\nClick here to get the kit!");
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        inv.setItem(3, item);

        item.setType(Material.IRON_BLOCK);
        meta = item.getItemMeta();
        meta.setDisplayName("Iron Kit");
        inv.setItem(4, item);

        kits = inv;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("kits") && sender instanceof Player) {
            Player player = (Player) sender;
            player.getInventory().getItem()

            player.openInventory(kits);
            return true;

        } else {
            sender.sendMessage("Unable to run in console!");
        }
        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().contains(invTitle)) return;
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;

        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);

        // Make sure the player is not clicking their inventory.
        if (e.getClickedInventory().getType() == InventoryType.PLAYER) return;

        if (e.getSlot() == 3 ) {
            if (!player.hasPermission("kits.noob")) {
                player.sendMessage("No permission");
                return;
            }
            dropChest(player, getNoobKit());
            player.closeInventory();
            player.updateInventory(); // Extra security for glitch items
            return;
        }
        if (e.getSlot() == 4 ) {
            if (!player.hasPermission("kits.iron")) {
                player.sendMessage("No permission");
                return;
            }
            dropChest(player, getIronKit());
            player.closeInventory();
            player.updateInventory(); // Extra security for glitch items
            return;
        }
    }

    private void dropChest(Player player, ItemStack[] items) {
        Location chest = null;
        if (player.getFacing() == BlockFace.NORTH) {
            chest = player.getLocation().add(0, 0, -1);
        }
        if (player.getFacing() == BlockFace.EAST) {
            chest = player.getLocation().add(1, 0, 0);
        }
        if (player.getFacing() == BlockFace.SOUTH) {
            chest = player.getLocation().add(0, 0, 1);
        }
        if (player.getFacing() == BlockFace.WEST) {
            chest = player.getLocation().add(-1, 0, 0);
        }

        if (chest.getBlock().getType() != Material.AIR) {
            player.sendMessage(ChatColor.RED + "Can't spawn chest here!");
            return;
        }

        Block block = chest.getBlock();
        block.setType(Material.CHEST);
        Chest c = (Chest) block.getState();
        c.getInventory().addItem(items);
    }

    private ItemStack[] getNoobKit() {
        return new ItemStack[]{
                new ItemStack(Material.COAL, 16),
                new ItemStack(Material.LEATHER_BOOTS, 1),
                new ItemStack(Material.LEATHER_CHESTPLATE, 1),
        };
    }
    private ItemStack[] getIronKit() {
        return new ItemStack[]{
                new ItemStack(Material.IRON_BLOCK, 16),
                new ItemStack(Material.IRON_CHESTPLATE, 1),
                new ItemStack(Material.IRON_BOOTS, 1),
        };
    }
}
