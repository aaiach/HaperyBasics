package fr.haperycube.haperybasics;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Workbench implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("workbench")){

			if(sender instanceof Player){

				Player p = (Player) sender;

				if(p.hasPermission("hc.workbench")){

					if(args.length == 0){
						
						p.openWorkbench(p.getLocation(), true);

					}else{
						p.sendMessage("§3Utilisez /workbench");
					}
					
				}else{
					p.sendMessage("§3Vous n'avez pas la permission");
					
				}
			}
		}


		return false;
	}

}
