package sounds;

/**
 * 
 */


import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * @author jjdiaz
 *
 */
public class Sonido  extends Thread {

	private  Clip clip;

	public Sonido(String string) { // constructor
		
		try {
			File file = new File( string ).getAbsoluteFile();
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream( file  );
			
			this.clip = AudioSystem.getClip();
			
			this.clip.open(audioInputStream);
			
		} catch(Exception ex) {
			System.err.println("Error with playing sound.");
			ex.printStackTrace();
			System.exit(0);
		} // catch

	} // constructor

	@Override
	public	void run() {
		
		while ( true ) {
			
		} // while

	} // run


	public void pauseSound() {
		this.clip.stop();
	} // pauseSound
	

	public void playSound() {
		this.clip.start();
		
	} // playsound

	public void playSoundEffect() {
		this.clip.start();		
	}

} // class
