package org.retropipes.lasertank.ggj.objects;

import java.util.ArrayList;

import org.retropipes.lasertank.ggj.assets.GameImage;
import org.retropipes.lasertank.ggj.assets.GameImageCache;

public class Flag extends GameObject {
    public Flag() {
	super();
	this.setFrames(3);
	final ArrayList<GameImage> frames = new ArrayList<>();
	frames.add(GameImageCache.get("flag_1"));
	frames.add(GameImageCache.get("flag_2"));
	frames.add(GameImageCache.get("flag_3"));
	this.setFrameAppearances(frames);
	this.setGoal();
    }
}
