package game;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

/** @author Gabriel Rodriguez and Claudia Monge
 * SICI4029 LK1
 * Monday, 30 de noviembre de 2020
 * Project: Galaxy Wars
 */

public class Merry_Christmas extends Thread {

	JPanel  santa_sprite = new JPanel( ); 
	static JPanel ex = new JPanel();
	Random randomizer = new Random();
	boolean active  = true ;
	int x;
	int y;
	int speed =2;
	
	@Override
	public void run() {

		int width  = GalaxyWars.width - santa_sprite.getSize().width ;  // ajuste


		int directionx = 1; // dirección inicial

		 
		while ( active ) {
			santa_sprite.setBackground(Color.BLACK);
			santa_sprite.setSize(new Dimension(550,950));
			

			this.x += randomizer.nextInt( this.speed ) * directionx;

			if( this.x >= width ) {
				active = false;
			}

			santa_sprite.setLocation( this.x, this.y);

			try {
				Thread.sleep( 1 ); // 1,000 milliseconds = 1 second
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // catch

		} // while( true )
		santa_sprite.setVisible( false );  // apagar el objeto gráfico

		santa_sprite = null ;  // destruir el objeto gráfico

	} // run
	
	public Merry_Christmas(int x, int y ) {		// constructor  Set the Correct X and Y
		this.x = x;
	    this.y = y;

		JPanel pane  = new JPanel() ;
		pane.setSize(new Dimension(900,900));
		pane.setBackground( new Color( 0,0,0,0) );

		JLabel label = new JLabel() ;
		label.setSize(new Dimension(900,900));

		// arreglo de imágenes
		ImageIcon[ ] image = { new ImageIcon("./src/images/Santa.gif") } ;

		label.setIcon( image[0 ]  ) ;  // escoger la imagen aleatoria 

		pane.add( label ) ;  // pegar el label con la imagen al JPanel
		
		santa_sprite.setVisible( true ); 

		santa_sprite.add( pane ) ;  // pegar el panel al JWindow
		
		santa_sprite.setBackground(new Color(0,0,0,0));

		santa_sprite.setLocation(x, y);

	 // apagado por default

		this.x =0;

		GalaxyWars.fullWindow.add(santa_sprite);

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