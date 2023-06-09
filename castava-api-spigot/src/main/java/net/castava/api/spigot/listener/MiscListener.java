package net.castava.api.spigot.listener;

import net.castava.api.Utils;
import net.castava.api.spigot.CastavaApiSpigot;
import net.castava.api.spigot.utils.SpigotUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MiscListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (CastavaApiSpigot.getInstance().getConfig().getBoolean("join")) {
            event.setJoinMessage("§8[§a+§8] §r" + event.getPlayer().getDisplayName());
        } else
            event.setJoinMessage(null);
        if (CastavaApiSpigot.getInstance().getConfig().getBoolean("admin-motd")) {
            event.getPlayer().sendMessage(Utils.PREFIX + "Aktuell online: §r" + SpigotUtils.getPlayerCount("§d%o%§8/§7%m%"));
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        if (CastavaApiSpigot.getInstance().getConfig().getBoolean("quit")) {
            event.setQuitMessage("§8[§c-§8] §r" + event.getPlayer().getDisplayName());
        } else
            event.setQuitMessage(null);
    }

}