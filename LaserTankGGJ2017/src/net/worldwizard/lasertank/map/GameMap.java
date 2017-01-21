package net.worldwizard.lasertank.map;

import net.worldwizard.lasertank.objects.Empty;
import net.worldwizard.lasertank.objects.Flag;
import net.worldwizard.lasertank.objects.GameObject;
import net.worldwizard.lasertank.objects.Ground;
import net.worldwizard.lasertank.objects.TankNorth;
import net.worldwizard.lasertank.objects.Wall;
import net.worldwizard.lasertank.objects.Water;

public class GameMap {
    // Constants
    private static final int MAP_WIDTH = 16;
    private static final int MAP_HEIGHT = 16;
    private static final int MAP_LAYERS = 4;
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
	Wall wall = new Wall();
	Water water = new Water();
	for (int l = 0; l < GameMap.MAP_LAYERS; l++) {
	    for (int x = 0; x < GameMap.MAP_WIDTH; x++) {
		for (int y = 0; y < GameMap.MAP_HEIGHT; y++) {
		    if (l == 0) {
			if (x == 3 && y == 3) {
			    this.set(water, x, y, l);
			} else {
			    this.set(g, x, y, l);
			}
		    } else if (l == 1) {
			if (x == 0 && y == 0) {
			    this.set(t, x, y, l);
			} else if (x == 2 && y == 2) {
			    this.set(wall, x, y, l);
			} else if (x == 2 && y == 4) {
			    this.set(wall, x, y, l);
			} else if (x == 4 && y == 2) {
			    this.set(wall, x, y, l);
			} else if (x == 4 && y == 4) {
			    this.set(wall, x, y, l);
			} else if (x == 15 && y == 15) {
			    this.set(f, x, y, l);
			} else {
			    this.set(e, x, y, l);
			}
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
