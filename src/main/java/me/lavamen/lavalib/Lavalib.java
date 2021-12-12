package me.lavamen.lavalib;

import me.lavamen.lavalib.commands.BaseCommandExecutor;
import me.lavamen.lavalib.commands.CommandManager;
import me.lavamen.lavalib.example.ExampleCommand;
import me.lavamen.lavalib.gui.SpecifiedGUIBuilder;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lavalib extends JavaPlugin {

    private static Lavalib instance;

    @Override
    public void onEnable() {
        instance = this;
        CommandManager.register(new ExampleCommand());
        getCommand("example").setExecutor(new BaseCommandExecutor());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Lavalib getInstance() {
        return instance;
    }
}
