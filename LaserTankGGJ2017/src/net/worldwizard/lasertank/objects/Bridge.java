package net.worldwizard.lasertank.objects;

import net.worldwizard.lasertank.assets.GameImageCache;

public class Bridge extends GameObject {
    public Bridge() {
	super();
	this.setName("Bridge");
	this.setAppearance(GameImageCache.get("bridge"));
    }
}
