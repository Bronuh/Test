package ru.bronuh.test;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.util.Vector;




public class EventListener implements Listener {
	public static PluginLogger Log;
	public static double FishChance, EggChance = 0.1;
	public static Server Core;
	public static ArrayList<Player> Damages = new ArrayList<Player>();
	public static HashMap<String,Integer> Levels = new HashMap<String,Integer>();
	public static HashMap<String,Location> Locations = new HashMap<String,Location>();
	public static String[] JoinMessages = {"PLY решил зайти в ебучий ад. Удачи ему, че",
			"PLY решил познать боль в мире лисичек",
			"PLY ошибся сервером, давайте поможем ему найти выход",
			"PLY не выжил в туалете гошона",
			"PLY вляпался в наш сервер",
			"new Player(\"PLY\").go(world.NAHUI)",
			"Мы настоятельно рекомендуем PLY закрыть дверь с той стороны сервера",
			"PLY зашел на сервер чтобы принести себя в жертву КАМЕННОЙ КНОПКЕ",
			"PLY желает испытать своё терпение... Или терпение всех остальных игроков, лол",
			"PLY увеличил потребление кислорода на сервере",
			"PLY не смог устоять перед силой рандома",
			"PLY решил испытать свою удачу",
			"PLY был послан нах и потому пришел сюда"};
	
	public static String[] QuitMessages = {"PLY не выдержал пыток",
			"PLY устал испытывать интересные ощущения",
			"PLY закончил сливать катку",
			"PLY теряет связь с миром",
			"PLY закрыл дверь с той стороны",
			"PLY наконец-то свалил отсюда",
			"Терпение PLY не прошло испытаний"};
	
	public static String[] BedEnterMessages = {"PLY спит",
			"PLY упал на кровать",
			"PLY чет устал",
			"PLY давит на массу",
			"PLY плющит харю",
			"PLY втыкает в подушку",
			"PLY медитирует",
			"PLY пытается познать дзен",
			"PLY ушел в себя"};
			
	public static String[] BedLeaveMessages = {"PLY не спит",
			"PLY упал с кровати",
			"PLY уже полон сил!",
			"PLY заканчивает давить массу",
			"PLY проснулся",
			"PLY вскочил с кровати",
			"PLY не смог познать дзен"};
	
	public static String[] DeathMessages = {"PLY стал на 100% мертвее чем это необходимо",
			"PLY решил удобрить собой землю",
			"PLY сливает катку",
			"PLY поделился на ноль"};
	
	
	
	PlayerData Vaster = new PlayerData("VasterLordEP", 
			new String[]{"ПАСТИРАСТИР","ВОСТИР","ВЫСТЕР","ПОТРАЧЕНЫЙ","Можно я выйду?","СИДОДЖИ","Глиномес","ГААСТИР","МАМОЧКИН БЛЗНДЗР",
					"Приемный"},
			"§r§l§5");
	
	PlayerData Bronuh = new PlayerData("Bronuh", 
			new String[]{"Бронхи","ЛЕСИЧКЕ","Рыжая морда","Зоофил","Бронхомунал","Пушистая задница","НОМЕР 6 С ДОПОЛНИТЕЛЬНЫМ ПАДЕНИЕМ",
					"Шуба","Коврик","Рандом","Неадвокат","Фурри","Неведомая Хрень","Этот","Жи Есть","НЛО","Тот-из-за-кого-всё-идет-не-так",
					"Неучтенное обстоятельство","Погода на Марсе","Фаза Луны","Пушок","Котлеточка","Картошечка","Макарошечка","КУСЬ"},
			"§r§l§6");
	
	PlayerData Maroka = new PlayerData("Maroka", 
			new String[]{"Maroka","Садовый гном"},
			"§r§l§4");
	
	PlayerData Hotsu = new PlayerData("Hotsu", 
			new String[]{"Hotsu","Хотсушек","Копатыч","Картофелекопалочка","Енотокотик"},
			"§r§l§1");
	
	PlayerData ZiniKo = new PlayerData("ZiniKo", 
			new String[]{"ZiniKo","Благушек","Стеностроитель","Енотокотик(2)"},
			"§r§l§e");
	
