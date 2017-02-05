package net.worldwizard.lasertank.objects;

import net.worldwizard.lasertank.assets.GameImageCache;

public class Wall extends GameObject {
    public Wall() {
	super();
	this.setName("Wall");
	this.setAppearance(GameImageCache.get("wall"));
	this.setSolid();
    }
}
