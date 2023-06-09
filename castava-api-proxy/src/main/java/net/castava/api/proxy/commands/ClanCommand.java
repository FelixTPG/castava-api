package net.castava.api.proxy.commands;

import com.mongodb.client.model.Filters;
import net.castava.api.Utils;
import net.castava.api.clans.IClanService;
import net.castava.api.elements.Clan;
import net.castava.api.proxy.utils.ProxyUtils;
import net.castava.api.services.ServiceProvider;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import org.bson.conversions.Bson;

import java.util.*;

public class ClanCommand extends Command {

    private final IClanService clanService = ServiceProvider.getService(IClanService.class);

    String label;
    public ClanCommand(String label) {
        super(label);
        this.label = label;
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxyUtils.checkSender(commandSender, proxiedPlayer -> {
            if (!proxiedPlayer.hasPermission("core.command.clan")) {
                proxiedPlayer.sendMessage(Utils.PREFIX + "§cDazu hast du keine Rechte!");
                return;
            }
            if (args.length == 0) {
                commandSender.sendMessage(Utils.PREFIX + "§cBenutzung: §6" + label);
                return;
            }
            switch (args[0]) {
                case "create" -> {
                    Clan clan = new Clan(null, args[0], args[1], Collections.singletonList(proxiedPlayer.getUniqueId()),
                            List.of(), Collections.singletonList(proxiedPlayer.getUniqueId()), proxiedPlayer.getUniqueId());
                    clanService.registerClan(clan, aBoolean -> {
                        proxiedPlayer.sendMessage(Utils.PREFIX + "§r" + clan.getName() + "§a wurde erfolgreich erstellt!");
                    });
                }
                case "delete" -> {
                    if (!(args.length <= 2)) {
                        proxiedPlayer.sendMessage(Utils.sendUsage("/" + label + " <clan>"));
                        return;
                    }
                    Clan clan = clanService.getClan(Filters.eq("name", args[1]));
                    if (clan == null) {
                        proxiedPlayer.sendMessage(Utils.PREFIX + "§cDieser Clan existiert nicht!");
                        return;
                    }
                    if (!clan.getOwner().toString().equals(proxiedPlayer.getUniqueId().toString())) {
                        proxiedPlayer.sendMessage(Utils.PREFIX + "§cDazu musst du der Inhaber des Clans sein!");
                        return;
                    }
                    if (!args[2].toLowerCase().equals("confirm")) {
                        proxiedPlayer.sendMessage(Utils.PREFIX + "§cBestätige den Befehl mit §6/" + label + "§6 delete " + clan.getName() + "§6 confirm");
                        return;
                    }
                    clanService.deleteClan(Filters.eq("name", clan.getName()));
                }
            }
        });
    }


}
