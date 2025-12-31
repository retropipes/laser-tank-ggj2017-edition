package org.retropipes.lasertank.ggj.objects;

import org.retropipes.lasertank.ggj.assets.GameImageCache;

public class TankEast extends GameObject {
    public TankEast() {
	super();
	this.setAppearance(GameImageCache.get("tank_east"));
    }
}
