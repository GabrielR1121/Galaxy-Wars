package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

/** @author Gabriel Rodriguez and Claudia Monge
 * SICI4029 LK1
 * Monday, 30 de noviembre de 2020
 * Project: Galaxy Wars
 */

public class TableUpdate {

	static int count = 0;
	public static void userScored(int point) {

		count+=point;		

		GalaxyWars.scoreCount.setText(""+count+"");


		GalaxyWars.scoreHolder.add(GalaxyWars.scoreTitle, BorderLayout.NORTH);

		GalaxyWars.scoreHolder.add(GalaxyWars.scoreCount, BorderLayout.SOUTH);

	}

	public static void gameOver() {

		GalaxyWars.timeHolder.setVisible(false);
		GalaxyWars.scoreTitle.setText("Game Over, Merry Christmas!");
		GalaxyWars.scoreTitle.setForeground(Color.RED);
		GalaxyWars.scoreCount.setForeground(Color.RED);
		GalaxyWars.scoreHolder.add(GalaxyWars.scoreTitle, BorderLayout.NORTH);
		GalaxyWars.scoreHolder.add(GalaxyWars.scoreCount, BorderLayout.SOUTH);
		GalaxyWars.scoreHolder.setSize(1000, 800);
		GalaxyWars.fullWindow.add(GalaxyWars.scoreHolder, BorderLayout.PAGE_START);
		
		new Merry_Christmas(0,100).start();
		try {
			TimeUnit.SECONDS.sleep(13);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}


}