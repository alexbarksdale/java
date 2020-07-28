package me.alex.Launch;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("launch") || label.equalsIgnoreCase("ln") && sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage("Zooooooom!");
                player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
                return true;
            }
            if (isNum(args[0])){
                int multiplier = Integer.parseInt(args[0]);
                player.sendMessage("Zooooooom with " + multiplier + "x multiplier!");
                player.setVelocity(player.getLocation().getDirection().multiply(multiplier).setY(2));
            } else {
                player.sendMessage(ChatColor.RED + "You must provide a number value!");
            }
        } else {
            sender.sendMessage("Unable to execute this command in the console!");
        }
        return false;
    }

    public boolean isNum(String num) {
        try {
            Integer.parseInt(num);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
