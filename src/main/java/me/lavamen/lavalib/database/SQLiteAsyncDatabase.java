package me.lavamen.lavalib.database;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteAsyncDatabase extends AbstractAsyncDatabase {
    private final String url;

    protected SQLiteAsyncDatabase(@NotNull Plugin plugin, @NotNull String name) {
        super(plugin, name);
        url = "jdbc:sqlite:" + plugin.getDataFolder() + File.separator + name;
        //  Class.forName("org.sqlite.JDBC").getConstructor().newInstance();
    }

    @Override
    protected @NotNull Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
