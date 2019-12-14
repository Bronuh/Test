
package ru.bronuh.test;


import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	
	public int PrintCount = 10;
	public static PluginLogger Log;
	public static Server Core;
	public EventListener Hooks = new EventListener();
	public static boolean _CHEATMODE = false;
	public static String MSG = "Изменены/добавлены ники. Теперь куриные яйца могут заспавнить рандомного моба";
	public static Tips TIPS = new Tips();
	public static boolean _DEBUG = false;
	public static FileConfiguration CFG;
	public static Main This;
	
	@Override
	public void onLoad() {
		Core = getServer();
		debug("Loading...");
		this.saveDefaultConfig();
		Log = (PluginLogger) getLogger();
		
		CFG = getConfig();
		This = this;
		//this.
		for(int i = 1;i<=PrintCount;i++) {
			getLogger().info(ChatColor.BLUE+"LOUDID!!!");
			
		}
		Core.broadcastMessage(ChatColor.GREEN+"Генератор неадеквата загружен");
		debug("Starting tips");
		TIPS.start();
		///
		
		this.reloadConfig();
		CFG = getConfig();
		
		debug("Loading config");
		setupInt("TipsInterval",120);
		setupDouble("EggChance",0.05);
		setupDouble("FishingChance",0.1);
		setupInt("BowSpeedUp",10);
		setupInt("BowExplosion",5);
		setupInt("SpeedUpLevel",30);
		setupInt("ExplosionLevel",40);
		setupBoolean("VasterFish",true);
		setupBoolean("HookRod",true);
		setupBoolean("CheatMode",false);
		setupString("MoneyName","ХЗ");
		this.saveConfig();
	}
	
	@Override
	public void onEnable() {
		
		Core = getServer();
		Log = (PluginLogger) getLogger();
		for(int i = 1;i<=PrintCount;i++) {
			getLogger().info(ChatColor.GREEN+"Hello!!!");
			
		}
		Core.broadcastMessage(ChatColor.GREEN+"Генератор неадеквата активен");
		EventListener.Core = Core;
		EventListener.Log = Log;
		
		debug("Initializing hooks");
		
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getCommand("bronuhsshit").setExecutor(new ChatListener());
		getCommand("stats").setExecutor(new ChatListener());
		getCommand("upgrade").setExecutor(new ChatListener());
		getCommand("lootbox").setExecutor(new ChatListener());
		getCommand("ender").setExecutor(new ChatListener());
		getCommand("fix").setExecutor(new ChatListener());
		getCommand("gm").setExecutor(new ChatListener());
		getCommand("name").setExecutor(new ChatListener());
		getCommand("othername").setExecutor(new ChatListener());
		getCommand("self").setExecutor(new ChatListener());
		getCommand("format").setExecutor(new ChatFormat());
		getCommand("debug").setExecutor(new ChatListener());
		getCommand("config").setExecutor(new ChatConfig());
		try {
		    Field f = Enchantment.class.getDeclaredField("acceptingNew");
		    f.setAccessible(true);
		    f.set(null, true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		
		
		//EnchantSniper Sniper = new EnchantSniper("sniper");
		//Enchantment.registerEnchantment(Sniper);
	}
	
	@Override
	public void onDisable() {
		for(int i = 1;i<=PrintCount;i++) {
			getLogger().info(ChatColor.RED+"Bye!!!");
			Core = getServer();
			for(Account a:Account.Accounts) {
				a.save();
			}
			TIPS.Active = false;
		}
		//Plugin.getDataFolder();
	}
	
	
	public FileConfiguration getCfg() {
		return this.getConfig();
	}
	
	public static void debug(String text) {
		if(_DEBUG) {
			Core.broadcastMessage(ChatColor.GRAY+"[DEBUG] "+text);
		}
	}
	
	public static void info(String text) {
		Core.broadcastMessage(ChatColor.YELLOW+"[INFO] "+text);
	}
	
	public static void setupInt(String Name, int DefaultValue) {
		if(CFG.contains(Name)) {
			debug("Config contains '"+Name+"'");
		}else {
			debug("Config does not contain '"+Name+"'");
			CFG.set(Name, DefaultValue);
			Main.This.saveConfig();
		}
	}
	
	public static void setupDouble(String Name, double DefaultValue) {
		if(CFG.contains(Name)) {
			debug("Config contains '"+Name+"'");
		}else {
			debug("Config does not contain '"+Name+"'");
			CFG.set(Name, DefaultValue);
			Main.This.saveConfig();
		}
	}
	
	public static void setupString(String Name, String DefaultValue) {
		if(CFG.contains(Name)) {
			debug("Config contains '"+Name+"'");
		}else {
			debug("Config does not contain '"+Name+"'");
			CFG.set(Name, DefaultValue);
			Main.This.saveConfig();
		}
	}
	
	public static void setupBoolean(String Name, boolean DefaultValue) {
		if(CFG.contains(Name)) {
			debug("Config contains '"+Name+"'");
		}else {
			debug("Config does not contain '"+Name+"'");
			CFG.set(Name, DefaultValue);
			Main.This.saveConfig();
		}
	}
	
	
}