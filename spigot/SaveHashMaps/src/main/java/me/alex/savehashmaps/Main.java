package me.alex.savehashmaps;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin implements Listener {
    public static Map<String, ItemStack[]> menus = new HashMap<String, ItemStack[]>();

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
        if (getConfig().contains("data")) {
            hydrateInvs();
        }
    }

    @Override
    public void onDisable() {
        if (!menus.isEmpty()) {
            saveInvs();
        }
    }

    public void saveInvs() {
        for (Map.Entry<String, ItemStack[]> entry : menus.entrySet()) {
            this.getConfig().set("data." + entry.getKey(), entry.getValue());
        }
        saveConfig();
    }

    public void hydrateInvs() {
        getConfig().getConfigurationSection("data").getKeys(false).forEach(key -> {
            @SuppressWarnings("unchecked")
            ItemStack[] content = ((List<ItemStack>) getConfig().get("data." + key)).toArray(new ItemStack[0]);
            menus.put(key, content);
        });

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("pv") && sender instanceof Player) {
            Player player = (Player) sender;
            Inventory inv = Bukkit.createInventory(player, 54, player.getName() + "'s Private Vault");

            if (menus.containsKey(player.getUniqueId().toString())) {
                inv.setContents(menus.get(player.getUniqueId().toString()));
            }

            player.openInventory(inv);
            return true;
        } else {
            sender.sendMessage("Unable to run in console!");
        }
        return false;
    }

    @EventHandler
    public void onGUIClose(InventoryCloseEvent e) {
        if (e.getView().getTitle().contains(e.getPlayer().getName() + "'s Private Vault")){
            menus.put(e.getPlayer().getUniqueId().toString(), e.getInventory().getContents());
        }

    }
}








