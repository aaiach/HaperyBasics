package fr.haperycube.haperybasics;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Broadcast implements CommandExecutor{


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			Player p = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("broadcast")) {
				if (p.hasPermission("hc.broadcast")) {
					if (args.length >= 1) {

						StringBuilder message = new StringBuilder();
						for (int i = 0; i < args.length; i ++) {
							message.append(" ");
							message.append(args[i]);
						}

						Bukkit.broadcastMessage("§9[Annonce] :§b" + message);

					}
					else {
						p.sendMessage("§3Syntaxe: /broadcast [Message]");
					}
				}
				else {
					p.sendMessage("§3Vous n'avez pas la permission");
				}
			}
		}
		return false;
	}
}
