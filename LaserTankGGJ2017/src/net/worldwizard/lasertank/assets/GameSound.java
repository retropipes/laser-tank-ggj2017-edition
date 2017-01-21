package net.worldwizard.lasertank.assets;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameSound {
    // Fields
    private final URL location;

    // Constructor
    public GameSound(URL u) {
	super();
	this.location = u;
    }

    public void play() {
	if (this.location != null) {
	    new Player(this.location).start();
	}
    }

    private static class Player extends Thread {
	// Fields
	private URL u;

	// Constructor
	public Player(URL input) {
	    this.u = input;
	}

	@Override
	public void run() {
	    try (AudioInputStream ais = AudioSystem.getAudioInputStream(this.u); Clip c = AudioSystem.getClip()) {
		c.open(ais);
		c.start();
	    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
		// Ignore
	    }
	}
    }
}
