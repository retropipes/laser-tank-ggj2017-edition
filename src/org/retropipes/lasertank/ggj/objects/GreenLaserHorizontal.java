package org.retropipes.lasertank.ggj.objects;

import org.retropipes.lasertank.ggj.assets.GameImageCache;

public class GreenLaserHorizontal extends GameObject {
    public GreenLaserHorizontal() {
	super();
	this.setAppearance(GameImageCache.get("green_laser_horizontal"));
    }
}
