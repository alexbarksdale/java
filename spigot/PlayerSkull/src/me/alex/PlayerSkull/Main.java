package me.alex.PlayerSkull;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("skull") && sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("skull.use")) {
                player.sendMessage("No permission!");
                return false;
            }

            if (args.length == 0) {
                player.sendMessage("Here is your skull.");
                player.getInventory().addItem(getPlayerHead(player.getName()));
                return true;
            }

            player.sendMessage("Here is a player's skull.");
            player.getInventory().addItem(getPlayerHead(args[0]));
            return true;
        } else {
            sender.sendMessage("Unable to use in console!");
        }
        return false;
    }

    @SuppressWarnings("deprecation")
    public ItemStack getPlayerHead(String player) {
        boolean isNewVersion = Arrays.stream(Material.values())
                .map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");

        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        ItemStack item = new ItemStack(type, 1);

        if (!isNewVersion) {
            item.setDurability((short) 3);
        }

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(player);

        item.setItemMeta(meta);
        return item;
    }
}
