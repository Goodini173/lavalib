package me.lavamen.lavalib.gui;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class SpecifiedGUIBuilder<T> extends GUIBuilder {

    private final Map<Integer, T> someStuff = new Int2ObjectArrayMap<>();

    public SpecifiedGUIBuilder<T> withStuff(int slot, T stuff) {
        someStuff.put(slot, stuff);
        return this;
    }

    /**
     * @param size how many lines will gui contain
     */
    @Override
    public @NotNull SpecifiedGUIBuilder<T> withSize(int size) {
        super.withSize(size);
        return this;
    }

    @Override
    public @NotNull SpecifiedGUIBuilder<T> setTemp(boolean temporary) {
        super.setTemp(temporary);
        return this;
    }

    @Override
    public @NotNull SpecifiedGUIBuilder<T> withName(@NotNull TextComponent name) {
        super.withName(name);
        return this;
    }

    @Override
    public @NotNull SpecifiedGUIBuilder<T> withName(@NotNull String name) {
        super.withName(name);
        return this;
    }

    @Override
    public @NotNull SpecifiedGUIBuilder<T> withItem(int slot, @NotNull ItemStack item) {
        super.withItem(slot, item);
        return this;
    }

    public @NotNull SpecifiedGUIBuilder<T> withInventoryHolder(InventoryHolder inventoryHolder) {
        super.withInventoryHolder(inventoryHolder);
        return this;
    }

    @Override
    public @NotNull SpecifiedGUI<T> build() {
        Inventory inv = Bukkit.createInventory(inventoryHolder, size, baseName);
        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
            inv.setItem(entry.getKey(), entry.getValue());
        }
        return new SpecifiedGUI<>(inv, temporary, someStuff);
    }

}
