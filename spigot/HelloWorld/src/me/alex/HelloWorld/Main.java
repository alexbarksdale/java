package me.alex.HelloWorld;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("hello") && sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("hello.use")) {
                player.sendMessage("Hello there " + ChatColor.GREEN + player.getDisplayName());
                return true;
            }
            player.sendMessage(ChatColor.RED + "No permission!" );
        } else {
            sender.sendMessage("Hello console!");
            return true;
        }
        return false;
    }
}
