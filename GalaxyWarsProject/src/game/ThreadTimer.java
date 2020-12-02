package game;
import java.awt.BorderLayout;

/** @author Gabriel Rodriguez and Claudia Monge
 * SICI4029 LK1
 * Monday, 30 de noviembre de 2020
 * Project: Galaxy Wars
 */

public class ThreadTimer extends Thread{

	@Override
	public void run() {

		while(GalaxyWars.timeCount!=0) {
			GalaxyWars.timeCount--;
			System.out.println(GalaxyWars.timeCount);
			GalaxyWars.time.setText(""+GalaxyWars.timeCount+"");
			GalaxyWars.timeHolder.add(GalaxyWars.time,BorderLayout.NORTH);
			GalaxyWars.fullWindow.add(GalaxyWars.timeHolder, BorderLayout.LINE_END);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		TableUpdate.gameOver();
	}

}