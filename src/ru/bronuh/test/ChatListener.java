package ru.bronuh.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
//import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_14_R1.CustomFunction.a;


public class ChatListener implements CommandExecutor {
	
	public static String Rules[]= {
			"1. ������� ����",
			"1.1 ������� �� �������� ������� ���� � ����",
			"1.2 � ������ ������� ������� ���� ��� �������",
			"1.3 ������ ��� ������� ����� ����������",
			" ",
			"2. �������� ������",
			"2.1 �������� ������ ����� ���������� ��� �������",
			"2.2 ����� ���� ��� ������� ���� ���� �� � ��������",
			"2.3 ������ �������� ������ - ����� �������� ������ �������",
			" ",
			"3. �������/������",
			"3.1 ���� �� ������� ������ ��� � ���������� ����� - �� ��������",
			"3.1.1 � ������������ 10 % �� �������� ����� ����",
			"3.1.2 � ����� ��� �����",
			"3.1.3 ������� � �� �����",
			"3.2 �� ������ ��������� ���� �������� ������� ��� ������� ����������� ����",
			"3.2.1 ���� � ��������� ���� ���� ����� ��������",
			"3.2.2 ������������� �������� ���� � ���������� �������������� ������",
			"3.2.3 ������������� �������� � ����� ������� �������� 10 ������",
			"3.3 ������ ������������ ����� �������� ������ �����",
			"3.3.1 ������ ��� ���� �������� ������������ �� 1 ����",
			"3.3.2 � ��� ���� ����� ���� ������� �� �������",
			"3.4 ������ ����� �������� ��������� ����������� ���� � ������ ������� ������������ �����������",
			"3.4.1 ������� ����������� ������ ��� ���� � �������� �� ������ �� ��������",
			" ",
			"4. �����",
			"4.1 ����� ����������� ���� ������� �������� � 20 ��� � ������������ ��� �����",
			"4.2 ��� ���������� ������������� ����� �������� ������ �������",
			"4.3 ������� �� ����� ������ ������������ �� 1 ����",
			"4.4 � ���� �� �� �������� ����� �� �������, ����� �������� ���� ���������������",
			"4.5 � ������ �������, ���� �� �������� ���� � ������ - ��������",
			" ",
			"5. ���",
			"5.1 ��� ���������� 30 ������, ������, ���������� �� ���� ����� � 10 ��� �������",
			"5.1.1 ��� ���� ����������� ������������� ����",
			"5.1.2 ���� ������ �������� ��� �� ������� �� ������ � ������ ��������",
			"5.1.3 ��� ��� �� ������� � ������, ��������� 30 ������",
			"5.2 ��� ���������� 40 ������, ������ ��� ������������ ����������",
			"5.2.1 ������������� �������� ����� 40 ������ ����� ����� ��� � ����",
			"5.2.2 � ���� ������ �� ����������� - ����� ���� � ������ ���� ������� ����� �������",
			" ",
			"6. ������",
			"6.1 ����� � ����� ����� - �����",
			"6.1.1 �� ��� ����� ��������� � ��������� ����",
			"6.1.2 ��� ���������� ���������� �� ����",
			"6.2 � ������ ������� ����� �������, ��� � ������ ������ ����"
	};
	
	public String prefix = ChatColor.DARK_GREEN + "Bronuh's Shit" + ChatColor.GRAY.toString() + " | ";
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		//if(command.get)
		Main.debug("Executed ChatListener");
		Player ply = (Player)sender;
		if(command.getName().contentEquals("fix")) {
			Main.debug("   Commamd is /fix");
			
			ItemStack Item = ply.getInventory().getItemInMainHand();
			ItemStack Item2 = ply.getInventory().getItemInOffHand();
			//Main.Core.broadcastMessage(Item.getData().getItemType()+" ��� ���������");
			
			Item.setDurability((short)0);
			Item2.setDurability((short)0);
			//e.getPlayer().getInventory().remove(Main);
			ply.updateInventory();
			return true;
		}
		
