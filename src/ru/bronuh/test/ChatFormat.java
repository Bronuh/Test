package ru.bronuh.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatFormat implements CommandExecutor {
	public String prefix = ChatColor.DARK_GREEN + "Chat Format" + ChatColor.GRAY.toString() + " | ";
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		Main.debug("Executed ChatFormat");
		if (command.getName().equalsIgnoreCase("format")) {
			Main.debug("   USed command /format");
			

			if(args.length != 0) {
				Player player = (Player) sender;
				String Text = "";
				
				String _Text = "";
				for(String Arg : args) {
					Text += (" "+Arg);
				}
				player.sendMessage(prefix + ChatColor.GRAY + "   Formatting input: "+Text);
				for(int i = 0; i <= (Text.length()-1); i++) {
					
					if(Text.charAt(i)=='@') {
						Main.debug("      Found '@'");
						int Current = i, Start = i;
						String Cmd = "";

						
						
						while(Current<Text.length()) {
							if(Text.charAt(Current)!=' '&Text.charAt(Current)!=','&Text.charAt(Current)!='.'
									&Text.charAt(Current)!='!'&Text.charAt(Current)!='?') {
								if(Text.charAt(Current)!='@') {
									Cmd+=Text.charAt(Current);
									Current++;
								}else {
									if(Current==Start) {
										Cmd+=Text.charAt(Current);
										Current++;
									}else {
										Cmd+=Text.charAt(Current);
										Current++;
										break;
									}
								}
							}else {
								break;
							}
						}
						i = Current-1;
						

						Main.debug("         Subcmd is "+Cmd);
						
						if(Cmd.startsWith("@ply:")) {
							String replace = Cmd.replace("@ply:", "");
							String Naem = "";
							try {
								Naem = findPlayer(replace).getCustomName();
							}catch(Exception e) {
								
							}
							
							if(Naem=="") {
								Naem = replace;
							}
							_Text += Naem+ChatColor.RESET;
							Main.debug("            Pasting player with name containing '"+replace+"'");
							
						}
						
						if(Cmd.equals("@me")) {
							Main.debug("            Pasting sender name");
							_Text += player.getCustomName()+ChatColor.RESET;
						}
						
						if(Cmd.equals("@@")) {
							Main.debug("            Pasting '@'");
							_Text += "@";
						}
						
						if(Cmd.equals("@here")) {
							Main.debug("            Pasting sender location");
							int x,y,z;
							x = player.getLocation().getBlockX();
							y = player.getLocation().getBlockY();
							z = player.getLocation().getBlockZ();
							_Text += "XYZ: ["+x+" \\ "+y+" \\ "+z+"]";
						}
						
					}else {
						_Text += Text.charAt(i);
					}
					
				}
				Main.debug("   Applying markup");
				_Text = _Text.replace("#RESET",""+ChatColor.RESET)
				.replace("#RED",""+ChatColor.RED)
				.replace("#GREEN",""+ChatColor.GREEN)
				.replace("#BLUE",""+ChatColor.BLUE)
				.replace("#GRAY",""+ChatColor.GRAY)
				.replace("#AQUA",""+ChatColor.AQUA)
				.replace("#DARK_PURPLE",""+ChatColor.DARK_PURPLE)
				.replace("#YELLOW",""+ChatColor.YELLOW)
				.replace("#LIGHT_PURPLE",""+ChatColor.LIGHT_PURPLE)
				.replace("#DARK_RED",""+ChatColor.DARK_RED)
				.replace("#ORANGE",""+ChatColor.GOLD)
				.replace("#DARK_GREEN",""+ChatColor.DARK_GREEN)
				.replace("#DARK_AQUA",""+ChatColor.DARK_AQUA)
				.replace("#DARK_GRAY",""+ChatColor.DARK_GRAY)
				.replace("#WHITE",""+ChatColor.WHITE)
				.replace("#BOLD",""+ChatColor.BOLD)
				.replace("#ITALIC",""+ChatColor.ITALIC)
				.replace("#UNDERLINE",""+ChatColor.UNDERLINE)
				.replace("#DARK_BLUE",""+ChatColor.DARK_BLUE)
				.replace("#MAGIC",""+ChatColor.MAGIC)
				.replace("#STRIKE",""+ChatColor.STRIKETHROUGH);
				//Text.replace("@me",player.getCustomName()+ChatColor.RESET);
					
				Main.Core.broadcastMessage(_Text);
			}
			
			return true;
		}
		
		return false;
	}
	
	public static Player findPlayer(String Name) {
		return Bukkit.getPlayer(Name);
	}
	
	public static String formatPlayer(Player player, String Text) {

		String _Text = "";
		for(int i = 0; i <= (Text.length()-1); i++) {
			
			if(Text.charAt(i)=='@') {
				//Main.Core.broadcastMessage(ChatColor.GRAY+"[DEBUG] "+ChatColor.RESET+"-  Found '@");
				int Current = i, Start = i;

				String Cmd = "";

				
				
				while(Current<Text.length()) {
					if(Text.charAt(Current)!=' ') {
						if(Text.charAt(Current)!='@') {
							Cmd+=Text.charAt(Current);
							Current++;
						}else {
							if(Current==Start) {
								Cmd+=Text.charAt(Current);
								Current++;
							}else {
								Cmd+=Text.charAt(Current);
								Current++;
								break;
							}
						}
					}else {
						break;
					}
				}
				i = Current-1;
				

				//Main.Core.broadcastMessage(ChatColor.GRAY+"[DEBUG] "+ChatColor.RESET+"-   Subcmd is "+Cmd);
				
				if(Cmd.startsWith("@ply:")) {
					String replace = Cmd.replace("@ply:", "");
					String Naem = "";
					try {
						Naem = findPlayer(replace).getCustomName();
					}catch(Exception e) {
						
					}
					
					if(Naem=="") {
						Naem = replace;
					}
					_Text += Naem+ChatColor.RESET;
					//Main.Core.broadcastMessage(ChatColor.GRAY+"[DEBUG] "+ChatColor.RESET+"-   Pasting selected Player");
					
				}
				
				if(Cmd.equals("@me")) {
					//Main.Core.broadcastMessage(ChatColor.GRAY+"[DEBUG] "+ChatColor.RESET+"-    Pasting sender name");
					_Text += player.getCustomName()+ChatColor.RESET;
				}
				
				if(Cmd.equals("@@")) {
					//Main.Core.broadcastMessage(ChatColor.GRAY+"[DEBUG] "+ChatColor.RESET+"-    Pasting @");
					_Text += "@";
				}
				
				if(Cmd.equals("@here")) {
					//Main.Core.broadcastMessage(ChatColor.GRAY+"[DEBUG] "+ChatColor.RESET+"-    Pasting sender location");
					int x,y,z;
					x = player.getLocation().getBlockX();
					y = player.getLocation().getBlockY();
					z = player.getLocation().getBlockZ();
					_Text += "XYZ: ["+x+" \\ "+y+" \\ "+z+"]";
				}
				
			}else {
				_Text += Text.charAt(i);
			}
			
		}
		 //Format = _Text;
		_Text = _Text.replace("#RESET",""+ChatColor.RESET)
		.replace("#RED",""+ChatColor.RED)
		.replace("#GREEN",""+ChatColor.GREEN)
		.replace("#BLUE",""+ChatColor.BLUE)
		.replace("#GRAY",""+ChatColor.GRAY)
		.replace("#AQUA",""+ChatColor.AQUA)
		.replace("#DARK_PURPLE",""+ChatColor.DARK_PURPLE)
		.replace("#YELLOW",""+ChatColor.YELLOW)
		.replace("#LIGHT_PURPLE",""+ChatColor.LIGHT_PURPLE)
		.replace("#DARK_RED",""+ChatColor.DARK_RED)
		.replace("#ORANGE",""+ChatColor.GOLD)
		.replace("#DARK_GREEN",""+ChatColor.DARK_GREEN)
		.replace("#DARK_AQUA",""+ChatColor.DARK_AQUA)
		.replace("#DARK_GRAY",""+ChatColor.DARK_GRAY)
		.replace("#WHITE",""+ChatColor.WHITE)
		.replace("#BOLD",""+ChatColor.BOLD)
		.replace("#ITALIC",""+ChatColor.ITALIC)
		.replace("#UNDERLINE",""+ChatColor.UNDERLINE)
		.replace("#DARK_BLUE",""+ChatColor.DARK_BLUE)
		.replace("#MAGIC",""+ChatColor.MAGIC)
		.replace("#STRIKE",""+ChatColor.STRIKETHROUGH);
		return _Text;
	}
	
	public static String format(String Text) {

		String _Text = "";
		for(int i = 0; i <= (Text.length()-1); i++) {
			
			if(Text.charAt(i)=='@') {
				//Main.Core.broadcastMessage(ChatColor.GRAY+"[DEBUG] "+ChatColor.RESET+"-  Found '@");
				int Current = i, Start = i;

				String Cmd = "";

				
				
				while(Current<Text.length()) {
					if(Text.charAt(Current)!=' ') {
						if(Text.charAt(Current)!='@') {
							Cmd+=Text.charAt(Current);
							Current++;
						}else {
							if(Current==Start) {
								Cmd+=Text.charAt(Current);
								Current++;
							}else {
								Cmd+=Text.charAt(Current);
								Current++;
								break;
							}
						}
					}else {
						break;
					}
				}
				i = Current-1;
				

				
				if(Cmd.startsWith("@ply:")) {
					String replace = Cmd.replace("@ply:", "");
					String Naem = "";
					try {
						Naem = findPlayer(replace).getCustomName();
					}catch(Exception e) {
						
					}
					
					if(Naem=="") {
						Naem = replace;
					}
					_Text += Naem+ChatColor.RESET;
					//Main.Core.broadcastMessage(ChatColor.GRAY+"[DEBUG] "+ChatColor.RESET+"-   Pasting selected Player");
					
				}
				
				
				
				if(Cmd.equals("@@")) {
					//Main.Core.broadcastMessage(ChatColor.GRAY+"[DEBUG] "+ChatColor.RESET+"-    Pasting @");
					_Text += "@";
				}
				
				
				
			}else {
				_Text += Text.charAt(i);
			}
			
		}
		 //Format = _Text;
		_Text = _Text.replace("#RESET",""+ChatColor.RESET)
		.replace("#RED",""+ChatColor.RED)
		.replace("#GREEN",""+ChatColor.GREEN)
		.replace("#BLUE",""+ChatColor.BLUE)
		.replace("#GRAY",""+ChatColor.GRAY)
		.replace("#AQUA",""+ChatColor.AQUA)
		.replace("#DARK_PURPLE",""+ChatColor.DARK_PURPLE)
		.replace("#YELLOW",""+ChatColor.YELLOW)
		.replace("#LIGHT_PURPLE",""+ChatColor.LIGHT_PURPLE)
		.replace("#DARK_RED",""+ChatColor.DARK_RED)
		.replace("#ORANGE",""+ChatColor.GOLD)
		.replace("#DARK_GREEN",""+ChatColor.DARK_GREEN)
		.replace("#DARK_AQUA",""+ChatColor.DARK_AQUA)
		.replace("#DARK_GRAY",""+ChatColor.DARK_GRAY)
		.replace("#WHITE",""+ChatColor.WHITE)
		.replace("#BOLD",""+ChatColor.BOLD)
		.replace("#ITALIC",""+ChatColor.ITALIC)
		.replace("#UNDERLINE",""+ChatColor.UNDERLINE)
		.replace("#DARK_BLUE",""+ChatColor.DARK_BLUE)
		.replace("#MAGIC",""+ChatColor.MAGIC)
		.replace("#STRIKE",""+ChatColor.STRIKETHROUGH);
		return _Text;
	}
}
