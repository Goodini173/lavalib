package me.lavamen.lavalib.database;

import org.jetbrains.annotations.NotNull;

/**
 * Delayed database assumes that opening a connection takes longer than
 * completing all tasks.
 * You should use delayed db if you want to access it multiple times per second
 * and you are not worried about delay, for example, logger
 */
public interface IDelayedDatabase {

    void addTask(@NotNull SQLTask task);

    void clearBuffer();
}
