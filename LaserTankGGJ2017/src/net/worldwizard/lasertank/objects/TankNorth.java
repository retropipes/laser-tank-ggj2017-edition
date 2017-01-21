package net.worldwizard.lasertank.objects;

import net.worldwizard.lasertank.loaders.ImageLoader;

public class TankNorth extends GameObject {
    public TankNorth() {
	super();
	this.setName("Tank (Facing North)");
	this.setAppearance(ImageLoader.loadObjectImage("tank_north"));
    }
}
