package fr.haperycube.haperybasics;

import java.util.HashMap;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Tpall implements CommandExecutor{
	
	private static HashMap<String, Location> loctp = new HashMap<String, Location>();


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("tpall")){

			if(sender instanceof Player){

				Player p = (Player) sender;
				Location loc1 = p.getLocation();

				if(args.length == 0){

					if(p.hasPermission("hc.tpall")){
						Json(p);
						loctp.put("tp", loc1);


					}else{
						p.sendMessage("§3Vous n'avez pas la permission");
					}

				}else{

					p.sendMessage("§9Utilisez: /tpall");
				}
			}
		}

		if(cmd.getName().equalsIgnoreCase("▦")){

			Player permission = (Player) sender;

			if(sender instanceof Player){

				if(permission.hasPermission("hc.tpall")){

					for(Player p: Bukkit.getOnlinePlayers()){
						Location loc2 = loctp.get("tp");
						p.teleport(loc2);

					}
				}

			}
		}

		return false;
	}

	public void Json(Player player) {
		String message = "Voulez vous vraiment teleporter chaque joueur à vous? Si oui cliquez ";
		IChatBaseComponent comp = ChatSerializer.a("{\"text\":\" " + message + " \",\"extra\":[{\"text\":\"§bIci\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§bTP tout le monde à vous\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/▦\"}}]}");
		PacketPlayOutChat packet = new PacketPlayOutChat(comp, true);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}


}
