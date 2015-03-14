package fr.haperycube.haperybasics;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Haperybasics extends JavaPlugin implements Listener{
	
	public static int tpDelay = 5;
	
	public static Plugin instance;
	
	public void onEnable(){
		
		instance = this;

		registerCommands();
		registerEvents();
		Gamemode.fillGameModeHashMap();
	}
	
	private void registerCommands() {
		
		
		getCommand("exp").setExecutor(new Exp());
		getCommand("feed").setExecutor(new Feed());
		getCommand("heal").setExecutor(new Heal());
		getCommand("fly").setExecutor(new Fly());
		getCommand("day").setExecutor(new Day());
		getCommand("night").setExecutor(new Night());
		getCommand("workbench").setExecutor(new Workbench());
		getCommand("invsee").setExecutor(new Invsee());
		getCommand("enderchest").setExecutor(new Enderchest());
		getCommand("vanish").setExecutor(new Vanish());
		getCommand("helpop").setExecutor(new Helpop());
		getCommand("broadcast").setExecutor(new Broadcast());
		getCommand("gamemode").setExecutor(new Gamemode());
		getCommand("clearinventory").setExecutor(new Clearinventory());
		getCommand("god").setExecutor(new God());
		getCommand("msg").setExecutor(new Msg());
		getCommand("socialspy").setExecutor(new Msg());
		getCommand("tpa").setExecutor(new Tpa());
		getCommand("tpaccept").setExecutor(new Tpa());
		getCommand("tpall").setExecutor(new Tpall());
		getCommand("▦").setExecutor(new Tpall());
		getCommand("tphere").setExecutor(new Tp());
		getCommand("tp").setExecutor(new Tp());


	}	
	
	
	private void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Invsee(), this);
		pm.registerEvents(new Enderchest(), this);
		pm.registerEvents(new Vanish(), this);
		pm.registerEvents(new God(), this);
		pm.registerEvents(new Tpa(), this);


		}
}
