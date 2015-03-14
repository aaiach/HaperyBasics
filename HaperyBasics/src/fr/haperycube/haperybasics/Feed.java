package fr.haperycube.haperybasics;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("feed")){

			if(sender instanceof Player){

				Player p = (Player) sender;

				if(p.hasPermission("hc.feed")){

					if(args.length == 1){

						Player target = Bukkit.getPlayerExact(args[0]);

						if(target != null){

							int i = target.getFoodLevel();

							if(i < 20){

								p.sendMessage("§b" + target.getName() + " §9est désormais nourrit");
								target.setFoodLevel(20);

							}else{
								p.sendMessage("§b" + target.getName() + " §3n'a pas faim");
							}

						}else{
							p.sendMessage("§3Joueur introuvable");
						}
					}
					
					if(args.length == 0){
						p.setFoodLevel(20);
					}

					if(args.length > 1){
						p.sendMessage("§3Utilisez /feed [nomdujoueur]");

					}

				}else{
					p.sendMessage("§3Vous n'avez pas la permission");
				}
			}

		}		return false;
	}



}
