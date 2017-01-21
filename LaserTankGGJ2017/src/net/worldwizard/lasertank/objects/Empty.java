package net.worldwizard.lasertank.objects;

import net.worldwizard.lasertank.loaders.ImageLoader;

public class Empty extends GameObject {
    public Empty() {
	super();
	this.setName("Empty");
	this.setAppearance(ImageLoader.loadObjectImage("empty"));
    }
}
