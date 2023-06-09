package net.castava.api.proxy.commands;

import net.castava.api.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Arrays;

public class MessageCommand extends Command {

    String label;
    public MessageCommand(String label) {
        super(label);
        this.label = label;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(Utils.PREFIX + "§cDazu musst du ein Spieler sein!");
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (player.hasPermission("castava.command.message")) {
            player.sendMessage(Utils.PREFIX + "§cDazu hast du keine Rechte!");
            return;
        }
        if (args.length < 2) {
            player.sendMessage(Utils.PREFIX + Utils.sendUsage("/" + label + " <player> <message>"));
            return;
        }
        if (args.length == 2) {
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(Utils.PREFIX + "§6" + args[0] + "§c spielt aktuell nicht auf dem Server!");
                return;
            }
            String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            target.sendMessage("§8[§a" + player.getName() + "§8 -> §adu§8] §7" + message);
            player.sendMessage("§8[§adu§8 -> §a" + target.getName() + "§8] §7" + message);
        }
    }

}
