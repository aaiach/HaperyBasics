package fr.haperycube.haperybasics;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Clearinventory implements CommandExecutor{


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("clearInventory")){
			if(sender instanceof Player){
				Player p = (Player) sender;

				if(p.hasPermission("hc.clearinventory")){
					if(args.length == 0){
						p.getInventory().clear();;
						p.sendMessage("§9Votre inventaire a été vidé");

					}else if(args.length > 1){
						p.sendMessage("§3Utilisez /clearinventory (nomdujoueur)");
					}
					if(args.length == 1){
						Player target = Bukkit.getPlayerExact(args[0]);
						if(p.hasPermission("hc.clearinventory.others")){

							if(target != null){

								target.getInventory().clear();
								target.sendMessage("§b"+ p.getName() +" §9a vidé votre inventaire");

							}else{
								p.sendMessage("§3Joueur introuvable");
							}
						}else{
							p.sendMessage("§3Vous n'avez pas la permission");
						}
					}
				}else{
					p.sendMessage("§3Vous n'avez pas la permission");
				}
			}
		}

		return false;
	}

}
