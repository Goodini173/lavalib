package me.lavamen.lavalib.database;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface IDatabase {

    void executeTask(@NotNull SQLTask task);

    void executeTasks(@NotNull SQLTask[] tasks);

    @NotNull
    String getName();

    @NotNull
    Plugin getPlugin();
}
