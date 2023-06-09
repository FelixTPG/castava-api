package net.castava.api.spigot;

import lombok.Getter;
import net.castava.api.clans.ClanServiceImpl;
import net.castava.api.clans.IClanService;
import net.castava.api.database.DatabaseManager;
import net.castava.api.database.IDatabaseManager;
import net.castava.api.discord.WebHookService;
import net.castava.api.discord.WebHookServiceImpl;
import net.castava.api.players.IPlayerService;
import net.castava.api.players.PlayerServiceImpl;
import net.castava.api.services.ServiceProvider;
import net.castava.api.spigot.listener.MiscListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CastavaApiSpigot extends JavaPlugin {

    @Getter
    private static CastavaApiSpigot instance;

    @Override
    public void onLoad() {
        instance = this;
        ServiceProvider.registerService(WebHookService.class, new WebHookServiceImpl());
        ServiceProvider.registerService(IDatabaseManager.class, new DatabaseManager().buildConnection("mongodb://admin:pExyxjxCD9WIyYQvG9h5@185.236.11.148:27017/?authSource=admin"));
        ServiceProvider.registerService(IClanService.class, new ClanServiceImpl());
        ServiceProvider.registerService(IPlayerService.class, new PlayerServiceImpl());

        //--------------->
        //AB HIER ANDERE SERVICES REGISTIEREN
    }

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new MiscListener(), this);
    }

    @Override
    public void onDisable() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                ServiceProvider.getService(IDatabaseManager.class).closeConnection();
            }
        }));
    }


}
