package fr.haperycube.haperybasics;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Helpop implements CommandExecutor{


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			Player p = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("helpop")) {
				if (p.hasPermission("hc.helpop")) {
					if (args.length >= 1) {

						StringBuilder message = new StringBuilder();
						for (int i = 0; i < args.length; i ++) {
							message.append(" ");
							message.append(args[i]);
						}

						for(Player pl: Bukkit.getOnlinePlayers()){
							if(pl.hasPermission("hc.helpop.receive")){

								Bukkit.broadcastMessage("§9[Staff Help] >§b" + message);
							}
						}

					}
					else {
						p.sendMessage("§3Syntaxe: /helpop [Message]");
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
