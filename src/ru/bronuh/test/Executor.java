package ru.bronuh.test;

import org.bukkit.entity.Player;

public class Executor extends Thread {
	
	Player p;
	int Levels;
	
	Executor(Player p, int Levels) {
        // Создаём новый поток
        super("Второй поток");
        Main.Log.info("Started XP thread");
        this.Levels = Levels;
        this.p = p;
        start(); // Запускаем поток
    }

    public void run() {
    	Main.Log.info("Sleeping...");
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Main.Log.info("Setting FOOKEN XPIEH");
    	p.setLevel(Levels);
    	Main.Log.info("Finished XP thread");
    }
}
