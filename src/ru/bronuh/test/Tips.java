package ru.bronuh.test;

import org.bukkit.ChatColor;
import ru.bronuh.test.Utils;;
public class Tips extends Thread {
	
	public static String Messages[] = {"�������� ������ ���������� ��� �������",
			"������� ���� ��� ������ ����� 5% ���� �������� ��������� ��������, ����� ������",
			"�� ����� ������� �� ������������: ����������� ���� ���������� � ����",
			"�� ����� ������� �� ������ 10% ���� ������� �����������, ���� �� ������",
			"�� ����� ������� �� ������ 10% ���� ������� ��������� ��������, ������� ������� � ���",
			"��� ���������� 30 ������ ������, ��������� �� ���� ����� � 10 ��� �������",
			"��� ���������� 40 ������ ���� ������ ����� ���������� ��� ������������",
			"������� �� ����� ����������� ���� ������� �������� �������� � 20 ���",
			"������� �� ����� ���� ������������ �� 1 ����",
			"� ������ ������� ������������� ������ ������",
			"��� ��������� ������ ������ ����� ������  �6������ �����",
			"������ ����� ������������ � �������� �����. �������� �� ������ �� ��������",
			"�������� ���� /bh Help",
			"�������� ������ ��������� ������� /bh Commands",
			"���������� ����� /stats"};
	
	public static int Interval = 240;
	public static int MessageID = 0;
	public static int Cnt = Messages.length-1;
	public static boolean TipsOn = true;
	public boolean Active = true;
	
	@Override
    public void run() {
		
		while(TipsOn&Active) {
			Main.Core.broadcastMessage(ChatColor.GREEN+""+ChatColor.BOLD+"[���������] "+ChatColor.RESET+ChatColor.GRAY+Messages[MessageID]);
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
