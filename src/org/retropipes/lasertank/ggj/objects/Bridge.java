package org.retropipes.lasertank.ggj.objects;

import org.retropipes.lasertank.ggj.assets.GameImageCache;

public class Bridge extends GameObject {
    public Bridge() {
	super();
	this.setAppearance(GameImageCache.get("bridge"));
    }
}
