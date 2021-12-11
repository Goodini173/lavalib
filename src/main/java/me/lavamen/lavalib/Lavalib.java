package me.lavamen.lavalib;

import me.lavamen.lavalib.commands.StressTestCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lavalib extends JavaPlugin {

    private static Lavalib instance;

    @Override
    public void onEnable() {
        instance = this;
      //  getCommand("stresstest").setExecutor(new StressTestCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Lavalib getInstance() {
        return instance;
    }
}
