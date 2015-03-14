package fr.haperycube.haperybasics;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class God implements CommandExecutor, Listener{


	private static ArrayList<String> godmode = new ArrayList<String>();


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("god")){
			if(sender instanceof Player){

				Player p = (Player) sender;

				if(p.hasPermission("hc.god")){

					if(godmode.contains(p.getName())){
						godmode.remove(p.getName());
						p.sendMessage("§9Vous n'êtes plus en GodMode");
					}
					
					else if(!godmode.contains(p.getName())){
						godmode.add(p.getName());
						p.sendMessage("§9Vous êtes désormais en GodMode");


					}

				}else{
					p.sendMessage("§3Vous n'avez pas la permission");
				}

			}

		}


		return false;
	}

	@EventHandler
	public void onD(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(godmode.contains(p.getName())){
				e.setCancelled(true);
				e.setDamage(0.0);
			}else{

			}

		}

	}
	
	public void onQ(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if(godmode.contains(p.getName())){
			godmode.remove(p.getName());
		}
	}

}
