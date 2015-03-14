package fr.haperycube.haperybasics;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;


public class Enderchest implements CommandExecutor, Listener {

	private HashMap<UUID, Inventory> ec = new HashMap<UUID, Inventory>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("enderchest")) {
				if (p.hasPermission("hc.enderchest.view")) {
					if (args.length == 1) {
						Player target = Bukkit.getPlayer(args[0]);
						if (target != null) {
							p.openInventory(target.getEnderChest());
							ec.put(p.getUniqueId(), target.getEnderChest());
							if (target.hasPermission("hc.enderchest.notify")) {
								target.sendMessage("§b" + p.getName() + "§9 est en train de regarder votre enderchest.");
							}
						} else {
							p.sendMessage("§3Ce joueur n'est pas connecté");
						}
					}if(args.length == 0){
						p.openInventory(p.getEnderChest());
						ec.put(p.getUniqueId(), p.getEnderChest());
					} 
				}

				if(args.length > 1){
					p.sendMessage("§3Utilisez /enderchest [nomdujoueur]");

				}
			} else {
				p.sendMessage("§3Vous n'avez pas la permission");
			}
		}

		return false;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if (e.getInventory() == ec.get(p.getUniqueId())) {
				if (!p.hasPermission("hc.enderchest.modify")) {
					e.setCancelled(true);
					p.sendMessage("§3Vous n'avez pas la permission de modifier les enderchests");
				}
			}
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (e.getPlayer() instanceof Player) {
			Player p = (Player) e.getPlayer();
			if (ec.containsKey(p.getUniqueId())) {
				ec.remove(p.getUniqueId());
			}
		}
	}
}