package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/** @author Gabriel Rodriguez and Claudia Monge
 * SICI4029 LK1
 * Monday, 30 de noviembre de 2020
 * Project: Galaxy Wars
 */


/**Creating the Main Menu*/

public class MainMenu {

	static JFrame startWindow = new JFrame();

	public static void main(String [] args) {

		JFrame startWindow = new JFrame();
		startWindow.setResizable(false);
		startWindow.setSize(500,500);
		startWindow.setLocationRelativeTo(null);
		JPanel holder = new JPanel(new GridBagLayout());
		JButton buttonStart = new JButton("Start");
		buttonStart.setBackground(new Color(102,0,153));
		buttonStart.setForeground(Color.white);
		buttonStart.setFont(new Font("TimesRoman", Font.BOLD, 30));
		buttonStart.setSize(5000,1000);
		

		buttonStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startWindow.setVisible(false);
				GalaxyWars.main(args);
			}
		});
		
		JPanel jPanel = new JPanel(new GridBagLayout());
		JLabel label = new JLabel() ;
		label.setIcon(GalaxyWars.SetImageSize("./src/images/GalaxyWars.jpeg",500,350));
		jPanel.add(label);
		holder.add(buttonStart);
		holder.setBackground(Color.black);
		jPanel.setBackground(Color.black);
		startWindow.add(label,BorderLayout.PAGE_START);
		startWindow.add(holder);
		startWindow.setVisible(true);
		
	}
	
	
}
