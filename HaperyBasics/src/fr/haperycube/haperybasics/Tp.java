package fr.haperycube.haperybasics;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tp implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("tphere")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(args.length == 1){
					Player target = Bukkit.getPlayerExact(args[0]);
					if(p.hasPermission("hc.tphere")){
						if(target != null){
							Location loc = p.getLocation();
							target.teleport(loc);
						}else{
							p.sendMessage("§3Joueur introuvable");
						}	
					}else{
						p.sendMessage("§3Vous n'avez pas la permission");
					}

				}


				if(args.length > 1){
					if(p.hasPermission("hc.tphere")){
						p.sendMessage("§3Utilisez /tphere [nomdujoueur]");
					}else{
						p.sendMessage("§3Vous n'avez pas la permission");
					} 

				}

				if(args.length == 0){
					if(p.hasPermission("hc.tphere")){
						p.sendMessage("§3Utilisez /tphere [nomdujoueur]");
					}else{
						p.sendMessage("§3Vous n'avez pas la permission");
					} 
				}
			}


		}

		if(cmd.getName().equalsIgnoreCase("tp")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(args.length == 1){
					Player target = Bukkit.getPlayerExact(args[0]);
					if(p.hasPermission("hc.tp")){
						if(target != null){
							Location loc = target.getLocation();
							p.teleport(loc);

						}else{
							p.sendMessage("§3Joueur introuvable");
						}	
					}else{
						p.sendMessage("§3Vous n'avez pas la permission");
					}

				}


				if(args.length > 1){
					if(p.hasPermission("hc.tp")){
						p.sendMessage("§3Utilisez /tphere [nomdujoueur]");
					}else{
						p.sendMessage("§3Vous n'avez pas la permission");
					} 

				}
				
				if(args.length == 0){
					if(p.hasPermission("hc.tphere")){
						p.sendMessage("§3Utilisez /tp [nomdujoueur]");
					}else{
						p.sendMessage("§3Vous n'avez pas la permission");
					} 
				}
			}


		}


		return false;
	}

}