		if(command.getName().contentEquals("upgrade")) {
			Main.debug("   Commamd is /upgrade");
			//Player ply = (Player)sender;
			Account Acc = Account.getAccount(ply);
			if(args.length == 0) {
				ply.sendMessage(prefix + ChatColor.RED + "��� ����� ��������-��?");
				return true;
			}
				if(args[0].equalsIgnoreCase("snipe")||args[0].equalsIgnoreCase("sni")) {
					int Cost = (int)(Account.SnipeCost*Math.pow(Account.SnipeCostMult,Acc.Snipe));
					if(Acc.Score >= Cost) {
						Acc.Score -= Cost;
						Acc.Snipe += 1;
						ply.sendMessage(prefix + ChatColor.GREEN + "�������� �������� �� "+Acc.Snipe+" (�������� ������ x"+(Acc.Snipe+1)+")");
					}else{
						ply.sendMessage(prefix + ChatColor.RED + "�� ����������� ����");
					}
				}
				
				if(args[0].equalsIgnoreCase("armor")||args[0].equalsIgnoreCase("arm")) {
					int Cost = (int)(Account.ArmorCost*Math.pow(Account.ArmorCostMult,Acc.Armor));
					if(Acc.Score >= Cost) {
						Acc.Score -= Cost;
						Acc.Armor += 1;
						int Protection = (int)((Acc.Armor*Account.ArmorConst)/(1+Acc.Armor*Account.ArmorConst)*100);
						ply.sendMessage(prefix + ChatColor.GREEN + "����� �������� �� "+Acc.Armor+" (�������� ����� �� "+(Protection)+
								"%)");
					}else{
						ply.sendMessage(prefix + ChatColor.RED + "�� ����������� ����");
					}
				}
				
				if(args[0].equalsIgnoreCase("strength")||args[0].equalsIgnoreCase("str")) {
					int Cost = (int)(Account.StrengthCost*Math.pow(Account.StrengthCostMult,Acc.Strength));
					if(Acc.Score >= Cost) {
						Acc.Score -= Cost;
						Acc.Strength += 1;
						ply.sendMessage(prefix + ChatColor.GREEN + "���� ����� �������� �� +"+Acc.Strength);
					}else{
						ply.sendMessage(prefix + ChatColor.RED + "�� ����������� ����");
					}
				}
				return true;
			}
		
		
		if(command.getName().contentEquals("stats")) {
			Main.debug("   Commamd is /stats");
			
			Account Acc =Account.getAccount(ply);
		    Acc.player.sendMessage(ChatColor.YELLOW + "���� ��������: "+Acc.Money+" "+ChatConfig.getString("MoneyName"));
			Acc.player.sendMessage(ChatColor.GREEN + "���� ����: "+Acc.Score);
			Acc.player.sendMessage(ChatColor.WHITE + "====================================");
			Acc.player.sendMessage(ChatColor.AQUA + "*   ���� ����: "+Acc.Snipe+" (�������� ������ x"+(Acc.Snipe+1)+
					". ��������� �� "+(int)(Account.SnipeCost*Math.pow(Account.SnipeCostMult,Acc.Snipe))+" �����");
			Acc.player.sendMessage(ChatColor.AQUA + "*   ���� �����: "+Acc.Strength+" (���� +"+(Acc.Strength)+
					". ��������� �� "+(int)(Account.StrengthCost*Math.pow(Account.StrengthCostMult,Acc.Strength))+" �����");
			int Protection = (int)((Acc.Armor*Account.ArmorConst)/(1+Acc.Armor*Account.ArmorConst)*100);
			Acc.player.sendMessage(ChatColor.AQUA + "*   �����: "+Acc.Armor+" (�������� ����� �� "+(Protection)+
					"%. ��������� �� "+(int)(Account.ArmorCost*Math.pow(Account.ArmorCostMult,Acc.Armor))+" �����");
			Acc.save();
			return true;
		}
		
		if(command.getName().contentEquals("gm")) {
			Main.debug("   Commamd is /gm");
			
			if(ply.getGameMode()==GameMode.CREATIVE) {
				ply.setGameMode(GameMode.SURVIVAL);
			}else {
				ply.setGameMode(GameMode.CREATIVE);
			}
			return true;
		}
		
