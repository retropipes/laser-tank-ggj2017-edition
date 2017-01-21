package net.worldwizard.lasertank.assets;

import java.util.ArrayList;

import net.worldwizard.lasertank.loaders.ImageLoader;

public class GameImageCache {
    // Fields
    private static ArrayList<GameImage> cache;

    // Constructor
    private GameImageCache() {
	// Do nothing
    }

    // Methods
    public static GameImage get(String name) {
	if (cache == null) {
	    cache = new ArrayList<>();
	}
	GameImage test = new GameImage(name);
	if (cache.contains(test)) {
	    int index = cache.indexOf(test);
	    return cache.get(index);
	} else {
	    GameImage gi = ImageLoader.loadObjectImage(name);
	    cache.add(gi);
	    return gi;
	}
    }

    public static GameImage getComposite(GameImage... gic) {
	if (cache == null) {
	    cache = new ArrayList<>();
	}
	String cacheName = GameImage.generateCacheName(gic);
	GameImage test = new GameImage(cacheName);
	if (cache.contains(test)) {
	    int index = cache.indexOf(test);
	    return cache.get(index);
	} else {
	    GameImage gi = new GameImage(gic);
	    cache.add(gi);
	    return gi;
	}
    }
}
