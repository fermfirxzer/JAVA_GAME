package main;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
public class sound {
	Clip clip;
	URL soundURL[]=new URL[30];
	public sound() {
		soundURL[0]=getClass().getResource("/sound/minning_1.wav");
		soundURL[1]=getClass().getResource("/sound/minning_break.wav");
	}
	public void setFile(int i) {
		try {
			AudioInputStream ais=AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
	        clip.open(ais);
	        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Reduce the volume by a specified amount in decibels (e.g., -10.0f for 10 dB lower)
            float volume = -25.0f; // Adjust this value to make the sound quieter
            volumeControl.setValue(volume);
		}catch(Exception e) {
			
		}
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
}
