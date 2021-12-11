package me.lavamen.lavalib.gui;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import me.lavamen.lavalib.Lavalib;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class GUIManager implements Listener {

    private final Map<Inventory, GUI> guiMap = new Object2ObjectArrayMap<>();
    private final Plugin plugin;

    public GUIManager(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void registerGui(GUI gui) {
        guiMap.put(gui.getInventory(), gui);
    }

    public void unregisterGui(GUI gui) {
        guiMap.remove(gui.getInventory());
    }

    @EventHandler
    public void inventoryInteractEvent(InventoryClickEvent e) {
        if(guiMap.containsKey(e.getInventory())) guiMap.get(e.getInventory()).interact(e);
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
