package me.alex.Doctor;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("doctor").setExecutor(new Heal());

    }

    @Override
    public void onDisable() {

    }
}
