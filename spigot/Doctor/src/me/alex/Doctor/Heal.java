package me.alex.Doctor;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Heal implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("doctor") && sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("doctor.use")) {
                player.sendMessage(ChatColor.RED + "No permission!");
                return true;
            }

            if (args.length == 0) {
                TextComponent message = new TextComponent("Would you like to be healed?");
                message.setColor(ChatColor.GOLD);
                message.setBold(true);
                message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/doctor healme"));
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click to heal!").color(ChatColor.GRAY).create()));
                player.spigot().sendMessage(message);
                return true;
            }

            if (args[0].equalsIgnoreCase("healme")) {
                player.setHealth(20.0);
                player.sendMessage("You've been healed!");
                return true;
            }
            player.sendMessage(ChatColor.RED + "Usage: /doctor");
            return true;
        } else {
            sender.sendMessage("Unable to use this command in console!");
        }
        return false;
    }
}
