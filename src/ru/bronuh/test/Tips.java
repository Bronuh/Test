package ru.bronuh.test;

import org.bukkit.ChatColor;
import ru.bronuh.test.Utils;;
public class Tips extends Thread {
	
	public static String Messages[] = {"Каменная кнопка взрывается при нажатии",
			"Куриные яйца при броске имеют 5% шанс призвать рандомную сущность, кроме боссов",
			"Во время рыбалки не отвлекайтесь: сорвавшийся улов взрывается к хуям",
			"Во время рыбалки вы имеете 10% шанс поймать вастерлорда, если он онлайн",
			"Во время рыбалки вы имеете 10% шанс поймать случайную сущность, которая полетит в вас",
			"При достижении 30 уровня стрелы, выпущеные из лука летят в 10 раз быстрее",
			"При достижении 40 уровня ваши стрелы будут взрываться при столкновении",
			"Нажатие на рычаг увеличивает вашу текущую скорость примерно в 20 раз",
			"Нажатие на рычаг дает неуязвимость на 1 удар",
			"В списке игроков подписываются спящие игроки",
			"Для получения своего набора ников пишите  §6рыжему уебку",
			"Удочку можно использовать в качестве крюка. Ползайте по стенам на здоровье",
			"Прочтите гайд /bh Help",
			"Прочтите список доступных комманд /bh Commands",
			"Посмотреть статы /stats"};
	
	public static int Interval = 240;
	public static int MessageID = 0;
	public static int Cnt = Messages.length-1;
	public static boolean TipsOn = true;
	public boolean Active = true;
	
	@Override
    public void run() {
		
		while(TipsOn&Active) {
			Main.Core.broadcastMessage(ChatColor.GREEN+""+ChatColor.BOLD+"[ПОДСКАЗКИ] "+ChatColor.RESET+ChatColor.GRAY+Messages[MessageID]);
			MessageID++;
			if(MessageID>Cnt) {
				MessageID = 0;
			}
			try {
				sleep(Main.CFG.getInt("TipsInterval")*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
