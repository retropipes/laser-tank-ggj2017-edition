package net.worldwizard.lasertank.objects;

import net.worldwizard.lasertank.loaders.ImageLoader;

public class TankWest extends GameObject {
    public TankWest() {
	super();
	this.setName("Tank (Facing West)");
	this.setAppearance(ImageLoader.loadObjectImage("tank_west"));
    }
}
