package game;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import sounds.Sonido;

/** @author Gabriel Rodriguez and Claudia Monge
 * SICI4029 LK1
 * Monday, 30 de noviembre de 2020
 * Project: Galaxy Wars
 */

public class Alien_Bonus extends Thread {
	private static Sonido player;
	JPanel  alien_sprite = new JPanel( ); 
	static JPanel ex = new JPanel();
	Random randomizer = new Random();
	boolean active  = true ;
	int x;
	int y;
	int speed =6;
	
	@Override
	public void run() {

		int width  = GalaxyWars.width - alien_sprite.getSize().width ;  // ajuste


		int directionx = 1; // dirección inicial


		while ( active ) {
			
	
			alien_sprite.setSize(new Dimension(100,90));

			this.x += randomizer.nextInt( this.speed ) * directionx;

			if( this.x >= width ) {
				active = false;
			}

			alien_sprite.setLocation( this.x, this.y);

			try {
				Thread.sleep( 1 ); // 1,000 milliseconds = 1 second
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // catch

		} // while( true )
		 try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		ex.setVisible(false);
		alien_sprite.setVisible( false );  // apagar el objeto gráfico

		alien_sprite = null ;  // destruir el objeto gráfico

	} // run
	
	public Alien_Bonus(int x, int y ) {		// constructor  Set the Correct X and Y
		JLabel explo = new JLabel(new ImageIcon("./src/images/Explosion.gif"));
		ex.add(explo);
		ex.setVisible(false);
		ex.setSize(100,90);
		ex.setBackground(Color.BLACK);
		GalaxyWars.fullWindow.add(ex);
		this.x = x;
	    this.y = y;

		JPanel pane  = new JPanel() ;
		pane.setSize(new Dimension(100,90));
		pane.setBackground( new Color( 0,0,0,0) );

		JLabel label = new JLabel() ;
		label.setSize(new Dimension(100,90));

		// arreglo de imágenes
		ImageIcon[ ] image = {   SetImageSize("./src/images/Alien_ship.png",100,90) } ;

		label.setIcon( image[0 ]  ) ;  // escoger la imagen aleatoria 

		pane.add( label ) ;  // pegar el label con la imagen al JPanel
		
		alien_sprite.setVisible( true ); 

		alien_sprite.add( pane ) ;  // pegar el panel al JWindow
		
		alien_sprite.setBackground(new Color(0,0,0,0));

		alien_sprite.setLocation(x, y);

	 // apagado por default

		alien_sprite.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
		

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			    TableUpdate.userScored(3);
				active = false;
				ex.setVisible(true);
				System.out.println(alien_sprite.getLocation());
				ex.setLocation(alien_sprite.getLocation());
				player = new Sonido("./src/sounds/Laser Gun Effect.wav" );	// crear un objeto de la class Sonido
				player.start();
				player.playSound();


			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		this.x =0;

		GalaxyWars.fullWindow.add(alien_sprite);

		//GalaxyWars.fullWindow.pack();


	} // constructor

	public static ImageIcon SetImageSize(String loc, int w, int h){

		ImageIcon icon = new ImageIcon(loc);
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(w,h, Image.SCALE_SMOOTH);
		ImageIcon newImc = new ImageIcon(newImg);

		return newImc;
	}

}