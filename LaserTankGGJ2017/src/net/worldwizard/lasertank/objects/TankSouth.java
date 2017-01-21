package net.worldwizard.lasertank.objects;

import net.worldwizard.lasertank.loaders.ImageLoader;

public class TankSouth extends GameObject {
    public TankSouth() {
	super();
	this.setName("Tank (Facing South)");
	this.setAppearance(ImageLoader.loadObjectImage("tank_south"));
    }
}
