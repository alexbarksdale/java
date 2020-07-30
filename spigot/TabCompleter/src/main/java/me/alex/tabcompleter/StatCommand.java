package me.alex.tabcompleter;

import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatCommand implements CommandExecutor {
    public enum Commands {
        DEATHS, KILLS;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("stats") && sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage("Usage: /stats [deaths, kills]");
                return true;
            }

            if (args.length == 1) {
                switch (Commands.valueOf(args[0].toUpperCase())) {
                    case DEATHS:
                        player.sendMessage("Deaths: " + player.getStatistic(Statistic.DEATHS));
                        break;
                    case KILLS:
                        player.sendMessage("Kills: " + player.getStatistic(Statistic.PLAYER_KILLS));
                        break;
                    default:
                        player.sendMessage("Unknown options");
                        return false;
                }
                return true;
            } else {
                player.sendMessage("Only takes 1 arg");
                return false;
            }

        } else {
            sender.sendMessage("Unable to run in console!");
        }

        return false;
    }
}
