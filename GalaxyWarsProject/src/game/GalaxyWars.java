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


public class GalaxyWars extends Thread {

	private static Sonido player;

	static JFrame fullWindow = new JFrame("Galaxy Wars");
	JPanel  sprite = new JPanel( );  // JFrame sprite = new JFrame( ) 
	static JPanel ex = new JPanel();

	Random randomizer = new Random();
	Random ran = new Random();

	boolean active  = true ;

	private int x ;  
	private int y ;  

	private int speed = 10; // rapidez con que se mueve en la pantalla
	static int height = Toolkit.getDefaultToolkit().getScreenSize().height;	
	static int width = Toolkit.getDefaultToolkit().getScreenSize().width;

	static JPanel scoreHolder = new JPanel();
	static JPanel timeHolder = new JPanel();
	static JLabel scoreTitle = new JLabel("SCORE");


	static int timeCount = 25;
	static int count = 0;        // user Score

	static String hits = " "+count+" ";
	static JLabel scoreCount = new JLabel(hits);
	static JLabel time = new JLabel(""+timeCount+"");


	@Override
	public void run() {


		int directionx = 1; // dirección inicial
		int directiony = 1 ;  // initial direction

		//Sets the JFrame's size for the running program
		fullWindow.setSize(width, height);

	
		
		while ( active ) {
			if(timeCount==0 || active == false) {
				ex.setVisible(false);        // apagar el objeto gráfico
				sprite.setVisible( false );  // apagar el objeto gráfico
				sprite = null ;  // destruir el objeto gráfico
			}

			int height  = GalaxyWars.height - sprite.getSize().height ;  // ajuste
			int width  = GalaxyWars.width - sprite.getSize().width ;  // ajuste


			//scoreHolder and sprite size adjusted
			scoreHolder.setSize(200,150);
			sprite.setSize(new Dimension(100,90));

			//Displays the stars thread on the screen.
			paint(fullWindow.getGraphics());
			try
			{
				//Delay by 1ms
				Thread.sleep(15);
			}
			catch(InterruptedException ie){}

			//adjusting the sprite's direction
			this.y = this.y + randomizer.nextInt( this.speed ) * directiony ;

			if( this.y >= height-30) {
				directiony = -1 ;

			}
			else if( this.y <= 0 ) {
				directiony = 1 ;

			}

			//adjusting the sprite's direction
			this.x += randomizer.nextInt( this.speed ) * directionx ;

			if( this.x >= width ) {
				directionx = -1 ;

			}
			else if( this.x <= 0 ) {
				directionx = 1 ;

			}

			sprite.setLocation( this.x, this.y);

			//Generates a new random ship or an alien bonus for the game.
			if(ran.nextInt(5000) == 1)
				new GalaxyWars().start();
			if(ran.nextInt(2500) == 1)
				new Alien_Bonus(0,randomizer.nextInt(height)-30).start();

			//additional thread pause
			try {
				Thread.sleep( 2 ); // 1,000 milliseconds = 1 second
			} catch (InterruptedException e) {

				e.printStackTrace();
			} // catch

		} // while( true )


		//Explosion pause after hitting a ship
		try {
			TimeUnit.MILLISECONDS.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


			ex.setVisible(false);        // apagar el objeto gráfico
			sprite.setVisible( false );  // apagar el objeto gráfico
			sprite = null ;  // destruir el objeto gráfico
	} // run


	public GalaxyWars() {		// constructor

		JPanel pane  = new JPanel() ;
		pane.setSize(new Dimension(100,90));
		pane.setBackground( new Color( 0,0,0,0) );

		JLabel label = new JLabel() ;
		label.setSize(new Dimension(100,90));

		//ship image
		ImageIcon[ ] image = {   SetImageSize("./src/images/UFO_Sprite.png",100,90)  } ;

		label.setIcon( image[ 0 ]  ) ;  

		pane.add( label ) ;  // pegar el label con la imagen al JPanel

		sprite.setVisible( true ); 

		sprite.add( pane ) ;  // pegar el panel al JWindow

		sprite.setBackground(new Color(0,0,0,0)); //transparent background
		x = randomizer.nextInt(width) ;  // posición inicial aleatoria
		y = randomizer.nextInt(height) ; // random initial position
		sprite.setLocation(x, y);


		sprite.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {


			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				//updates the score table by one
				TableUpdate.userScored(1);
				//set the explotion visible on the ships location
				ex.setVisible(true);
				ex.setLocation(sprite.getLocation());
				active = false;
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

		//adds the ship to the JFrame
		fullWindow.add(sprite);

	} // constructor

	public static ImageIcon SetImageSize(String loc, int w, int h){

		ImageIcon icon = new ImageIcon(loc);
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(w,h, Image.SCALE_SMOOTH);
		ImageIcon newImc = new ImageIcon(newImg);

		return newImc;
	}

	public static void main(String[] args) {

		//Sound Path
		String path = "./src/sounds/" ; 
		String musicString = path + "Gusty_Garden_Galaxy_8-BIT_-_Super_Mario_Galaxy_Youtubemp3.download_.wav";


		Sonido FullSoundPlayer = new Sonido( musicString );	//Creates and object from the Sonido class.
		FullSoundPlayer.playSound(); 	                    //Starts sound thread.

		//Setting up fullWindow JFrame
		fullWindow.setEnabled(true);
		fullWindow.getContentPane().setBackground(Color.BLACK);
		fullWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		//Setting up the scoreHolder JPanel and adding it to the fullWindow
		scoreHolder.setBackground(new Color(0,0,0,0));
		scoreHolder.setSize(200, 150);
		scoreTitle.setBackground(new Color(102,0,153));
		scoreTitle.setForeground(Color.white);
		scoreTitle.setFont(new Font("TimesRoman", Font.BOLD, 30));
		scoreCount.setBackground(new Color(102,0,153));
		scoreCount.setForeground(Color.white);
		scoreCount.setFont(new Font("TimesRoman", Font.BOLD, 30));
		scoreHolder.add(scoreTitle, BorderLayout.NORTH);
		scoreHolder.add(scoreCount, BorderLayout.SOUTH);
		fullWindow.add(scoreHolder);



		//Setting up the timeHolder JPanel and adding it to the fullWindow

		timeHolder.setBackground(new Color(0,0,0,0));
		timeHolder.setSize(200, 150);
		time.setBackground(new Color(102,0,153));
		time.setForeground(Color.white);
		time.setFont(new Font("TimesRoman", Font.BOLD, 30));
		timeHolder.add(time,BorderLayout.NORTH);
		fullWindow.add(timeHolder, BorderLayout.LINE_END);


		fullWindow.setVisible(true); 

		//Creating Cursor
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("./src/images/CrossHair_Cursor.png");
		Cursor c = toolkit.createCustomCursor(image , new Point(fullWindow.getX(), fullWindow.getY()), "img");
		fullWindow.setCursor(c);

		//Creating the explosion
		JLabel label = new JLabel(new ImageIcon("./src/images/Explosion.gif"));
		ex.add(label);
		ex.setVisible(false);
		ex.setSize(100,90);
		ex.setBackground(Color.BLACK);
		fullWindow.add(ex);

		//Starts timer Thread
		new ThreadTimer().start();


		for (int i = 0; i < 15; i++) {

			new GalaxyWars().start();

		} // for loop

	} // main

	public static void paint(Graphics g)
	{
		g.setColor(Color.WHITE);
		int x=(int)(Math.random()*5000)%1980;
		int y=(int)(Math.random()*5000)%1820;
		g.drawLine(x,y,x,y);
	}


} // class