package me.lavamen.lavalib.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;

public class BaseCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0 || !CommandManager.containsCommand(command.getName())) return true;
        String cmdName = command.getName();
        SubCommand subCommand = CommandManager.getSubCommand(cmdName, args[0]);
        if(subCommand == null) {
            if(args[0].equals("help")) {
                for (String s : CommandManager.prepareHelp(cmdName)) {
                    sender.sendMessage(s);
                }
            }
        } else {
            subCommand.execute(sender, command, label, Arrays.copyOfRange(args, 1, args.length));
        }
        return true;
    }
}
