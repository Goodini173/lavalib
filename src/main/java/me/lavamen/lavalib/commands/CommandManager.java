package me.lavamen.lavalib.commands;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class CommandManager {

    private static Map<String, Map<String, SubCommand>> commands = new Object2ObjectArrayMap<>();

    public static void register(@NotNull AdvancedCommand advancedCommand) {
        Class clazz = advancedCommand.getClass();
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if(method.isAnnotationPresent(BaseCommand.class)) {
                BaseCommand command = method.getAnnotation(BaseCommand.class);
                Map<String, SubCommand> map = commands.get(command.mainCommand());
                if(map == null) {
                    map = new Object2ObjectArrayMap<>();
                    commands.put(command.mainCommand(), map);
                }
                map.put(command.name(), new SubCommand(method, advancedCommand, command));
            }
        }
    }

    public static void unregister(@NotNull String cmd) {
        commands.remove(cmd);
    }

    public static boolean containsCommand(String command) {
        return commands.containsKey(command);
    }

    public static @Nullable Map<String, SubCommand> getSubCommands(@NotNull String mainCommand) {
        return commands.get(mainCommand);
    }

    public static @Nullable SubCommand getSubCommand(@NotNull String mainCommand, @NotNull String subCommand) {
        if(!commands.containsKey(mainCommand)) return null;
        return commands.get(mainCommand).get(subCommand);
    }

    public static @NotNull List<String> prepareHelp(@NotNull String cmd) {
        List<String> result = new ObjectArrayList<>();
        if(commands.containsKey(cmd)) {
            for (SubCommand subCommand : getSubCommands(cmd).values()) {
                if(!subCommand.isHidden()) result.add(subCommand.help());
            }
        }
        return result;
    }
}
