package me.lavamen.lavalib.database;

import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLAsyncDelayedDatabase extends AbstractAsyncDelayedDatabase {

    private final String url;
    private final String user;
    private final String password;

    public MySQLAsyncDelayedDatabase(Plugin plugin, String name, String host, String port, String user, String password) throws SQLException {
        this(plugin, name, 20, 20, host, port, user, password);
    }

    public MySQLAsyncDelayedDatabase(Plugin plugin, String name, int delay, int maxTasks, String host, String port, String user, String password) throws SQLException {
        super(plugin, name, delay, maxTasks);
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + name;
        this.user = user;
        this.password = password;
        getConnection().close();
    }

    @Override
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
