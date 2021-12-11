package me.lavamen.lavalib.commands;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import org.bukkit.Bukkit;

import java.lang.reflect.Method;
import java.util.Map;

public class CommandManager {

  //  private static Map<String, Map<String>> commands = new Object2ObjectArrayMap<>();

    public static void register(Class clazz) {
        Method[] methods = clazz.getMethods();


        for (Method method : methods) {
            if(method.isAnnotationPresent(BaseCommand.class)) {
                BaseCommand command = method.getAnnotation(BaseCommand.class);
    //            commands.put(command.mainCommand() + "." + command.name(), method);
            }
        }
    }

    public static void unregister(Class clazz) {
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if(method.isAnnotationPresent(BaseCommand.class)) {
                BaseCommand command = method.getAnnotation(BaseCommand.class);
     //           commands.remove(command.mainCommand() + "." + command.name());
            }
        }
    }
}
