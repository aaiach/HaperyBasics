package fr.haperycube.haperybasics;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Vanish implements CommandExecutor, Listener{

	private static ArrayList<Player> vanish = new ArrayList<Player>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("vanish")){

			if(sender instanceof Player){
				Player p = (Player) sender;

				if(p.hasPermission("hc.vanish")){

					if(args.length == 0){

						if(vanish.contains(p)){

							vanish.remove(p);
							p.sendMessage("§9Vous n'êtes plus en vanish");

							for(Player pl: Bukkit.getOnlinePlayers()){
								pl.showPlayer(p);
							}



						}else{

							vanish.add(p);
							p.sendMessage("§9Vous êtes désormais en vanish");


							for(Player pl: Bukkit.getOnlinePlayers()){
								pl.hidePlayer(p);

							}



						}
					}else{
						p.sendMessage("§9Utilisez : §3/vanish");
					}
				}else{
					p.sendMessage("§3Vous n'avez pas la permission");
				}
			}
		}

		return false;
	}

	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){

		Player p = e.getPlayer();


		for (Player hidden : Bukkit.getServer().getOnlinePlayers()) {
			if (vanish.contains(hidden)){
				p.hidePlayer(hidden);
			}


			if (vanish.contains(p)) {


				for (Player pl : Bukkit.getServer().getOnlinePlayers())
					pl.hidePlayer(e.getPlayer());

				e.setJoinMessage("");
			}

		}

	}
}
