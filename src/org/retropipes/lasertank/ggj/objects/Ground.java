package org.retropipes.lasertank.ggj.objects;

import org.retropipes.lasertank.ggj.assets.GameImageCache;

public class Ground extends GameObject {
    public Ground() {
	super();
	this.setAppearance(GameImageCache.get("ground"));
    }
}
