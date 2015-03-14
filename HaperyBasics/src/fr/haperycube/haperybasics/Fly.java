package fr.haperycube.haperybasics;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("fly")){

			if(sender instanceof Player){

				Player p = (Player) sender;

				if(args.length == 0){
					if(p.hasPermission("hc.fly.self")){
						if(p.isFlying()){
							p.setAllowFlight(false);
							p.setFlying(false);
							p.sendMessage("§9Vous ne pouvez plus voler");


						}else{
							p.setAllowFlight(true);
							p.setFlying(true);
							p.sendMessage("§9Vous pouvez voler");


						}
					}else{
						p.sendMessage("§3Vous n'avez pas la permission");
					}
				}

				if(args.length == 1){

					Player target = Bukkit.getPlayerExact(args[0]);

					if(p.hasPermission("hc.fly.others")){

						if(target != null){

							if(target.isFlying()){
								target.setAllowFlight(false);
								target.setFlying(false);
								target.sendMessage("§9Vous ne pouvez plus voler");
								p.sendMessage("§b" + target.getName() + " §9ne peut plus voler");


							}else{
								target.setAllowFlight(true);
								target.setFlying(true);
								target.sendMessage("§9Vous pouvez voler");
								p.sendMessage("§b" + target.getName() + " §9peut désormais voler");


							}

						}else{
							p.sendMessage("§3Joueur introuvable");
						}	

					}else{
						p.sendMessage("§3Vous n'avez pas la permission");
					}

				}

				if(args.length > 1){

					if(p.hasPermission("hc.fly.self") || p.hasPermission("hc.fly.others")){
						p.sendMessage("§3Utilisez /fly [nomdujoueur]");

					}else{
						p.sendMessage("§3Vous n'avez pas la permission");
					} 

				}
			}
		}


		return false;
	}

}
