package net.worldwizard.lasertank.map;

import net.worldwizard.lasertank.objects.Empty;
import net.worldwizard.lasertank.objects.GameObject;
import net.worldwizard.lasertank.objects.Ground;

public class GameMap {
    // Constants
    private static final int MAP_WIDTH = 16;
    private static final int MAP_HEIGHT = 16;
    private static final int MAP_LAYERS = 4;
    // Fields
    private GameObject[] data;

    public GameMap() {
	super();
	this.data = new GameObject[GameMap.MAP_WIDTH * GameMap.MAP_HEIGHT * GameMap.MAP_LAYERS];
    }
    
    public void fill() {
	Empty e = new Empty();
	Ground g = new Ground();
	for (int l = 0; l < GameMap.MAP_LAYERS; l++) {
	    for (int x = 0; x < GameMap.MAP_WIDTH; x++) {
		for (int y = 0; y < GameMap.MAP_HEIGHT; y++) {
		    if (l == 0) {
			this.set(g, x, y, l);
		    } else {
			this.set(e, x, y, l);
		    }
		}
	    }
	}
    }

    public GameObject get(int x, int y, int l) {
	return this.data[l * GameMap.MAP_LAYERS * GameMap.MAP_HEIGHT + y * GameMap.MAP_HEIGHT + x];
    }

    public void set(GameObject go, int x, int y, int l) {
	this.data[l * GameMap.MAP_LAYERS * GameMap.MAP_HEIGHT + y * GameMap.MAP_HEIGHT + x] = go;
    }
}
