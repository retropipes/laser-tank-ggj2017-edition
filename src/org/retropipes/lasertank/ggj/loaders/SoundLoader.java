package org.retropipes.lasertank.ggj.loaders;

import java.net.URL;

import org.retropipes.lasertank.ggj.assets.GameSound;

public class SoundLoader {
    // Private constructor
    private SoundLoader() {
	// Do nothing
    }

    public static GameSound loadSound(final String soundName) {
	final URL u = SoundLoader.class.getResource("/net/worldwizard/lasertank/assets/sounds/" + soundName + ".wav");
	return new GameSound(u);
    }
}
