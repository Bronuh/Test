package ru.bronuh.test;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import static ru.bronuh.test.Utils.debug;
import static ru.bronuh.test.Utils.info;

import java.util.Map;

public class ChatConfig implements CommandExecutor {
	public String prefix = ChatColor.GOLD + "CONFIG" + ChatColor.GRAY.toString() + " | ";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		Main.debug("Executed ChatConfig");
		Player player = (Player) sender;
		
		
		if (command.getName().equalsIgnoreCase("config")) {
			Main.debug("   Used command /config");
			
			Configuration CFG = Main.This.getConfig();
			String Action = args[0], Name = "", Value = "";
			try {
				Value = args[2];
				
			}catch(Exception e) {
				Value = "";
			}
			
			try {
				Name = args[1];
				
			}catch(Exception e) {
				Name = "";
			}
			
			if(args.length!=0) {
				
				if(Action.equals("values")) {
					//Map<String,Object> KV = CFG.getValues(true);
					for(Map.Entry<String, Object> KV : CFG.getValues(true).entrySet()) {
						//info(KV.getKey()+" = "+KV.getValue().toString());
						player.sendMessage(prefix + ChatColor.YELLOW + KV.getKey()+" = "+KV.getValue().toString());
					}
				}
				
				if(Action.equals("setint")) {
					Main.debug("      Action is setint");
					if(CFG.contains(Name)) {
						debug("         Config containing "+Name);
						//CFG.
						CFG.set(Name, Integer.parseInt(Value));
					}else {
						debug("         Config doesn't containing "+Name);
						CFG.set(Name, Integer.parseInt(Value));
					}
					Main.This.saveConfig();
					return true;
					//Main.This.saveDefaultConfig();
				}
				
				if(Action.equals("getint")) {
					Main.debug("      Action is getint");
					if(CFG.contains(Name)) {
						debug("         Config containing "+Name);
						info(Name+" = "+CFG.getInt(Name));
						//CFG.get(Name);
					}else {
						debug("         Config doesn't containing "+Name);
						//CFG.addDefault(Name, Value);
					}
					//Main.This.saveConfig();
					//Main.This.saveDefaultConfig();
				}
				
				if(Action.equals("setdouble")) {
					Main.debug("      Action is setdouble");
					if(CFG.contains(Name)) {
						debug("         Config containing "+Name);
						//CFG.
						CFG.set(Name, Double.parseDouble(Value));
					}else {
						debug("         Config doesn't containing "+Name);
						CFG.set(Name, Double.parseDouble(Value));
					}
					Main.This.saveConfig();
					return true;
					//Main.This.saveDefaultConfig();
				}
				
				if(Action.equals("getdouble")) {
					Main.debug("      Action is getdouble");
					if(CFG.contains(Name)) {
						debug("         Config containing "+Name);
						info(Name+" = "+CFG.getDouble(Name));
						//CFG.get(Name);
					}else {
						debug("         Config doesn't containing "+Name);
						//CFG.addDefault(Name, Value);
					}
				}
				
				if(Action.equals("setboolean")) {
					Main.debug("      Action is setboolean");
					setBoolean(Name, Boolean.parseBoolean(Value));
					Main.This.saveConfig();
					return true;
				}
				
				if(Action.equals("getboolean")) {
					Main.debug("      Action is getboolean");
					if(CFG.contains(Name)) {
						//debug("         Config containing "+Name);
						info(Name+" = "+getBoolean(Name));
					}else {
						debug("         Config doesn't containing "+Name);
					}
				}
			}
		}
		return false;
	}
	
	
	public static int getInt(String Name) {
		Configuration CFG = Main.This.getConfig();
		if(CFG.contains(Name)) {
			debug("Config containing "+Name+" = "+CFG.getString(Name));
			return Integer.parseInt(CFG.getString(Name));
		}else {
			debug("Config doesn't containing "+Name);
			return 0;
		}
	}
	
	public static void setInt(String Name, int Value) {
		Configuration CFG = Main.This.getConfig();
		
		if(CFG.contains(Name)) {
			debug("Config containing "+Name+" = "+CFG.getString(Name));
			//CFG.
			CFG.set(Name, Value+"");
		}else {
			debug("Config doesn't containing "+Name);
			CFG.set(Name, Value+"");
		}
		Main.This.saveConfig();
	}
	
	
	public static double getDouble(String Name) {
		Configuration CFG = Main.This.getConfig();
		if(CFG.contains(Name)) {
			debug("Config containing "+Name+" = "+CFG.getString(Name));
			return Double.parseDouble(CFG.getString(Name));
		}else {
			debug("Config doesn't containing "+Name);
			return 0;
		}
	}
	
	public static void setDouble(String Name, double Value) {
		Configuration CFG = Main.This.getConfig();
		
		if(CFG.contains(Name)) {
			debug("Config containing "+Name+" = "+CFG.getString(Name));
			//CFG.
			CFG.set(Name, Value+"");
		}else {
			debug("Config doesn't containing "+Name);
			CFG.set(Name, Value+"");
		}
		Main.This.saveConfig();
	}
	
	public static boolean getBoolean(String Name) {
		Configuration CFG = Main.This.getConfig();
		if(CFG.contains(Name)) {
			debug("Config containing "+Name+" = "+CFG.getString(Name));
			return Boolean.parseBoolean(CFG.getString(Name));
		}else {
			debug("Config doesn't containing "+Name);
			return false;
		}
	}
	
	public static void setBoolean(String Name, boolean Value) {
		Configuration CFG = Main.This.getConfig();
		
		if(CFG.contains(Name)) {
			debug("Config containing "+Name+" = "+CFG.getString(Name));
			//CFG.
			CFG.set(Name, Value+"");
		}else {
			debug("Config doesn't containing "+Name);
			CFG.set(Name, Value+"");
		}
		Main.This.saveConfig();
	}
	
	public static String getString(String Name) {
		Configuration CFG = Main.This.getConfig();
		if(CFG.contains(Name)) {
			debug("Config containing "+Name+" = "+CFG.getString(Name));
			return CFG.getString(Name);
		}else {
			debug("Config doesn't containing "+Name);
			return "";
		}
	}
	
	public static void setString(String Name, String Value) {
		Configuration CFG = Main.This.getConfig();
		
		if(CFG.contains(Name)) {
			debug("Config containing "+Name+" = "+CFG.getString(Name));
			//CFG.
			CFG.set(Name, Value+"");
		}else {
			debug("Config doesn't containing "+Name);
			CFG.set(Name, Value+"");
		}
		Main.This.saveConfig();
	}
}
