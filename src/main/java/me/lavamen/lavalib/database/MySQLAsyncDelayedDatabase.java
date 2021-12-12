package me.lavamen.lavalib.database;

import me.lavamen.lavalib.database.lite.AbstractAsyncDelayedDatabase;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Executors;


public class MySQLAsyncDelayedDatabase extends AbstractAsyncDelayedDatabase {

    private final String url;
    private final String user;
    private final String password;

    public MySQLAsyncDelayedDatabase(@NotNull Plugin plugin, @NotNull String name, @NotNull String host, @NotNull String port, @NotNull String user, @NotNull String password) throws SQLException {
        this(plugin, name, 20, 20, host, port, user, password);
    }

    public MySQLAsyncDelayedDatabase(@NotNull Plugin plugin, @NotNull String name, int delay, int maxTasks, @NotNull String host, @NotNull String port, @NotNull String user, @NotNull String password) throws SQLException {
        super(plugin, name, delay, maxTasks);
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + name;
        this.user = user;
        this.password = password;
        connection = getConnection();
    }

    @Override
    protected @NotNull Connection getConnection() throws SQLException {
        Connection c = DriverManager.getConnection(url, user, password);
        c.setNetworkTimeout(Executors.newSingleThreadExecutor(), delay * 100);
        return c;
    }
}
