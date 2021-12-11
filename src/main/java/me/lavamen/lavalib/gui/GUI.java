package me.lavamen.lavalib.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class GUI {

    @Nonnull
    protected final Inventory inventory;

    public GUI(@NotNull Inventory inventory) {
        this.inventory = inventory;
    }

    public void open(Player p) {
        p.openInventory(inventory);
    }

    public @NotNull Inventory getInventory() {
        return inventory;
    }

    /**
     * This method is called when player interacts with this gui.
     * Override it to add custom logic
     */
    public void interact(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
