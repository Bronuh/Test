package ru.bronuh.test;

public class PlayerData {
	public String Name, Names[], Format;

	
	PlayerData(String Name, String Names[], String Format){
		this.Name = Name;
		this.Names = Names;
		this.Format = Format;
		Players.Players.add(this);
	}
	
	public String GetRandom() {
		return Format+Names[(int)Math.round(Math.random()*(Names.length-1))]+"§r";
	}
	
	
}
