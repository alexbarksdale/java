package me.alex.Gamble;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends JavaPlugin implements Listener {
    List<Inventory> invs = new ArrayList<Inventory>();
    public static ItemStack[] contents;
    private int itemIdx = 0;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("gamble") && sender instanceof Player) {
            Player player = (Player) sender;

            if(!player.hasPermission("gamble.use")) {
                player.sendMessage("No permission!");
                return false;
            }

            ItemStack fee = new ItemStack(Material.DIAMOND);
            fee.setAmount(3);
            if (player.getInventory().getItemInMainHand().isSimilar(fee)) {
                player.getInventory().removeItem(fee);
                // Spin the GUI
                spin(player);
                return true;
            }

            player.sendMessage("Not enough funds! 3 Diamonds required.");
            return false;
        } else {
            sender.sendMessage("Unable to run command in console!");
        }
        return false;
    }

    public void shuffle(Inventory inv) {
        if(contents == null) {
            ItemStack[] items = new ItemStack[10];

            items[0] = new ItemStack(Material.DIRT, 5);
            items[1] = new ItemStack(Material.DIAMOND, 3);
            items[2] = new ItemStack(Material.EMERALD, 7);
            items[3] = new ItemStack(Material.BEACON, 1);
            items[4] = new ItemStack(Material.GRANITE, 20);
            items[5] = new ItemStack(Material.TRIDENT, 2);
            items[6] = new ItemStack(Material.RED_CARPET, 20);
            items[7] = new ItemStack(Material.BLUE_CONCRETE, 20);
            items[8] = new ItemStack(Material.ORANGE_DYE, 3);
            items[9] = new ItemStack(Material.LAPIS_BLOCK, 20);

            contents = items;
        }

        int startingIdx = ThreadLocalRandom.current().nextInt(contents.length);

        for (int i=0; i < startingIdx; i++) {
            // Put items in the middle of the inv
            for (int itemStacks=9; itemStacks < 18; itemStacks++) {
                inv.setItem(itemStacks, contents[(itemStacks + itemIdx) % contents.length]);
            }
            itemIdx++;
        }

        ItemStack item = new ItemStack(Material.HOPPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("|");
        item.setItemMeta(meta);
        inv.setItem(4, item);
    }

    public void spin(final Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Spinnnnn Awayy");
        shuffle(inv);
        invs.add(inv);
        player.openInventory(inv);

        Random r = new Random();
        double seconds = 7.0 + (12.0 - 7.0) * r.nextDouble();

        new BukkitRunnable() {
            double delay = 0;
            int ticks = 0;
            boolean done = false;

            public void run() {
                if (done) return;

                ticks++;

                delay += 1 / (20 * seconds);

                if (ticks > delay * 10) {
                    ticks = 0;

                    for (int itemStacks = 9; itemStacks < 18; itemStacks++) {
                        inv.setItem(itemStacks, contents[(itemStacks + itemIdx) % contents.length]);
                    }
                    itemIdx++;

                    if (delay >= .5) {
                        done = true;
                        new BukkitRunnable() {
                           public void run() {
                               ItemStack item = inv.getItem(13);
                               // TODO: Check if inv is full
                               player.getInventory().addItem(item);
                               player.updateInventory();
                               player.closeInventory();
                               cancel();
                           }
                        }.runTaskLater(Main.getPlugin(Main.class), 50);
                        cancel();
                    }
                }
            }
        }.runTaskTimer(this, 0, 1);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!invs.contains(e.getInventory())) return;
        e.setCancelled(true);
    }
}
