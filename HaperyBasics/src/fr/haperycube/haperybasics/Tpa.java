package fr.haperycube.haperybasics;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class Tpa implements CommandExecutor, Listener {

	private static HashMap<Player, Player> tprequest = new HashMap<Player, Player>();
	private static HashMap<Player, Double> tpX = new HashMap<Player, Double>();
	private static HashMap<Player, Double> tpZ = new HashMap<Player, Double>();
	private static ArrayList<Player> teleporting = new ArrayList<Player>();
	int requestLast = 15;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			final Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("tpa")) {
				if (p.hasPermission("hc.tpa")) {
					if (args.length == 1) {
						final Player target = Bukkit.getPlayer(args[0]);
						if (target != null) {
							if (!tprequest.containsKey(target)) {
								tprequest.put(target, p);
								p.sendMessage("§9Requête de téléportation envoyée à §b" + target.getName());
								target.sendMessage("§9Requête de téléportation reçue de §b" + p.getName() + "§9, faites §b/tpaccept "
										+ "§9pour accepter la requête. La requête expire dans §b" + requestLast + " secondes§9.");
								Bukkit.getScheduler().scheduleSyncDelayedTask(Haperybasics.instance, new Runnable() {
									public void run() {
										if (!teleporting.contains(p)) {
											tprequest.remove(target);
										}
									}
								}, requestLast * 20);
							} else {
								p.sendMessage("§3Vous avez déjà encoyé une requête à ce joueur");
							}
						}
					} else {
						p.sendMessage("§3Utilisez : /tpa [Joueur]");
					}
				} else {
					p.sendMessage("§3Vous n'avez aucune requête en attente");
				}
			}
		}
			if (sender instanceof Player) {
				if (cmd.getName().equalsIgnoreCase("tpaccept")) {
					final Player p = (Player) sender;
					if (p.hasPermission("hc.tpa")) {
						if (tprequest.containsKey(p)) {
							final Player pl = tprequest.get(p);
							teleporting.add(pl);
							tpX.put(pl, pl.getLocation().getX());
							tpZ.put(pl, pl.getLocation().getZ());
							p.sendMessage("§9vous avez accepté la requête de téléportation de §b" + pl.getName());
							pl.sendMessage("§b" + p.getName() + " §9a accepté votre requête de téléportation, ne bougez pas");
							Bukkit.getScheduler().scheduleSyncDelayedTask(Haperybasics.instance, new Runnable() {
								public void run() {
									if (teleporting.contains(pl)) {
										pl.teleport(p);
										tprequest.remove(pl);
										tpX.remove(pl);
										tpZ.remove(pl);
										teleporting.remove(pl);
										pl.sendMessage("§9Téléportation en cours");
									}
								}
							}, Haperybasics.tpDelay * 20);
						} else {
							p.sendMessage("§3Vous n'avez aucune requête en attente");
						}
					} else {
						p.sendMessage("§3Vous n'avez pas la permission");
					}
				}
		}
		return false;
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		/*if (teleporting.contains(p)) {
			teleporting.remove(p);
			p.sendMessage("§3Vous avez bougé, téléportation annulée !");
		}*/
		if (tpX.containsKey(p) && tpZ.containsKey(p)) {
			p.sendMessage("move");
			if ((tpX.get(p.getName()) == p.getLocation().getX()) && (tpZ.get(p.getName()) == p.getLocation().getZ())) {
				p.sendMessage("ok");
			} else {
				teleporting.remove(p);
				p.sendMessage("§3Vous avez bougé, téléportation annulée !");
			}
		}
	}
}