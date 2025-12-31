package org.retropipes.lasertank.ggj.objects;

import org.retropipes.lasertank.ggj.assets.GameImageCache;

public class GreenLaserVertical extends GameObject {
    public GreenLaserVertical() {
	super();
	this.setAppearance(GameImageCache.get("green_laser_vertical"));
    }
}
