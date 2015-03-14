package fr.haperycube.haperybasics;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("heal")){

			if(sender instanceof Player){

				Player p = (Player) sender;

				if(p.hasPermission("hc.heal")){

					if(args.length == 1){

						Player target = Bukkit.getPlayerExact(args[0]);

						if(target != null){

							double i = ((Damageable)target).getHealth();
							if(i < 20){

								p.sendMessage("§b" + target.getName() + " §9est désormais guéri");
								target.setHealth(20D);

							}else{
								p.sendMessage("§b" + target.getName() + " §3est déjà guéri");
							}

						}else{
							p.sendMessage("§3Joueur introuvable");

						}
					}

					if(args.length == 0){
						p.setHealth(20D);
					}

					if(args.length > 1){
						p.sendMessage("§3Utilisez /heal [nomdujoueur]");

					}

				}else{
					p.sendMessage("§3Vous n'avez pas la permission");
				}
			}

		}		return false;
	}



}
