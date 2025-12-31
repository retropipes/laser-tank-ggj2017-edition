package org.retropipes.lasertank.ggj.objects;

import org.retropipes.lasertank.ggj.assets.GameImageCache;

public class Wall extends GameObject {
    public Wall() {
	super();
	this.setAppearance(GameImageCache.get("wall"));
	this.setSolid();
    }
}
