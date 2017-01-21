package net.worldwizard.lasertank.objects;

import net.worldwizard.lasertank.assets.GameImageCache;

public class TankEast extends GameObject {
    public TankEast() {
	super();
	this.setName("Tank (Facing East)");
	this.setAppearance(GameImageCache.get("tank_east"));
    }
}
