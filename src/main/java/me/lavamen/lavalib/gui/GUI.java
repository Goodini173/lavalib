package me.lavamen.lavalib.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class GUI {

    @Nonnull
    protected final Inventory inventory;
    protected final boolean temporary;

    public GUI(@NotNull Inventory inventory, boolean temporary) {
        this.inventory = inventory;
        this.temporary = temporary;
    }

    public void open(@NotNull Player p) {
        p.openInventory(inventory);
    }

    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public boolean isTemporary() {
        return temporary;
    }

    /**
     * This method is called when player interacts with this gui.
     * Override it to add custom logic
     */
    public void onInteraction(@NotNull InventoryClickEvent event) {
        event.setCancelled(true);
    }

    /**
     * This method is called when player closes with this gui.
     * Override it to add custom logic
     */
    public void onClosing(@NotNull InventoryCloseEvent event) {
    }
}
