package net.worldwizard.lasertank.objects;

import net.worldwizard.lasertank.loaders.ImageLoader;

public class Ground extends GameObject {
    public Ground() {
	super();
	this.setName("Ground");
	this.setAppearance(ImageLoader.loadObjectImage("ground"));
    }
}
