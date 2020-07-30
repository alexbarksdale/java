package me.alex.tabcompleter;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("stats").setExecutor(new StatCommand());
        this.getCommand("stats").setTabCompleter(new StatTab());
    }

    @Override
    public void onDisable() {
    }

}
