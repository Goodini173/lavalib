package me.lavamen.lavalib.gui;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Map;

/**
 * You should use this class to link some GUI slots with external objects.
 * For example, kits
 */
public class SpecifiedGUI<T> extends GUI {

    protected final Map<Integer, T> someStuff;

    public SpecifiedGUI(@NotNull Inventory inventory, boolean temporary) {
        super(inventory, temporary);
        someStuff = new Int2ObjectArrayMap<>(inventory.getSize());
    }

    public SpecifiedGUI(@NotNull Inventory inventory, boolean temporary, @NotNull Map<Integer, T> map) {
        super(inventory, temporary);
        someStuff = new Int2ObjectArrayMap<>(inventory.getSize());
        someStuff.putAll(map);
    }

    public void link(int slot, @NotNull T stuff) {
        someStuff.put(slot, stuff);
    }

    public void link(@NotNull Map<Integer, T> map) {
        someStuff.putAll(map);
    }

    public void clear() {
        someStuff.clear();
    }

    @Nullable
    public T getStuff(int slot) {
        return someStuff.get(slot);
    }
}
