package org.retropipes.lasertank.ggj.objects;

import org.retropipes.lasertank.ggj.assets.GameImageCache;

public class TankNorth extends GameObject {
    public TankNorth() {
	super();
	this.setAppearance(GameImageCache.get("tank_north"));
    }
}
