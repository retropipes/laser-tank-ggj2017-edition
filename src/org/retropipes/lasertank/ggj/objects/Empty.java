package org.retropipes.lasertank.ggj.objects;

import org.retropipes.lasertank.ggj.assets.GameImageCache;

public class Empty extends GameObject {
    public Empty() {
	super();
	this.setAppearance(GameImageCache.get("empty"));
    }
}
