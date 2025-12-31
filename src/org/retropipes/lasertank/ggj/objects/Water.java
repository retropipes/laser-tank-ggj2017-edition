package org.retropipes.lasertank.ggj.objects;

import java.util.ArrayList;

import org.retropipes.lasertank.ggj.assets.GameImage;
import org.retropipes.lasertank.ggj.assets.GameImageCache;

public class Water extends GameObject {
    public Water() {
	super();
	this.setFrames(3);
	final ArrayList<GameImage> frames = new ArrayList<>();
	frames.add(GameImageCache.get("water_1"));
	frames.add(GameImageCache.get("water_2"));
	frames.add(GameImageCache.get("water_3"));
	this.setFrameAppearances(frames);
	this.setKills();
    }
}
