package net.worldwizard.lasertank.objects;

import net.worldwizard.lasertank.assets.GameImageCache;

public class TankWest extends GameObject {
    public TankWest() {
	super();
	this.setName("Tank (Facing West)");
	this.setAppearance(GameImageCache.get("tank_west"));
    }
}
