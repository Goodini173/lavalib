package me.lavamen.lavalib.gui;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import java.util.Map;

public class GUIBuilder {

    protected Component baseName = Component.text("");
    protected int size;
    protected InventoryHolder inventoryHolder = null;
    protected Map<Integer, ItemStack> items = new Int2ObjectArrayMap<>();
    protected boolean temporary;

    /**
     * @param size how many lines will gui contain
     */
    public GUIBuilder withSize(int size) {
        this.size = size * 9;
        return this;
    }

    public GUIBuilder setTemp(boolean temporary) {
        this.temporary = temporary;
        return this;
    }

    public GUIBuilder withName(TextComponent name) {
        this.baseName = name;
        return this;
    }

    public GUIBuilder withName(String name) {
        withName(Component.text(name));
        return this;
    }

    public GUIBuilder withItem(int slot, ItemStack item) {
        if(slot > size - 1 || slot < 0) throw new IllegalArgumentException(slot + " slot is out of range");
        this.items.put(slot, item);
        return this;
    }

    public GUIBuilder withInventoryHolder(InventoryHolder inventoryHolder) {
        this.inventoryHolder = inventoryHolder;
        return this;
    }

    public GUI build() {
        Inventory inv = Bukkit.createInventory(inventoryHolder, size, baseName);
        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
            inv.setItem(entry.getKey(), entry.getValue());
        }
        return new GUI(inv, temporary);
    }


}
