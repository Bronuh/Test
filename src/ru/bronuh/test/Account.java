package ru.bronuh.test;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Account {
	public String Name;
	public int Score, Money, Snipe, Armor, Strength, Looting, Multishot, Vampirism, Permission;
	public Player player;
	public boolean Attack;
	public static ArrayList<Account> Accounts = new ArrayList<Account>();
	public static double ArmorConst = 0.065;
	
	public static double SnipeConst = 0.25, StrengthConst=1.5;
	
	public static int SnipeCost = 15,
			ArmorCost = 5,
			StrengthCost = 5,
			LootingCost = 10,
			MultishotCost = 50,
			VampirismCost = 20;
	public static double SnipeCostMult = 2,
			ArmorCostMult = 1.5,
			StrengthCostMult = 2.5,
			LootingCostMult = 1.5,
			MultishotCostMult = 1.65,
			VampirismCostMult = 1.5;
	
	
	
	static File cfile;
    static FileConfiguration config;
    static File folder = new File(Main.This.getDataFolder(), "player data" + File.separator);
    static File df = Main.This.getDataFolder();
    
    
    
    Account(Player Ply){
		Name = Ply.getCustomName();
		Score = 0;
		Money = 0;
		Snipe = 0;
		Armor = 0;
		Strength = 0;
		Looting = 0;
		player = Ply;
		create(Ply);
		Accounts.add(this);
		Permission = 0;
	} 
    
	public boolean buy(int Value) {
		boolean access = (Money>=Value);
		if(!access) {
			player.sendMessage(ChatColor.RED + "ÍÅ ÄÎÑÒÀÒÎ×ÍÛÅ "+ChatConfig.getString("MoneyName")+" ("+Money+"/"+Value+")");
		}else {
			Money -= Value;
			player.sendMessage(ChatColor.GREEN + "ÑÏÈÑÀÍÎ "+Value+" "+ChatConfig.getString("MoneyName")+" (Îñòàëîñü "+Money+")");
		}

		return access; //(Money>=Value);
		
	}
	

    
    public static Account getAccount(Player Ply) {
    	for(Account a: Accounts) {
			if(a.player==Ply) {
				return a;
			}
		}
    	return new Account(Ply);
    }
	
	
    
    public void create(Player p) {
    	//player = p;
        cfile = new File(df, "player data" + File.separator + p.getName() + ".yml");
        if (!df.exists()) df.mkdir();
        if (!cfile.exists()) {
            try {
                cfile.createNewFile();
                
            } catch(Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error creating " + cfile.getName() + "!");
            }
        }
        load();
        //config = YamlConfiguration.loadConfiguration(cfile);
        config.set("warnings", "0");
    }
 
    public static File getfolder() {
        return folder;
    }
 
    public static File getfile() {
        return cfile;
    }
 
    public  void load() {
        cfile = new File(df, "player data" + File.separator + player.getName() + ".yml");
        config = YamlConfiguration.loadConfiguration(cfile);
        Score = config.getInt("Score");
        Money = config.getInt("Money");
        Snipe = config.getInt("Snipe");
		Armor = config.getInt("Armor");
		Strength = config.getInt("Strength");
		Looting = config.getInt("Looting");
		Permission = config.getInt("Permission");
    }
 
    public static FileConfiguration get() {
        return config;
    }
 
    public void save() {
        try {
        	config.set("Name", Name);
        	config.set("Score", Score);
        	config.set("Money", Money);
        	config.set("Snipe", Snipe);
        	config.set("Armor", Armor);
        	config.set("Strength", Strength);
        	config.set("Looting", Looting);
        	config.set("Permission", Permission);
            config.save(cfile);
        } catch(Exception e) {
            Bukkit.broadcast(ChatColor.RED + "Error saving " + cfile.getName() + "!", "ChatColor.ErrorMsgs");
        }
    }
	
	
}
