package me.alex.tabcompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class StatTab implements TabCompleter {
    List <String> arguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (arguments.isEmpty()) {
            for (StatCommand.Commands command : StatCommand.Commands.values()) {
                arguments.add(command.toString().toLowerCase());
            }
        }

        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for (String arg : arguments) {
                if (arg.toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(arg);
                }
            }
        }
        return completions;
    }
}
