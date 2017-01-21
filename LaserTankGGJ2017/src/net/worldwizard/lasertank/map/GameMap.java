package net.worldwizard.lasertank.map;

import net.worldwizard.lasertank.objects.Empty;
import net.worldwizard.lasertank.objects.Flag;
import net.worldwizard.lasertank.objects.GameObject;
import net.worldwizard.lasertank.objects.Ground;
import net.worldwizard.lasertank.objects.TankNorth;

public class GameMap {
    // Constants
    private static final int MAP_WIDTH = 16;
    private static final int MAP_HEIGHT = 16;
    private static final int MAP_LAYERS = 2;
    // Fields
    private GameObject[][][] data;

    public GameMap() {
	super();
	this.data = new GameObject[GameMap.MAP_WIDTH][GameMap.MAP_HEIGHT][GameMap.MAP_LAYERS];
    }

    public void fill() {
	Empty e = new Empty();
	Ground g = new Ground();
	TankNorth t = new TankNorth();
	Flag f = new Flag();
	for (int l = 0; l < GameMap.MAP_LAYERS; l++) {
	    for (int x = 0; x < GameMap.MAP_WIDTH; x++) {
		for (int y = 0; y < GameMap.MAP_HEIGHT; y++) {
		    if (l == 0) {
			this.set(g, x, y, l);
		    } else if (l == 1 && x == 0 && y == 0) {
			this.set(t, x, y, l);
		    } else if (l == 1 && x == 15 && y == 15) {
			this.set(f, x, y, l);
		    } else {
			this.set(e, x, y, l);
		    }
		}
	    }
	}
    }

    public GameObject get(int x, int y, int l) {
	return this.data[x][y][l];
    }

    public void set(GameObject go, int x, int y, int l) {
	this.data[x][y][l] = go;
    }
}
