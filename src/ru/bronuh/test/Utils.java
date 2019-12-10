package ru.bronuh.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Utils {
	public static double round(double value, int places) {
	    double _val = value*Math.pow(10, places);
	    return Math.round(_val)/Math.pow(10, places);
	}
	
	public static float round(float value, int places) {
	    double _val = value*Math.pow(10, places);
	    return (float) (Math.round(_val)/Math.pow(10, places));
	}
	
	public static FileConfiguration getCfg() {
		return Bukkit.getPluginManager().getPlugin("BronuhsShit").getConfig();
	}
	
	public static void debug(String text) {
		if(Main._DEBUG) {
			Main.Core.broadcastMessage(ChatColor.GRAY+"[DEBUG] "+text);
		}
	}
	
	public static void info(String text) {
		Main.Core.broadcastMessage(ChatColor.YELLOW+"[INFO] "+text);
	}
}
