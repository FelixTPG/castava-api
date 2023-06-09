package net.castava.api.proxy.utils;

import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.function.Consumer;

@UtilityClass
public class ProxyUtils {

    public static void checkSender (CommandSender commandSender, Consumer<ProxiedPlayer> executor) {
        if (commandSender instanceof ProxiedPlayer) executor.accept((ProxiedPlayer) commandSender);
    }

}
