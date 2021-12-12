package me.lavamen.lavalib.database;

import org.apache.commons.lang.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.function.Consumer;

public class SQLTask {

    @NotNull
    private final String sql;
    @Nullable
    private final Consumer<ResultSet> consumer;

    /**
     * Creates SQL update task
     */
    public SQLTask(@NotNull String sql) {
        this.sql = sql;
        this.consumer = null;
    }

    /**
     * Creates SQL query task if consumer not null
     */
    public SQLTask(@NotNull String sql, @NotNull Consumer<ResultSet> consumer) {
        Validate.notEmpty(sql, "SQL command cannot be null/empty");
        this.sql = sql;
        this.consumer = consumer;
    }

    public @NotNull String getSql() {
        return sql;
    }

    public @Nullable Consumer<ResultSet> getConsumer() {
        return consumer;
    }

    public void execute(@NotNull Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        if (consumer == null) ps.executeUpdate();
        else consumer.accept(ps.executeQuery());
    }

    public void execute(@NotNull Statement statement) throws SQLException {
        if (consumer == null) statement.executeUpdate(sql);
        else consumer.accept(statement.executeQuery(sql));
    }
}
