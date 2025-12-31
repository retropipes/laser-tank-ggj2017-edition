package org.retropipes.lasertank.ggj.objects;

import org.retropipes.lasertank.ggj.assets.GameImageCache;

public class TankWest extends GameObject {
    public TankWest() {
	super();
	this.setAppearance(GameImageCache.get("tank_west"));
    }
}
