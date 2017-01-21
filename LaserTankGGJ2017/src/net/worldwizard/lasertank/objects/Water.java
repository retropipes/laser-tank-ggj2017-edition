package net.worldwizard.lasertank.objects;

import java.util.ArrayList;

import net.worldwizard.lasertank.assets.GameImage;
import net.worldwizard.lasertank.loaders.ImageLoader;

public class Water extends GameObject {
    public Water() {
	super();
	this.setName("Water");
	this.setFrames(3);
	ArrayList<GameImage> frames = new ArrayList<>();
	frames.add(ImageLoader.loadObjectImage("water_1"));
	frames.add(ImageLoader.loadObjectImage("water_2"));
	frames.add(ImageLoader.loadObjectImage("water_3"));
	this.setFrameAppearances(frames);
	this.setKills();
    }
}
