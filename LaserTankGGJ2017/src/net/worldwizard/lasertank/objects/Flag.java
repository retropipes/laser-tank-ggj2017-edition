package net.worldwizard.lasertank.objects;

import java.util.ArrayList;

import net.worldwizard.lasertank.assets.GameImage;
import net.worldwizard.lasertank.loaders.ImageLoader;

public class Flag extends GameObject {
    public Flag() {
	super();
	this.setName("Flag");
	this.setFrames(3);
	ArrayList<GameImage> frames = new ArrayList<>();
	frames.add(ImageLoader.loadObjectImage("flag_1"));
	frames.add(ImageLoader.loadObjectImage("flag_2"));
	frames.add(ImageLoader.loadObjectImage("flag_3"));
	this.setFrameAppearances(frames);
    }
}
