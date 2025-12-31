package org.retropipes.lasertank.ggj.objects;

import org.retropipes.lasertank.ggj.assets.GameImageCache;

public class TankSouth extends GameObject {
    public TankSouth() {
	super();
	this.setAppearance(GameImageCache.get("tank_south"));
    }
}
