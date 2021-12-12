package me.lavamen.lavalib.gui;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class SpecifiedGUIBuilder<T> extends GUIBuilder{

    private final Map<Integer, T> someStuff = new Int2ObjectArrayMap<>();

    public SpecifiedGUIBuilder<T> withStuff(int slot, T stuff) {
        someStuff.put(slot, stuff);
        return this;
    }

    /**
     * @param size how many lines will gui contain
     */
    @Override
    public SpecifiedGUIBuilder<T> withSize(int size) {
        super.withSize(size);
        return this;
    }

    @Override
    public SpecifiedGUIBuilder<T> setTemp(boolean temporary) {
        super.setTemp(temporary);
        return this;
    }

    @Override
    public SpecifiedGUIBuilder<T> withName(TextComponent name) {
        super.withName(name);
        return this;
    }

    @Override
    public SpecifiedGUIBuilder<T> withName(String name) {
        super.withName(name);
        return this;
    }

    @Override
    public SpecifiedGUIBuilder<T> withItem(int slot, ItemStack item) {
        super.withItem(slot, item);
        return this;
    }

    public SpecifiedGUIBuilder<T> withInventoryHolder(InventoryHolder inventoryHolder) {
        super.withInventoryHolder(inventoryHolder);
        return this;
    }

    @Override
    public SpecifiedGUI<T> build() {
        Inventory inv = Bukkit.createInventory(inventoryHolder, size, baseName);
        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
            inv.setItem(entry.getKey(), entry.getValue());
        }
        return new SpecifiedGUI<>(inv, temporary, someStuff);
    }

}