	PlayerData PrometheusCoal = new PlayerData("PrometheusCoal", 
			new String[]{"PrometheusCoal","Дебил","Совенок","КОНБ","Подпортившаяся лошадь","ЫЫ","Конина","Казахское мясо"},
			"§r§l§b");
	/*PrometheusCoal
	PlayerData Vaster = new PlayerData("", 
			new String[]{},
			"");
			*/
	
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		
		LivingEntity Damager;
		if(e.getDamager() instanceof Arrow) {
			Main.debug("Damager is Arrow");
			Arrow Arr = (Arrow)e.getDamager();
			Damager = (LivingEntity)Arr.getShooter();
			Main.debug("Shooter: "+((LivingEntity)Arr.getShooter()).getType());
		}else {
			Damager = (LivingEntity)(e.getDamager());
		}
		
		LivingEntity Victim = (LivingEntity)(e.getEntity());
		double Damage = Math.min(e.getDamage(),Victim.getHealth());
		
		if(Damager instanceof Player) {
			Account Acc = Account.getAccount((Player)Damager);
			
				
				Main.debug("DOMAGE EVENT: "+Damage);
				if(Damage>=Victim.getHealth()) {
					
					Account.getAccount((Player)Damager).Score+=1;
				}
				
				
			
			if(e.getDamager() instanceof Arrow) {
			}else {
				e.setDamage(e.getDamage()+Acc.Strength*Account.StrengthConst);
				Damage = Math.min(e.getDamage(),Victim.getHealth());
				Main.debug("FULL DAMAGE: "+Damage);
			}
			Account.getAccount((Player)Damager).Money+=(int)Damage;
		}
		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{

			//Main.Log.info("[LOGGER] "+e.getPlayer().getName()+" решил зайти в ебучий ад. Удачи ему, че.");
		//Random r = new Random();
		int num = (int)Math.round(Math.random()*(JoinMessages.length-1));
		
		
		String Nick = e.getPlayer().getName();
		
		PlayerData Find = Players.GetPlayerData(e.getPlayer().getName());
		
		if(Find!=null) {
			Nick = Find.GetRandom();
			e.getPlayer().setDisplayName(Nick);
			e.getPlayer().setCustomName(Nick);
			e.getPlayer().setCustomNameVisible(true);
			textLevel(e.getPlayer());
		}else {
		
			e.getPlayer().setDisplayName(Nick);
			e.getPlayer().setCustomName(Nick);
			e.getPlayer().setCustomNameVisible(true);
			textLevel(e.getPlayer());
			
		}
		
		Nick = "§l§e"+Nick+"§r";
		
		Account Acc =Account.getAccount(e.getPlayer());
		
	     e.setJoinMessage("[JOIN MESSAGE] "+ JoinMessages[num].replace("PLY",Nick));
	    Acc.player.sendMessage(ChatColor.YELLOW + "Твое баблишко: "+Acc.Money+" "+ChatConfig.getString("MoneyName"));
		Acc.player.sendMessage(ChatColor.YELLOW + "Твои очки: "+Acc.Score);
	     
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e)
	{
		int num = (int)Math.round(Math.random()*(QuitMessages.length-1));
		Account Acc =Account.getAccount(e.getPlayer());
		Acc.save();
		
		Account.Accounts.remove(Acc);
		e.setQuitMessage("[QUIT MESSAGE] "+ QuitMessages[num].replace("PLY",e.getPlayer().getCustomName()+"§r"));
	
	}
	
	
	@EventHandler
	public void onPlayerBedEnter(PlayerBedEnterEvent e) {
		
		if(e.getBedEnterResult()==BedEnterResult.OK) {
			
			int num = (int)Math.round(Math.random()*(BedEnterMessages.length-1));
			String Nick = "[СПИТ] "+e.getPlayer().getCustomName()+ChatColor.RESET;
			e.getPlayer().setDisplayName(Nick);
			e.getPlayer().setCustomName(Nick);
			e.getPlayer().setCustomNameVisible(true);
			textLevel(e.getPlayer());
			
			Main.Core.broadcastMessage(BedEnterMessages[num].replace("PLY",e.getPlayer().getCustomName()+ChatColor.AQUA));
		}
		textLevel(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerBedLeave(PlayerBedLeaveEvent e) {
		
		int num = (int)Math.round(Math.random()*(BedLeaveMessages.length-1));
		
		String Nick = e.getPlayer().getCustomName();
		Nick = Nick.replace("[СПИТ] ", "")+ChatColor.RESET;
		e.getPlayer().setDisplayName(Nick);
		e.getPlayer().setCustomName(Nick);
		e.getPlayer().setCustomNameVisible(true);
		textLevel(e.getPlayer());
		
		Main.Core.broadcastMessage(BedLeaveMessages[num].replace("PLY",e.getPlayer().getCustomName()+ChatColor.YELLOW));
		e.getPlayer().setHealth(20);
		textLevel(e.getPlayer());
	}
	
	
	@EventHandler
	public void onPlayerLevelUp(PlayerLevelChangeEvent e) {
		
		e.getPlayer().setHealth(20);
		//e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel()+20);
		Account.getAccount(e.getPlayer()).Score+=e.getNewLevel();
		textLevel(e.getPlayer());
		if(e.getNewLevel()>=ChatConfig.getInt("SpeedUpLevel")&e.getOldLevel()<ChatConfig.getInt("SpeedUpLevel")) {
			//Main.Core.broadcastMessage(ChatColor.BLUE+e.getPlayer().getName()+ " достиг мастерства во владении луком");
		}
		if(e.getNewLevel()>=ChatConfig.getInt("ExplosionLevel")&e.getOldLevel()<ChatConfig.getInt("ExplosionLevel")) {
			//Main.Core.broadcastMessage(ChatColor.DARK_PURPLE+e.getPlayer().getName()+ " стал богом лучников");
		}
	}
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent e) {
		String state = e.getState().name();
		Main.debug("FISHING EVENT: "+state);
		//Main.Core.broadcastMessage("[DEBUG]: "+ChatColor.WHITE+"FISHING EVENT: "+e.getState().name());
		textLevel(e.getPlayer());
		if(state.equals("FAILED_ATTEMPT")) {
			Main.Core.broadcastMessage(ChatColor.RED+e.getPlayer().getName()+ " познал мощь своей удочки");
			e.getPlayer().setHealth(1);
			e.getPlayer().getWorld().createExplosion(e.getHook().getLocation().add(new Vector(0,0,0)), 9, false);
			
		}
		if(e.getState() == State.IN_GROUND&ChatConfig.getBoolean("HookRod")) {
			
			Player tgt = e.getPlayer();
			if(!tgt.isSneaking()) {
				Damages.remove(e.getPlayer());
				Vector vec = e.getHook().getLocation().subtract(e.getPlayer().getLocation()).toVector();
				tgt.setVelocity(vec.normalize().multiply(vec.length()*1.4));
				
				Damages.add(tgt);
				//e.get
				ItemStack Main, Off;
				
				Main = e.getPlayer().getInventory().getItemInMainHand();
				Off = e.getPlayer().getInventory().getItemInOffHand();
				
				if(Main.getType()==Material.FISHING_ROD) {
					Main.setDurability((short)0);
					//e.getPlayer().getInventory().remove(Main);
					e.getPlayer().updateInventory();
					
				}else if(Off.getType()==Material.FISHING_ROD) {
					Off.setDurability((short)0);
					//e.getPlayer().getInventory().remove(Off);
					e.getPlayer().updateInventory();
				}
			}
		}
		if(state.equals("CAUGHT_ENTITY")) {
			//Main.Core.broadcastMessage(ChatColor.RED+e.getPlayer().getName()+ " познал мощь своей удочки");
			//e.getPlayer().setHealth(1);
			//e.getPlayer().getWorld().createExplosion(e.getHook().getLocation().add(new Vector(0,0,0)), 9, false);
			double x = e.getCaught().getVelocity().getX();
        	double y = e.getCaught().getVelocity().getY();
        	double z = e.getCaught().getVelocity().getZ();
			if(e.getCaught() instanceof Player) {
				Damages.remove(e.getCaught());
	        	Damages.add((Player)e.getCaught());
			}
        	e.getCaught().setVelocity(new Vector(x*20, (y+1)*2, z*20));
		}
		
		if(state.equals("CAUGHT_FISH")) {
			//Main.Core.broadcastMessage("[DEBUG]: "+ChatColor.GREEN+"CAUGHT FISH EVENT");
			
			Player Vastir = Main.Core.getPlayer("VasterLordEP");
			if(Vastir!=null&e.getPlayer()!=Vastir) {
				if(chance(0.1)&ChatConfig.getBoolean("VasterFish")) {
					if(chance(0.5)) {
						Main.Core.broadcastMessage(ChatColor.DARK_PURPLE+"ЭТО ЧТО ЗА ПОКЕМОН?");
					}else {
						Main.Core.broadcastMessage(ChatColor.GREEN+e.getPlayer().getCustomName()+ChatColor.WHITE+" ПОЙМАЛ "
							+ChatColor.DARK_PURPLE+"ВОСТИРА!1");
					}
					e.getPlayer().giveExpLevels(1);
				
					Vastir.teleport(e.getHook().getLocation());
					//e.getCaught().
				}
				
				
			}
			double _FishChance = ChatConfig.getDouble("FishingChance");
			if(chance(FishChance)) {
				
				FishChance = _FishChance;
				Main.Core.broadcastMessage(ChatColor.GREEN+e.getPlayer().getCustomName()+ChatColor.AQUA+" СМОГ ПОЙМАТЬ ЧТО-ТО СТРАННОЕ!");
				
				EntityType rnd[] = {EntityType.BAT,
						EntityType.BOAT,
						EntityType.DOLPHIN,
						EntityType.FOX,
						EntityType.IRON_GOLEM,
						EntityType.PANDA,
						EntityType.PIG,
						EntityType.MINECART,
						EntityType.THROWN_EXP_BOTTLE,
						EntityType.TROPICAL_FISH,
						EntityType.MUSHROOM_COW,
						EntityType.ARMOR_STAND,
						EntityType.VILLAGER,
						EntityType.DROWNED,
						EntityType.GUARDIAN,
						EntityType.PILLAGER,
						EntityType.RAVAGER,
						EntityType.WANDERING_TRADER,
						EntityType.SPECTRAL_ARROW,
						EntityType.CREEPER};
				
				Entity tgt = e.getPlayer().getWorld().spawnEntity(e.getHook().getLocation(),
						rnd[(int)Math.round(Math.random()*(rnd.length-1))]);
				
				Vector vec = e.getPlayer().getLocation().subtract(e.getHook().getLocation()).toVector();
				tgt.setVelocity(vec.normalize().multiply(vec.length()*2));
				e.getPlayer().giveExpLevels(1);
			}else {
				FishChance = FishChance+(1-FishChance)*_FishChance;
				
			}
			Main.Core.broadcastMessage(ChatColor.GRAY+"[DEBUG] FishChance = "+FishChance);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		//Player p = e.getPlayer();
		//if(Main._CHEATMODE) {
		textLevel(e.getPlayer());
		    if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		        Block clicked = e.getClickedBlock();
		        if (clicked.getType() == Material.STONE_BUTTON) {
		        	e.getPlayer().setCustomName("SHIETED");
					e.getPlayer().setCustomNameVisible(true);
		        	e.getPlayer().setDisplayName("But OK");
					//e.getPlayer().(e.getPlayer().getLocation());
					e.getPlayer().setHealth(0);
					e.getPlayer().getWorld().createExplosion(clicked.getLocation(), 3, true);
					Main.Core.broadcastMessage(e.getPlayer().getName()+" не ожидал такой подставы и резко умер от каменной кнопки");
		        }
		        
		        if (clicked.getType() == Material.LEVER) {
		        	
					//e.getPlayer().setVelocity(new Vector(0,5,0));
		        	double x = e.getPlayer().getVelocity().getX();
		        	double y = e.getPlayer().getVelocity().getY();
		        	double z = e.getPlayer().getVelocity().getZ();
		        	Damages.remove(e.getPlayer());
		        	Damages.add(e.getPlayer());
		        	//Vector norm = e.getPlayer().getEyeLocation().getDirection().setY(0).normalize();
		        	
		        	//e.getPlayer().setVelocity(e.getPlayer().getVelocity().add(new Vector(0,3,0)).add(norm.multiply(40)));
					e.getPlayer().setVelocity(new Vector(x*20, (y+1)*2, z*20));
					
					//Main.Core.broadcastMessage(e.getPlayer().getName()+" не ожидал такой подставы и резко умер от каменной кнопки");
		        }
		    //}
		}
			
	}
	
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		Player p;
		if(e.getEntity() instanceof Player) {
			p = (Player)e.getEntity();
			textLevel(p);
			for(Player ply : Damages) {
				if(p==ply) {
					e.setCancelled(true);
					Damages.remove(ply);
					return;
				}
			}
			Account Acc = Account.getAccount(p);
			double Protection = 1-((Acc.Armor*Account.ArmorConst)/(1+Acc.Armor*Account.ArmorConst));
			
			e.setDamage(e.getDamage()*Protection);
		}
		
		//Main.Core.broadcastMessage("[DEBUG]: "+ChatColor.RED+"Получен урон: "+e.getEntity().getType().name());
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		//e.setDroppedExp(0);
		
		if(e.getEntity() instanceof Player) {
			
			//Player p = (Player)e.getEntity();
			//((Player)e.getEntity()).getInventory().
			//Main.Core.broadcastMessage(ChatColor.RED+p.getName()+" стал на 100% мертвее");
			
			
		}
		
		
	}
	
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		//e.getPlayer().teleport(e.getPlayer().getBedSpawnLocation());
		
		
		//e.getPlayer().setPlayerListName(e.getPlayer().getCustomName()+" ("+(e.getPlayer().getLevel()+e.getPlayer().getExp())+")");
		textLevel(e.getPlayer());
		
		
		
	}
	
	
	
	@EventHandler
		public void onEntityShootBow(EntityShootBowEvent e) {
			Player p;
			if(e.getEntity() instanceof Player) {
				p = (Player)e.getEntity();
				
				textLevel(p);
				
				
				if(e.getProjectile() instanceof Arrow) {
					if(ChatConfig.getBoolean("CheatMode")) {
						//Predicate<Integer> isPositive = x -> x > 0;
						e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(10));
						//Random random = new Random();
						for(int i = 1;i<=200;i++) {
							Arrow Arr = p.getWorld().spawnArrow(e.getProjectile().getLocation(),e.getProjectile().getVelocity().normalize(),(float) e.getProjectile().getVelocity().length(),6);
							Arr.setShooter(p);
						}
					}else {
						Account Acc = Account.getAccount((Player)p);
						double Mult = (Acc.Snipe+1)*Account.SnipeConst;
						e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(Mult));
						/*if(p.getLevel()>=ChatConfig.getInt("SpeedUpLevel")) {
							int Mult = ChatConfig.getInt("BowSpeedUp");
							e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(Mult));
							
						}*/
					}
					
					//Predicate<Entity> Collide = ent -> (ent != p)&(ent instanceof Arrow);
					//e.getProjectile().setGravity(false);
					
					
					//Vector Dir = p.getEyeLocation().getDirection().normalize();
					//RayTraceResult RT = p.getWorld().rayTrace(p.getEyeLocation(), Dir, 100, FluidCollisionMode.NEVER, true, 1, Collide);
					//p.getWorld().createExplosion(new Location(p.getWorld(),RT.getHitPosition().getX(),RT.getHitPosition().getY(),RT.getHitPosition().getZ()), 3, false);
				}
			}
		}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		//if(Main._CHEATMODE) {
		if(e.getEntity() instanceof Arrow) {
			Arrow Proj = (Arrow) e.getEntity();
			Entity Src = (Entity) Proj.getShooter();
			Player Ply;
			
			if(Src instanceof Player) {
				Ply = (Player)Src;
				textLevel(Ply);
				if(Ply.getLevel()>=ChatConfig.getInt("ExplosionLevel")) {
					int Power = ChatConfig.getInt("BowExplosion");
					if(e.getHitEntity()!=null) {
						//Ply.getWorld().createExplosion(e.getHitEntity().getLocation(), Power, false);
					}else {
						//Ply.getWorld().createExplosion(e.getHitBlock().getLocation(), Power, false);
					}
					Proj.remove();
				}
				
			}
		}
		
		if(e.getEntity() instanceof Egg) {
			double _EggChance = ChatConfig.getDouble("EggChance");
			Egg egg = (Egg) e.getEntity();
			Entity Src = (Entity) egg.getShooter();
			Player Ply;
			
			if(Src instanceof Player) {
				Ply = (Player)Src;
				
				if(chance(EggChance)) {
					Main.debug(ChatColor.GRAY+"[DEBUG] EggChance SPAWNING");
					EggChance = _EggChance;
					EntityType rnd[] = {EntityType.BAT,
							EntityType.BOAT,
							EntityType.DOLPHIN,
							EntityType.FOX,
							EntityType.IRON_GOLEM,
							EntityType.PANDA,
							EntityType.PIG,
							EntityType.MINECART,
							EntityType.THROWN_EXP_BOTTLE,
							EntityType.TROPICAL_FISH,
							EntityType.MUSHROOM_COW,
							EntityType.ARMOR_STAND,
							EntityType.VILLAGER,
							EntityType.DROWNED,
							EntityType.GUARDIAN,
							EntityType.PILLAGER,
							EntityType.RAVAGER,
							EntityType.WANDERING_TRADER,
							EntityType.SPECTRAL_ARROW,
							EntityType.CREEPER,
							EntityType.WITHER_SKELETON,
							EntityType.ENDERMAN,
							EntityType.PRIMED_TNT};
					
					if(e.getHitEntity()!=null) {
						Ply.getWorld().spawnEntity(e.getHitEntity().getLocation(),
								rnd[(int)Math.round(Math.random()*(rnd.length-1))]);
						Main.Core.broadcastMessage(ChatColor.GREEN+Ply.getCustomName()+ChatColor.BLUE+" ПРИЗВАЛ НЕЧТО!");
					}else {
					
						Ply.getWorld().spawnEntity(e.getHitBlock().getLocation().add(new Vector(0,1,0)),
								rnd[(int)Math.round(Math.random()*(rnd.length-1))]);
						Main.Core.broadcastMessage(ChatColor.GREEN+Ply.getCustomName()+ChatColor.BLUE+" ПРИЗВАЛ НЕЧТО!");
					}
				}else {
					EggChance = EggChance+(1-EggChance)*_EggChance;
					Main.Core.broadcastMessage(ChatColor.GRAY+"[DEBUG] EggChance = "+EggChance);
				}
				
			}
		}

		//}
		
	}
	
	public static boolean chance(double prob) {
		return (Math.random()<= prob);
	}
	
	public static void textLevel(Player p) {
		p.setPlayerListName(p.getCustomName()+ChatColor.RESET+" ("+(p.getLevel()+Utils.round(p.getExp(),2))+")");
	}
	
	public static FileConfiguration getCfg() {
		return Bukkit.getPluginManager().getPlugin("BronuhsShit").getConfig();
	}
	
	public static void changeName(Player Ply) {
		//int num = (int)Math.round(Math.random()*(JoinMessages.length-1));
		Main.debug("       Executed changeName(Player)");
		
		String Nick = Ply.getName();
		String OldNick = Ply.getName()+ChatColor.RESET;
		
		
		PlayerData Find = Players.GetPlayerData(Ply.getName());
		
		if(Find!=null) {
			Nick = Find.GetRandom();
			Ply.setDisplayName(Nick);
			Ply.setCustomName(Nick);
			Ply.setCustomNameVisible(true);
			textLevel(Ply);
		}else {
		
			Ply.setDisplayName(Nick);
			Ply.setCustomName(Nick);
			Ply.setCustomNameVisible(true);
			textLevel(Ply);
			
		}
		
		Nick = "§l§e"+Nick+"§r";
		Main.Core.broadcastMessage(OldNick+" сменил ник на "+Nick);
	     //.Nick.e.setJoinMessage("[JOIN MESSAGE] "+ JoinMessages[num].replace("PLY",Nick));
	}

}
