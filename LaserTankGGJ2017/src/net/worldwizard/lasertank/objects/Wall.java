package net.worldwizard.lasertank.objects;

import net.worldwizard.lasertank.loaders.ImageLoader;

public class Wall extends GameObject {
    public Wall() {
	super();
	this.setName("Wall");
	this.setAppearance(ImageLoader.loadObjectImage("wall"));
    }
}
