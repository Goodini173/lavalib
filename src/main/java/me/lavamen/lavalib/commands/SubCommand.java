package me.lavamen.lavalib.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SubCommand {

    private final Method method;
    private final AdvancedCommand executor;
    private final BaseCommand baseCommand;

    public SubCommand(Method method, AdvancedCommand executor, BaseCommand baseCommand) {
        this.method = method;
        this.executor = executor;
        this.baseCommand = baseCommand;
    }

    public void execute(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length < baseCommand.min() || (baseCommand.max() > 0 && args.length > baseCommand.max())) return;
        if((sender instanceof Player && !baseCommand.player()) || (sender instanceof ConsoleCommandSender && !baseCommand.console())) return;
        try {
            method.invoke(executor, sender, command, label, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public boolean isHidden() {
        return baseCommand.hidden();
    }

    public String help() {
        return baseCommand.mainCommand() + " " + baseCommand.usage() + " - " + baseCommand.desc();
    }
}
