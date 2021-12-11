package me.lavamen.lavalib.gui;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

/**
 * You should use this class to link some GUI slots with external objects.
 * For example, kits
 */
public class SpecifiedGUI<T> extends GUI {

    protected final Map<Integer, T> someStuff = new Int2ObjectArrayMap<>();

    public SpecifiedGUI(@NotNull Inventory inventory) {
        super(inventory);
    }

    public SpecifiedGUI(@NotNull Inventory inventory, @NotNull Map<Integer, T> map) {
        super(inventory);
        someStuff.putAll(map);
    }

    public void link(int slot, @NotNull T stuff) {
        someStuff.put(slot, stuff);
    }

    public void link(@NotNull Map<Integer, T> map) {
        someStuff.putAll(map);
    }
}
