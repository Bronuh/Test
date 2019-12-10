package ru.bronuh.test;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Players {
	public static ArrayList<PlayerData> Players = new ArrayList<PlayerData>();
	
	public static PlayerData GetPlayerData(String Name) {
		for(PlayerData Find : Players) {
			if(Find.Name.contentEquals(Name)) {
				return Find;
				
			}
		}
		return null;
	}
	
	
}
