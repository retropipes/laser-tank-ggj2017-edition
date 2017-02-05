package net.worldwizard.lasertank.objects;

import net.worldwizard.lasertank.assets.GameImageCache;

public class Ground extends GameObject {
    public Ground() {
	super();
	this.setName("Ground");
	this.setAppearance(GameImageCache.get("ground"));
    }
}
