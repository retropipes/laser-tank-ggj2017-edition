package net.worldwizard.lasertank.objects;

import java.util.ArrayList;

import net.worldwizard.lasertank.assets.GameImage;

public abstract class GameObject {
    // Fields
    private ArrayList<GameImage> appearances;
    private int currentFrame;
    private int totalFrames;
    private boolean hasAnimation;
    private String name;

    // Constructor
    protected GameObject() {
	super();
	this.appearances = new ArrayList<>();
	this.currentFrame = 0;
	this.totalFrames = 1;
	this.hasAnimation = false;
    }

    // Methods
    public GameImage getAppearance() {
	return this.appearances.get(this.currentFrame);
    }

    public void animate() {
	if (this.hasAnimation) {
	    this.currentFrame++;
	    if (this.currentFrame >= this.totalFrames) {
		this.currentFrame = 0;
	    }
	}
    }

    public String getName() {
	return this.name;
    }

    protected void setFrames(int newFrames) {
	this.hasAnimation = (newFrames > 1);
	this.totalFrames = newFrames;
    }

    protected void setAppearance(GameImage image) {
	this.appearances.clear();
	this.appearances.add(image);
    }

    protected void setFrameAppearances(ArrayList<GameImage> frameApps) {
	this.appearances.clear();
	this.appearances.addAll(frameApps);
    }

    protected void setName(String newName) {
	this.name = newName;
    }
}
