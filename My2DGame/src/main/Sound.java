package main;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound {

	Clip clip; //Use for open file
	URL soundURL[] = new URL[30]; //Use URL to store the file path
	
	public Sound () {
		
		soundURL[0] = getClass().getResource("/sound/BlueBOyAdventure.wav");
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		soundURL[2] = getClass().getResource("/sound/powerup.wav");
		soundURL[3] = getClass().getResource("/sound/unlock.wav");
		soundURL[4] = getClass().getResource("/sound/fanfare.wav");
	
	}

	
	public void setFile(int i) {
		
		    try {
		        AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
		        clip = AudioSystem.getClip();
		        clip.open(ais);
		    } catch (Exception e) {
		        // Atrapamos cualquier error aquí mismo
		        System.err.println("Error cargando el sonido: " + e.getMessage());
		        // No lanzamos 'throws', por lo que el error muere aquí
		    }
		
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop () {
		clip.stop();
	}
	
}
