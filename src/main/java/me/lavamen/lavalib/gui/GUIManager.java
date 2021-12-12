package me.lavamen.lavalib.gui;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class GUIManager implements Listener {

    private final Map<Inventory, GUI> guiMap = new Object2ObjectArrayMap<>();
    private final Plugin plugin;

    public GUIManager(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void registerGui(@NotNull GUI gui) {
        guiMap.put(gui.getInventory(), gui);
    }

    public void unregisterGui(@NotNull GUI gui) {
        guiMap.remove(gui.getInventory());
    }

    @EventHandler
    public void inventoryInteractEvent(@NotNull InventoryClickEvent e) {
        if (guiMap.containsKey(e.getInventory())) guiMap.get(e.getInventory()).onInteraction(e);
    }

    @EventHandler
    public void inventoryCloseEvent(@NotNull InventoryCloseEvent e) {
        if (guiMap.containsKey(e.getInventory())) {
            GUI gui = guiMap.get(e.getInventory());
            gui.onClosing(e);
            if (gui.isTemporary()) unregisterGui(gui);
        }
    }

    public @NotNull Plugin getPlugin() {
        return plugin;
    }
}
