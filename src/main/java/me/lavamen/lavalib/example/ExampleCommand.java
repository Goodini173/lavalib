package me.lavamen.lavalib.example;

import me.lavamen.lavalib.commands.AdvancedCommand;
import me.lavamen.lavalib.commands.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ExampleCommand implements AdvancedCommand {

    @BaseCommand(
            mainCommand = "example",
            name = "test1",
            plugin = "LavaLib",
            desc = "desc"
    )
    public void command1(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("JEBAITED");
    }

    @BaseCommand(
            mainCommand = "example",
            name = "test2",
            plugin = "LavaLib",
            desc = "desc"
    )
    public void command2(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("JEBAITED2");
    }

    @BaseCommand(
            mainCommand = "example",
            name = "test3",
            plugin = "LavaLib",
            desc = "desc"
    )
    public void command3(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("JEBAITED3");
    }

}
