package me.lavamen.lavalib.database;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Executors;

public class MySQLAsyncDatabase extends AbstractAsyncDatabase {

    private final String url;
    private final String user;
    private final String password;

    protected MySQLAsyncDatabase(@NotNull Plugin plugin, @NotNull String name, @NotNull String host, @NotNull String port, @NotNull String user, @NotNull String password) throws SQLException {
        super(plugin, name);
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + name;
        this.user = user;
        this.password = password;
        connection = getConnection();
    }

    @Override
    protected @NotNull Connection getConnection() throws SQLException {
        Connection c = DriverManager.getConnection(url, user, password);
        c.setNetworkTimeout(Executors.newSingleThreadExecutor(), 10000);
        return c;
    }
}
