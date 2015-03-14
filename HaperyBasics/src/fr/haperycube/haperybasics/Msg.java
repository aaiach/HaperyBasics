package fr.haperycube.haperybasics;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Msg implements CommandExecutor{

	private static ArrayList<Player> socialspy = new ArrayList<Player>();

	//private static HashMap<Player, Player> reply = new HashMap<Player, Player>();



	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {

			Player p = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("msg")) {
				if (p.hasPermission("hc.msg")) {
					if (args.length >= 2) {

						Player target = Bukkit.getPlayerExact(args[0]);

						if(target != null){

							StringBuilder message = new StringBuilder();
							for (int i = 1; i < args.length; i ++) {
								message.append(" ");
								message.append(args[i]);
							}

							for(Player pl: Bukkit.getOnlinePlayers()){
								
								if(socialspy.contains(pl)){

									Bukkit.broadcastMessage("§9[SocialSpy] §b" + p.getName() +   ">§b " + target.getName() + " >§b " + message);
								}
							}

							target.sendMessage("§9" + p.getName() + " >§b" + message);
							p.sendMessage("§9" + target.getName() + " <§b" + message);

						}else {
							p.sendMessage("§3Joueur introuvable");
						}
					}
					else {
						p.sendMessage("§3Syntaxe: /msg [Nomdujoueur] [Message]");
					}
				}
				else {
					p.sendMessage("§3Vous n'avez pas la permission");
				}
			}

			if(cmd.getName().equalsIgnoreCase("socialspy")){
				if(p.hasPermission("hc.socialspy")){
					if(socialspy.contains(p)){
						
						socialspy.remove(p);
						p.sendMessage("§9Socialspy desactivé");
						
					}else{
						
						socialspy.add(p);
						p.sendMessage("§9Socialspy activé");
						
					}
				}
			}
		}
		return false;
	}


}
