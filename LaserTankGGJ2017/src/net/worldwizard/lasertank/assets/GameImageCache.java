package net.worldwizard.lasertank.assets;

import java.util.ArrayList;

public class GameImageCache {
    // Fields
    private ArrayList<GameImage> cache;

    // Constructor
    public GameImageCache() {
	this.cache = new ArrayList<>();
    }

    // Methods
    public GameImage getComposite(GameImage... gic) {
	String cacheName = GameImage.generateCacheName(gic);
	GameImage test = new GameImage(cacheName);
	if (this.cache.contains(test)) {
	    int index = this.cache.indexOf(test);
	    return this.cache.get(index);
	} else {
	    GameImage gi = new GameImage(gic);
	    this.cache.add(gi);
	    return gi;
	}
    }
}
