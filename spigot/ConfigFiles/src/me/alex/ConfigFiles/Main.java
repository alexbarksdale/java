package me.alex.ConfigFiles;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("randblocks") && sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("randblocks.use")) {
                player.sendMessage("No permission!");
                return false;
            }

            if (args.length == 0) {
                player.sendMessage("Usage: /randblocks reload");
            }

            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
//                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("reload.message"));
                    // For multiple lines
                    for (String msg : this.getConfig().getStringList("reload.message")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                    }
                    this.reloadConfig();
                }
            }
        } else {
            sender.sendMessage("Unable to run in console!");
        }
        return false;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        this.getConfig().getConfigurationSection("blocks").getKeys(false).forEach(key -> {
            if (key.equalsIgnoreCase(e.getBlock().getType().toString())) {
               ItemStack[] items = new ItemStack[this.getConfig().getStringList("blocks." + key).size()];
               ItemStack item = null;
               int pos = 0;
               Random r = new Random();

               for (String i : this.getConfig().getStringList("blocks." + key)) {
                   try {
                       item = new ItemStack(Material.matchMaterial(i), r.nextInt(16) + 1);
                   } catch (Exception err) {
                       // Give the item they are mining if something went wrong.
                       System.out.println("Unable to find material" + i + ". " + err);
                       item = new ItemStack(Material.matchMaterial(key));
                   }
                   items[pos] = item;
                   pos++;
               }
               int num = r.nextInt(items.length);
               e.setDropItems(false);
               World world = e.getPlayer().getWorld();
               world.dropItemNaturally(e.getPlayer().getLocation(), items[num]);
            }
        });
    }
}
