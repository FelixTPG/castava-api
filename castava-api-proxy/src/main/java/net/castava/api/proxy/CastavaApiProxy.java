package net.castava.api.proxy;

import net.castava.api.clans.ClanServiceImpl;
import net.castava.api.clans.IClanService;
import net.castava.api.database.DatabaseManager;
import net.castava.api.database.IDatabaseManager;
import net.castava.api.discord.WebHookService;
import net.castava.api.discord.WebHookServiceImpl;
import net.castava.api.players.IPlayerService;
import net.castava.api.players.PlayerServiceImpl;
import net.castava.api.proxy.commands.MessageCommand;
import net.castava.api.proxy.commands.PingCommand;
import net.castava.api.services.ServiceProvider;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class CastavaApiProxy extends Plugin {

    @Override
    public void onLoad() {
        ServiceProvider.registerService(WebHookService.class, new WebHookServiceImpl());
        ServiceProvider.registerService(IDatabaseManager.class, new DatabaseManager().buildConnection("mongodb://admin:pExyxjxCD9WIyYQvG9h5@185.236.11.148:27017/?authSource=admin"));
        ServiceProvider.registerService(IPlayerService.class, new PlayerServiceImpl());
        ServiceProvider.registerService(IClanService.class, new ClanServiceImpl());

        //--------------->
        //AB HIER ANDERE SERVICES REGISTIEREN
    }

    @Override
    public void onEnable() {
        getLogger().info("Plugin has been enabled!");
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new PingCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new MessageCommand("msg"));
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
