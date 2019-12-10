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
			"1. Куриные яйца",
			"1.1 Никогда не бросайте куриные яйца в доме",
			"1.2 И вообще кидайте куриные яйца как гранату",
			"1.3 Кстати они реально могут взрываться",
			" ",
			"2. Каменная кнопка",
			"2.1 Каменная кнопка мощно взрывается при нажатии",
			"2.2 Кроме того она убивает даже если вы в креативе",
			"2.3 Короче каменная кнопка - самый надежный способ умереть",
			" ",
			"3. Рыбалка/Удочка",
			"3.1 Если на сервере играет чел с фиолетовым ником - не рыбачьте",
			"3.1.1 С вероятностью 10 % вы поймаете этого чела",
			"3.1.2 И тогда вам пизда",
			"3.1.3 Бронуху и КО можно",
			"3.2 Вы имеете некоторый шанс выловить крипера или другого враждебного моба",
			"3.2.1 Хотя и полезного моба тоже можно выловить",
			"3.2.2 Следовательно рыбачить надо в специально подготовленных местах",
			"3.2.3 Рекомендуется рыбачить с моста высотой примерно 10 блоков",
			"3.3 Удочка подбрасывает любую сущность высоко вверх",
			"3.3.1 Игроки при этом получают неуязвимость на 1 удар",
			"3.3.2 А вот мобы имеют шанс умереть от падения",
			"3.4 Удочка также является средством преодоления стен и других излишне вертикальных препятствий",
			"3.4.1 Поэтому используйте удочку как крюк и ползайте по стенам на здоровье",
			" ",
			"4. Рычаг",
			"4.1 Рычаг увеличивает вашу текущую скорость в 20 раз и подбрасывает вас вверх",
			"4.2 При правильном использовании можно довольно далеко улетать",
			"4.3 Нажатие на рычаг дарует неуязвимость на 1 удар",
			"4.4 И если вы не получите урона от падения, любой слеующий урон проигнорируется",
			"4.5 С другой стороны, если вы получите урон в полете - вампизда",
			" ",
			"5. Лук",
			"5.1 При достижении 30 уровня, стрелы, выпущенные из лука летят в 10 раз быстрее",
			"5.1.1 При этом значительно увеличивается урон",
			"5.1.2 Даже полный алмазный сет не спасает от смерти с одного выстрела",
			"5.1.3 Так что не стреляй в друзей, достигнув 30 уровня",
			"5.2 При достижении 40 уровня, стрелы при столкновении взрываются",
			"5.2.1 Следовательно стрелять после 40 уровня можно разве что в энде",
			"5.2.2 А чтоб такого не происходило - чаруй шмот и книжки дабы немного слить уровень",
			" ",
			"6. Другое",
			"6.1 Игрок с рыжим ником - уебак",
			"6.1.1 Но его можно попросить о кастомном нике",
			"6.1.2 Или попытаться отказаться от него",
			"6.2 В списке игроков можно увидеть, кто в данный момент спит"
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
			//Main.Core.broadcastMessage(Item.getData().getItemType()+" был исправлен");
			
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
				ply.sendMessage(prefix + ChatColor.RED + "Что нужно улучшить-то?");
				return true;
			}
				if(args[0].equalsIgnoreCase("snipe")||args[0].equalsIgnoreCase("sni")) {
					int Cost = (int)(Account.SnipeCost*Math.pow(Account.SnipeCostMult,Acc.Snipe));
					if(Acc.Score >= Cost) {
						Acc.Score -= Cost;
						Acc.Snipe += 1;
						ply.sendMessage(prefix + ChatColor.GREEN + "Стрельба улучшена до "+Acc.Snipe+" (скорость стрелы x"+(Acc.Snipe+1)+")");
					}else{
						ply.sendMessage(prefix + ChatColor.RED + "НЕ ДОСТАТОЧНЫЕ ОЧКИ");
					}
				}
				
				if(args[0].equalsIgnoreCase("armor")||args[0].equalsIgnoreCase("arm")) {
					int Cost = (int)(Account.ArmorCost*Math.pow(Account.ArmorCostMult,Acc.Armor));
					if(Acc.Score >= Cost) {
						Acc.Score -= Cost;
						Acc.Armor += 1;
						int Protection = (int)((Acc.Armor*Account.ArmorConst)/(1+Acc.Armor*Account.ArmorConst)*100);
						ply.sendMessage(prefix + ChatColor.GREEN + "Броня улучшена до "+Acc.Armor+" (снижение урона на "+(Protection)+
								"%)");
					}else{
						ply.sendMessage(prefix + ChatColor.RED + "НЕ ДОСТАТОЧНЫЕ ОЧКИ");
					}
				}
				
				if(args[0].equalsIgnoreCase("strength")||args[0].equalsIgnoreCase("str")) {
					int Cost = (int)(Account.StrengthCost*Math.pow(Account.StrengthCostMult,Acc.Strength));
					if(Acc.Score >= Cost) {
						Acc.Score -= Cost;
						Acc.Strength += 1;
						ply.sendMessage(prefix + ChatColor.GREEN + "Сила атаки улучшена до +"+Acc.Strength);
					}else{
						ply.sendMessage(prefix + ChatColor.RED + "НЕ ДОСТАТОЧНЫЕ ОЧКИ");
					}
				}
				return true;
			}
		
		
		if(command.getName().contentEquals("stats")) {
			Main.debug("   Commamd is /stats");
			
			Account Acc =Account.getAccount(ply);
		    Acc.player.sendMessage(ChatColor.YELLOW + "Твое баблишко: "+Acc.Money+" "+ChatConfig.getString("MoneyName"));
			Acc.player.sendMessage(ChatColor.GREEN + "Твои очки: "+Acc.Score);
			Acc.player.sendMessage(ChatColor.WHITE + "====================================");
			Acc.player.sendMessage(ChatColor.AQUA + "*   Сила лука: "+Acc.Snipe+" (Скорость стрелы x"+(Acc.Snipe+1)+
					". Улучшение за "+(int)(Account.SnipeCost*Math.pow(Account.SnipeCostMult,Acc.Snipe))+" очков");
			Acc.player.sendMessage(ChatColor.AQUA + "*   Сила удара: "+Acc.Strength+" (Урон +"+(Acc.Strength)+
					". Улучшение за "+(int)(Account.StrengthCost*Math.pow(Account.StrengthCostMult,Acc.Strength))+" очков");
			int Protection = (int)((Acc.Armor*Account.ArmorConst)/(1+Acc.Armor*Account.ArmorConst)*100);
			Acc.player.sendMessage(ChatColor.AQUA + "*   Броня: "+Acc.Armor+" (снижение урона на "+(Protection)+
					"%. Улучшение за "+(int)(Account.ArmorCost*Math.pow(Account.ArmorCostMult,Acc.Armor))+" очков");
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
				Main.Core.broadcastMessage(OldNick+" сменил ник на "+Nick);
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
				Main.Core.broadcastMessage(OldNick+" сменил ник на "+Nick);
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
				player.sendMessage(ChatColor.GREEN + "  /bh Help " + ChatColor.GRAY + " | Показывает правила безопасной игры на блядском сервере");
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
				player.sendMessage(ChatColor.GREEN + "  /bh Help " + ChatColor.GRAY + " | Показывает правила безопасной игры на блядском сервере");
				player.sendMessage(ChatColor.GREEN + "  /bh Info " + ChatColor.GRAY + " | Краткая информация о плагине");
				player.sendMessage(ChatColor.GREEN + "  /bh Commands " + ChatColor.GRAY + " | Показывает список доступных комманд");
				player.sendMessage(ChatColor.GREEN + "  /bh Tips " + ChatColor.GRAY + " | Выводит все подсказки");
				player.sendMessage(ChatColor.GREEN + "  /debug " + ChatColor.GRAY + " | Переключает вывод оотладочных сообщений в чат");
				player.sendMessage(ChatColor.GREEN + "  /me " + ChatColor.GRAY + " | Выполняет действие от вашего лица. Алсо /do, /self");
				player.sendMessage(ChatColor.GREEN + "  /format " + ChatColor.GRAY + " | Выводит отформатированный текст");
				player.sendMessage(ChatColor.GREEN + "        Использование: " + ChatColor.GRAY + "");
				player.sendMessage(ChatColor.GREEN + "        " + ChatColor.GRAY + " | @me - добавляет ваш ник");
				player.sendMessage(ChatColor.GREEN + "        " + ChatColor.GRAY + " | @ply:nick - находит игрока по нику и вставляет его ник");
				player.sendMessage(ChatColor.GREEN + "        " + ChatColor.GRAY + " | @here - добавляет ваши координаты");
				player.sendMessage(ChatColor.GREEN + "        " + ChatColor.GRAY + " | @@ - вставляет @");
				player.sendMessage(ChatColor.GREEN + "        " + ChatColor.GRAY + " | #ЦВЕТ - переключает цвет. Например, #GREEN или #DARK_RED");
				player.sendMessage(ChatColor.GREEN + "        " + ChatColor.GRAY + " | #СТИЛЬ - то же что и цвет, но меняет стиль текста");
				player.sendMessage(ChatColor.GREEN + "  /name " + ChatColor.GRAY + " | Меняет ваш ник. Поддерживает форматирование. Если ник не указан, выбирает случайный из списка.");
				return true;
			}
			
			if (args[0].equalsIgnoreCase("help")) {
				Main.debug("      Argument is 'help'");
				player.sendMessage(prefix + ChatColor.GREEN + "Commands:");
				
				
				//Main.Core.broadcastMessage(ChatColor.GREEN+""+ChatColor.BOLD+"[ПОДСКАЗКИ] "+ChatColor.RESET+Messages[MessageID]);
				for(String Str : Rules) {
					player.sendMessage(ChatColor.GREEN +Str);
				}
				player.sendMessage(ChatColor.GREEN + "  /bh Help " + ChatColor.GRAY + " | Показывает правила безопасной игры на блядском сервере");
				player.sendMessage(ChatColor.GREEN + "  /bh commands " + ChatColor.GRAY + " | показывает список доступных комманд");
				
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
