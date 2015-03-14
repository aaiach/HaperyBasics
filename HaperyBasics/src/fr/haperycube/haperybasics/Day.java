package fr.haperycube.haperybasics;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Day implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("day")){

			if(sender instanceof Player){


				if(args.length >= 0){

					Player p = (Player) sender;
					World world = p.getWorld();

					if(p.hasPermission("hc.day")){
						world.setTime(0);

					}else{
						p.sendMessage("§3Vous n'avez pas la permission");
					}
				}
			}
		}

		return false;
	}

}
