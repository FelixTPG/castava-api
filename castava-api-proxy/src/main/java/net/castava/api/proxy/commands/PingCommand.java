package net.castava.api.proxy.commands;

import net.castava.api.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(Utils.PREFIX + "§cDazu musst du ein Spieler sein!");
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        player.sendMessage(Utils.PREFIX + "§7Dein Ping: §5" + player.getPing() + "ms");
    }

}
