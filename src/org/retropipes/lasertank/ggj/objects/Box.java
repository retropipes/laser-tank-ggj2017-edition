package org.retropipes.lasertank.ggj.objects;

import org.retropipes.lasertank.ggj.assets.GameImageCache;

public class Box extends GameObject {
    public Box() {
	super();
	this.setAppearance(GameImageCache.get("box"));
	this.setSolid();
	this.setLaserMoves();
    }
}