		if (command.getName().equalsIgnoreCase("name")) {
			Main.debug("   Commamd is /name");
			if(args.length == 0) {
				EventListener.changeName((Player)sender);
				return true;
			}else {
				String Text = "";
				for(String Arg : args) {
					Text += (" "+Arg);
				}
				Player Ply = (Player)sender;
				String OldNick = Ply.getCustomName()+ChatColor.RESET;
				String Nick = ChatFormat.formatPlayer((Player)sender, Text)+ChatColor.RESET;
				Ply.setDisplayName(Nick);
				Ply.setCustomName(Nick);
				Ply.setCustomNameVisible(true);
				EventListener.textLevel(Ply);
				Main.Core.broadcastMessage(OldNick+" ������ ��� �� "+Nick);
			}
		}
		
		if (command.getName().equalsIgnoreCase("othername")) {
			Main.debug("   Commamd is /othername");
			Player Tar = Bukkit.getPlayer(args[0]);
			
			if(args.length == 1) {
				EventListener.changeName(Tar);
				return true;
			}else {
				String Text = "";
				int i =0;
				for(String Arg : args) {
					if(i!=0) {
						if(i>1) {
							Text += (" "+Arg);
						}else {
							Text += (Arg);
						}
					}
					i++;
				}
				Player Ply = Tar;
				String OldNick = Ply.getCustomName()+ChatColor.RESET;
				String Nick = ChatFormat.formatPlayer((Player)sender, Text)+ChatColor.RESET;
				Ply.setDisplayName(Nick);
				Ply.setCustomName(Nick);
				Ply.setCustomNameVisible(true);
				EventListener.textLevel(Ply);
				Main.Core.broadcastMessage(OldNick+" ������ ��� �� "+Nick);
			}
		}
		
		if (command.getName().equalsIgnoreCase("self")) {
			Main.debug("   Connamd is /self");
			Player player = (Player) sender;
			if(args.length != 0) {
				String Args = "";
				for(String Arg : args) {
					Args += (" "+Arg);
				}
				
				Main.Core.broadcastMessage(player.getCustomName()+ChatColor.RESET+Args);
			}
			return true;
		}
		
		if (command.getName().equalsIgnoreCase("debug")) {
			Main.debug("   Command is /debug");
			Main._DEBUG = !Main._DEBUG;
			Main.info("Set _DEBUG = "+Main._DEBUG);
			return true;
		}
		
