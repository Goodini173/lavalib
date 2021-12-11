package me.lavamen.lavalib.database;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractAsyncDelayedDatabase extends AbstractAsyncDatabase implements IDelayedDatabase {

    private final List<SQLTask> buffer = new ObjectArrayList<>();
    protected final int delay;
    protected final int maxTasks;

    protected AbstractAsyncDelayedDatabase(@NotNull Plugin plugin, @NotNull String name) {
        this(plugin, name, 20, 20);
    }

    protected AbstractAsyncDelayedDatabase(@NotNull Plugin plugin, @NotNull String name, int delay, int maxTasks) {
        super(plugin, name);
        this.delay = delay;
        this.maxTasks = maxTasks;
        new BukkitRunnable() {
            @Override
            public void run() {
                clearBuffer();
            }
        }.runTaskTimerAsynchronously(plugin, delay, delay);
    }

    @Override
    public void addTask(@NotNull SQLTask task) {
        buffer.add(task);
        if(buffer.size() >= maxTasks) clearBuffer();
    }

    @Override
    public void clearBuffer() {
        if (buffer.isEmpty()) return;
        executeTasks(buffer.toArray(new SQLTask[0]));
        buffer.clear();
    }
}
