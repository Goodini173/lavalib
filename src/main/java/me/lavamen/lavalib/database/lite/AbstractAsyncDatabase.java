package me.lavamen.lavalib.database.lite;

import me.lavamen.lavalib.database.IDatabase;
import me.lavamen.lavalib.database.SQLTask;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractAsyncDatabase implements IDatabase {

    @NotNull
    protected final String name;
    @NotNull
    protected final Plugin plugin;
    protected Connection connection;


    protected AbstractAsyncDatabase(@NotNull Plugin plugin, @NotNull String name) {
        this.plugin = plugin;
        this.name = name;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public void executeTask(@NotNull SQLTask task) {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    if(connection == null || connection.isClosed()) connection = getConnection();
                    task.execute(connection);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }.runTaskAsynchronously(plugin);
    }

    @Override
    public void executeTasks(@NotNull SQLTask[] tasks) {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    if(connection == null || connection.isClosed()) connection = getConnection();
                    Statement s = connection.createStatement();
                    for (SQLTask task : tasks) {
                        task.execute(s);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(plugin);
    }

    @NotNull
    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    protected abstract @NotNull Connection getConnection() throws SQLException;
}
