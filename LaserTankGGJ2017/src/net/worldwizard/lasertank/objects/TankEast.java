package net.worldwizard.lasertank.objects;

import net.worldwizard.lasertank.loaders.ImageLoader;

public class TankEast extends GameObject {
    public TankEast() {
	super();
	this.setName("Tank (Facing East)");
	this.setAppearance(ImageLoader.loadObjectImage("tank_east"));
    }
}
