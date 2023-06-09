package net.castava.api.spigot.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

@UtilityClass
public class SpigotUtils {

    public static void checkSender(CommandSender commandSender, Consumer<Player> executor) {
        if (commandSender instanceof Player) executor.accept((Player) commandSender);
    }

    public static String getPlayerCount(String format) {
        format = format.replace("%max%", String.valueOf(Bukkit.getMaxPlayers()));
        format = format.replace("%online%", String.valueOf(Bukkit.getOnlinePlayers()));
        return format;
    }


}
