package fr.haperycube.haperybasics;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Exp implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("exp")){
			if(sender instanceof Player){

				Player p = (Player) sender;

				if(args.length == 1){

					if(p.hasPermission("hc.exp.get")){

						Player target = Bukkit.getPlayerExact(args[0]);

						if(target != null){
							int i = target.getLevel();
							p.sendMessage("§b" + target.getName() + " §9a§b " + i + " §9niveaux d'xp");

						}else{
							p.sendMessage("§3Joueur introuvable");
						}

					}else{
						p.sendMessage("§3Vous n'avez pas la permission");
					}
				}

				if(args.length == 3){

					if(args[0].equalsIgnoreCase("set")){

						if(p.hasPermission("hc.exp.set")){


							Player target2 = Bukkit.getPlayerExact(args[1]);
							String i2 = args[2];

							if(isInteger(i2)){
								int a = Integer.parseInt(i2);
								if(a > 0 && a < 31){
									if(target2 != null){
										target2.setLevel(a);;
										p.sendMessage("§b" + target2.getName() + " §9a désormais §b " + a + " §9niveaux");
									}else{
										p.sendMessage("§3Joueur introuvable");
									}

								}else{		
									p.sendMessage("§3Montant d'exp invalide");
								}

							}else{
								p.sendMessage("§3Montant d'exp invalide");
							}
						}

					}else{ 		
						p.sendMessage("§3Utilisez /exp set [nomdujoueur] [montant d'exp]");
					}
				}

				if(args.length == 0 || args.length > 3 || args.length == 2){

					if(p.hasPermission("hc.exp.get") || p.hasPermission("hc.exp.set")){
						p.sendMessage("§3Utilisez /exp set [nomdujoueur] [montant d'exp]");

					}else{
						p.sendMessage("§3Vous n'avez pas la permission");
					} 




				}
			}
		}

		return false;
	}






	public static boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e){ 
			return false; 
		}
		return true;
	}

}