		if (command.getName().equalsIgnoreCase("bronuhsshit")) {
			Player player = (Player) sender;
			Main.debug("   Comand is /bh");
			if(args.length == 0) {
				player.sendMessage(prefix + ChatColor.GREEN + "Commands:");
				player.sendMessage(ChatColor.GREEN + "  /bh Help " + ChatColor.GRAY + " | ���������� ������� ���������� ���� �� �������� �������");
				player.sendMessage(ChatColor.GREEN + "  /bh Info " + ChatColor.GRAY + " | Shows info about the plugin");
				return true;
			}
			if (args[0].equalsIgnoreCase("tips")) {
				Main.debug("      Argument is 'tips'");
				for(int i = 1;i < Tips.Messages.length; i++) {
					player.sendMessage(i+". "+Tips.Messages[i]);
				}
				return true;
			}
			
			
			if (args[0].equalsIgnoreCase("sys")) {
				Main.debug("      Argument is 'sys'");
			
					player.sendMessage(prefix + ChatColor.GREEN + "Available processors (cores): " + 
					        Runtime.getRuntime().availableProcessors());
					player.sendMessage(prefix + ChatColor.GREEN + "Free memory: " + 
					        Runtime.getRuntime().freeMemory()/1024/1024);
					player.sendMessage(prefix + ChatColor.GREEN + "Max memory: " + 
					        Runtime.getRuntime().maxMemory()/1024/1024);
					player.sendMessage(prefix + ChatColor.GREEN + "Total memory: " + 
					        Runtime.getRuntime().totalMemory()/1024/1024);
				
				return true;
			}
			if (args[0].equalsIgnoreCase("commands")) {
				Main.debug("      Argument is 'commands'");
				player.sendMessage(prefix + ChatColor.GREEN + "Commands:");
				player.sendMessage(ChatColor.GREEN + "  /bh Help " + ChatColor.GRAY + " | ���������� ������� ���������� ���� �� �������� �������");
				player.sendMessage(ChatColor.GREEN + "  /bh Info " + ChatColor.GRAY + " | ������� ���������� � �������");
				player.sendMessage(ChatColor.GREEN + "  /bh Commands " + ChatColor.GRAY + " | ���������� ������ ��������� �������");
				player.sendMessage(ChatColor.GREEN + "  /bh Tips " + ChatColor.GRAY + " | ������� ��� ���������");
				player.sendMessage(ChatColor.GREEN + "  /debug " + ChatColor.GRAY + " | ����������� ����� ����������� ��������� � ���");
				player.sendMessage(ChatColor.GREEN + "  /me " + ChatColor.GRAY + " | ��������� �������� �� ������ ����. ���� /do, /self");
				player.sendMessage(ChatColor.GREEN + "  /format " + ChatColor.GRAY + " | ������� ����������������� �����");
				player.sendMessage(ChatColor.GREEN + "        �������������: " + ChatColor.GRAY + "");
				player.sendMessage(ChatColor.GREEN + "        " + ChatColor.GRAY + " | @me - ��������� ��� ���");
				player.sendMessage(ChatColor.GREEN + "        " + ChatColor.GRAY + " | @ply:nick - ������� ������ �� ���� � ��������� ��� ���");
				player.sendMessage(ChatColor.GREEN + "        " + ChatColor.GRAY + " | @here - ��������� ���� ����������");
				player.sendMessage(ChatColor.GREEN + "        " + ChatColor.GRAY + " | @@ - ��������� @");
				player.sendMessage(ChatColor.GREEN + "        " + ChatColor.GRAY + " | #���� - ����������� ����. ��������, #GREEN ��� #DARK_RED");
				player.sendMessage(ChatColor.GREEN + "        " + ChatColor.GRAY + " | #����� - �� �� ��� � ����, �� ������ ����� ������");
				player.sendMessage(ChatColor.GREEN + "  /name " + ChatColor.GRAY + " | ������ ��� ���. ������������ ��������������. ���� ��� �� ������, �������� ��������� �� ������.");
				return true;
			}
			
			if (args[0].equalsIgnoreCase("help")) {
				Main.debug("      Argument is 'help'");
				player.sendMessage(prefix + ChatColor.GREEN + "Commands:");
				
				
				//Main.Core.broadcastMessage(ChatColor.GREEN+""+ChatColor.BOLD+"[���������] "+ChatColor.RESET+Messages[MessageID]);
				for(String Str : Rules) {
					player.sendMessage(ChatColor.GREEN +Str);
				}
				player.sendMessage(ChatColor.GREEN + "  /bh Help " + ChatColor.GRAY + " | ���������� ������� ���������� ���� �� �������� �������");
				player.sendMessage(ChatColor.GREEN + "  /bh commands " + ChatColor.GRAY + " | ���������� ������ ��������� �������");
				
		        if (player.hasPermission("BHS.reload")) 
		            player.sendMessage(ChatColor.GREEN + "  /bh Reload" + ChatColor.GRAY + " | Reloads the config");

		       
				return true;
			}
			
			if (args[0].equalsIgnoreCase("reload")) {
				Main.debug("      Argument is 'reload'");
				if (player.hasPermission("BHS.reload")) {
					
					Bukkit.getPluginManager().getPlugin("BronuhsShit").reloadConfig();
					//Bukkit.getPluginManager().getPlugin("BronuhsShit").getCo
					player.sendMessage(prefix + ChatColor.GREEN + "Config reloaded!");
					
					return true;
				} else {
					player.sendMessage(prefix + ChatColor.RED + "You do not have the premission needed to use this command.");
					return true;
				}
			}
			
			if (args[0].equalsIgnoreCase("info")) {
				Main.debug("      Argument is 'info'");
				String version = Bukkit.getPluginManager().getPlugin("BronuhsShit").getDescription().getVersion();

				player.sendMessage(prefix + ChatColor.GREEN + "Info:");
				player.sendMessage(ChatColor.GREEN + "  Made by " + ChatColor.GRAY + "Bronuh");
				player.sendMessage(ChatColor.GREEN + "  Version: " + ChatColor.GRAY + version);
				//player.sendMessage(ChatColor.GREEN + "  Link: " + ChatColor.GRAY + "http://bit.ly/1UrBeYp");
				return true;
			}
		}
		return false;
	}
	
	public static FileConfiguration getCfg() {
		return Bukkit.getPluginManager().getPlugin("BronuhsShit").getConfig();
	}
}
