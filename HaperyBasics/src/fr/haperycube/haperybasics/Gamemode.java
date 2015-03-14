package fr.haperycube.haperybasics;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

	private static HashMap<String, GameMode> gm = new HashMap<String, GameMode>();

	public static void fillGameModeHashMap() {
		gm.put("0", GameMode.SURVIVAL);
		gm.put("1", GameMode.CREATIVE);
		gm.put("2", GameMode.ADVENTURE);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("gamemode")) {
				
				if(args.length != 0 && args.length < 3){

					if (gm.containsKey(args[0])) {
						if (args.length == 2) {
							if (p.hasPermission("hc.gamemode.others")) {
								Player target = Bukkit.getPlayer(args[1]);
								if (target != null) {
									target.setGameMode(gm.get(args[0]));
									target.sendMessage("§9Gamemode :§3 " + gm.get(args[0]));
								}else{
									p.sendMessage("§3Joueur introuvable");
								}
							}else{
								p.sendMessage("§3Vous n'avez pas la permission");
							}

						} if (args.length == 1) {
							if (p.hasPermission("hc.gamemode.self")){
								p.setGameMode(gm.get(args[0]));
								p.sendMessage("§9Gamemode :§3 " + gm.get(args[0]));

							}else{
								p.sendMessage("§3Vous n'avez pas la permission");
							}
						} 

					} else{
						p.sendMessage("§3Utilisez /gamemode [0, 1, 2] (nomdujoueur)");
					}
					
				} else{
					p.sendMessage("§3Utilisez /gamemode [0, 1, 2] (nomdujoueur)");
				}

			}

		}
		return false;
	}
}
