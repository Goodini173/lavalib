package me.lavamen.lavalib.database;

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
                Connection c = null;
                try {
                    c = getConnection();
                    task.execute(c);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.runTaskAsynchronously(plugin);
    }

    @Override
    public void executeTasks(@NotNull SQLTask[] tasks) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Connection c = null;
                try {
                    c = getConnection();
                    Statement s = c.createStatement();
                    for (SQLTask task : tasks) {
                        task.execute(s);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.runTaskAsynchronously(plugin);
    }

    @NotNull
    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    protected abstract Connection getConnection() throws SQLException;
}
