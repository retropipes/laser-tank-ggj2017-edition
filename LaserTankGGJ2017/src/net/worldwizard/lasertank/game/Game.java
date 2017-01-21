package net.worldwizard.lasertank.game;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import net.worldwizard.lasertank.assets.GameImage;
import net.worldwizard.lasertank.assets.GameImageCache;
import net.worldwizard.lasertank.assets.GameSound;
import net.worldwizard.lasertank.loaders.SoundLoader;
import net.worldwizard.lasertank.map.GameMap;
import net.worldwizard.lasertank.objects.Empty;
import net.worldwizard.lasertank.objects.GameObject;
import net.worldwizard.lasertank.objects.GreenLaserHorizontal;
import net.worldwizard.lasertank.objects.GreenLaserVertical;
import net.worldwizard.lasertank.objects.TankEast;
import net.worldwizard.lasertank.objects.TankNorth;
import net.worldwizard.lasertank.objects.TankSouth;
import net.worldwizard.lasertank.objects.TankWest;

public class Game extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    static final int MAP_SIZE = 16;
    static final int FACING_NORTH = 0;
    static final int FACING_SOUTH = 1;
    static final int FACING_WEST = 2;
    static final int FACING_EAST = 3;
    static final GameObject EMPTY = new Empty();
    static final GameObject TANK_NORTH = new TankNorth();
    static final GameObject TANK_SOUTH = new TankSouth();
    static final GameObject TANK_WEST = new TankWest();
    static final GameObject TANK_EAST = new TankEast();
    static final GameObject GREEN_HORZ = new GreenLaserHorizontal();
    static final GameObject GREEN_VERT = new GreenLaserVertical();
    // Fields
    EventHandler eh;
    GameMap map;
    JLabel[][] draw;
    int facing, laserFacing;
    int playerX, playerY, laserX, laserY;
    GameObject tank, laser;
    GameSound dead, goal, laserDead;

    public Game() {
	super("LaserTank");
	this.eh = new EventHandler();
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.facing = Game.FACING_NORTH;
	this.playerX = 0;
	this.playerY = 0;
	this.dead = SoundLoader.loadSound("die");
	this.goal = SoundLoader.loadSound("end_level");
	this.laserDead = SoundLoader.loadSound("laser_die");
	this.map = new GameMap();
	this.map.fill();
	this.tank = this.map.get(this.playerX, this.playerY, 1);
	this.draw = new JLabel[Game.MAP_SIZE][Game.MAP_SIZE];
	this.getContentPane().setLayout(new GridLayout(Game.MAP_SIZE, Game.MAP_SIZE));
	EmptyBorder eb = new EmptyBorder(0, 0, 0, 0);
	for (int x = 0; x < Game.MAP_SIZE; x++) {
	    for (int y = 0; y < Game.MAP_SIZE; y++) {
		JLabel jl = new JLabel();
		jl.setBorder(eb);
		this.draw[x][y] = jl;
		this.getContentPane().add(jl);
	    }
	}
	this.addKeyListener(this.eh);
	this.draw();
	this.pack();
    }

    public void startGame() {
	this.setVisible(true);
	new Animator().start();
	new LaserRunner().start();
    }

    void draw() {
	for (int x = 0; x < Game.MAP_SIZE; x++) {
	    for (int y = 0; y < Game.MAP_SIZE; y++) {
		JLabel jl = this.draw[y][x];
		GameImage gi0 = this.map.get(x, y, 0).getAppearance();
		GameImage gi1 = this.map.get(x, y, 1).getAppearance();
		GameImage gi2 = this.map.get(x, y, 2).getAppearance();
		GameImage gi3 = this.map.get(x, y, 3).getAppearance();
		GameImage gi = GameImageCache.getComposite(gi0, gi1, gi2, gi3);
		jl.setIcon(gi);
	    }
	}
	this.repaint();
    }

    boolean fixBounds(int opx, int opy) {
	if (this.playerX < 0) {
	    this.playerX = 0;
	}
	if (this.playerX >= Game.MAP_SIZE) {
	    this.playerX = Game.MAP_SIZE - 1;
	}
	if (this.playerY < 0) {
	    this.playerY = 0;
	}
	if (this.playerY >= Game.MAP_SIZE) {
	    this.playerY = Game.MAP_SIZE - 1;
	}
	GameObject go0 = this.map.get(this.playerX, this.playerY, 0);
	GameObject go1 = this.map.get(this.playerX, this.playerY, 1);
	if (go0.isSolid() || go1.isSolid()) {
	    this.playerX = opx;
	    this.playerY = opy;
	}
	if (go0.killsPlayer() || go1.killsPlayer()) {
	    this.dead.play();
	    this.map.set(Game.EMPTY, opx, opy, 1);
	    this.removeKeyListener(this.eh);
	    this.draw();
	    JOptionPane.showMessageDialog(this, "You are dead!", "LaserTank", JOptionPane.INFORMATION_MESSAGE);
	    return false;
	}
	if (go0.isGoal() || go1.isGoal()) {
	    this.goal.play();
	    this.map.set(Game.EMPTY, opx, opy, 1);
	    this.removeKeyListener(this.eh);
	    this.draw();
	    JOptionPane.showMessageDialog(this, "You win!", "LaserTank", JOptionPane.INFORMATION_MESSAGE);
	    return false;
	}
	return true;
    }

    boolean fixLaserBounds() {
	if (this.laserX < 0) {
	    return false;
	}
	if (this.laserX >= Game.MAP_SIZE) {
	    return false;
	}
	if (this.laserY < 0) {
	    return false;
	}
	if (this.laserY >= Game.MAP_SIZE) {
	    return false;
	}
	GameObject go0 = this.map.get(this.playerX, this.playerY, 0);
	GameObject go1 = this.map.get(this.playerX, this.playerY, 1);
	if (go0.isSolid() || go1.isSolid()) {
	    this.laserDead.play();
	    return false;
	}
	return true;
    }

    private class EventHandler implements KeyListener {
	private GameSound move, turn, bump, fire;

	public EventHandler() {
	    this.move = SoundLoader.loadSound("move");
	    this.turn = SoundLoader.loadSound("turn");
	    this.bump = SoundLoader.loadSound("bump_head");
	    this.fire = SoundLoader.loadSound("fire_laser");
	}

	@Override
	public void keyTyped(KeyEvent e) {
	    // Do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    Game g = Game.this;
	    boolean spun = false;
	    int opx = g.playerX;
	    int opy = g.playerY;
	    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		if (g.facing == Game.FACING_EAST) {
		    // Move
		    g.playerX++;
		    this.move.play();
		} else {
		    // Turn
		    spun = true;
		    g.facing = Game.FACING_EAST;
		    g.tank = Game.TANK_EAST;
		    this.turn.play();
		}
	    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		if (g.facing == Game.FACING_WEST) {
		    // Move
		    g.playerX--;
		    this.move.play();
		} else {
		    // Turn
		    spun = true;
		    g.facing = Game.FACING_WEST;
		    g.tank = Game.TANK_WEST;
		    this.turn.play();
		}
	    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		if (g.facing == Game.FACING_SOUTH) {
		    // Move
		    g.playerY++;
		    this.move.play();
		} else {
		    // Turn
		    spun = true;
		    g.facing = Game.FACING_SOUTH;
		    g.tank = Game.TANK_SOUTH;
		    this.turn.play();
		}
	    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
		if (g.facing == Game.FACING_NORTH) {
		    // Move
		    g.playerY--;
		    this.move.play();
		} else {
		    // Turn
		    spun = true;
		    g.facing = Game.FACING_NORTH;
		    g.tank = Game.TANK_NORTH;
		    this.turn.play();
		}
	    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		// Shoot
		this.fire.play();
		spun = true;
		if (g.facing == Game.FACING_NORTH) {
		    if (g.playerY > 0) {
			g.map.set(Game.GREEN_VERT, g.playerX, g.playerY - 1, 3);
			g.laser = Game.GREEN_VERT;
			g.laserFacing = Game.FACING_NORTH;
			g.laserX = g.playerX;
			g.laserY = g.playerY - 1;
		    }
		} else if (g.facing == Game.FACING_SOUTH) {
		    if (g.playerY < Game.MAP_SIZE - 1) {
			g.map.set(Game.GREEN_VERT, g.playerX, g.playerY + 1, 3);
			g.laser = Game.GREEN_VERT;
			g.laserFacing = Game.FACING_SOUTH;
			g.laserX = g.playerX;
			g.laserY = g.playerY + 1;
		    }
		} else if (g.facing == Game.FACING_WEST) {
		    if (g.playerX > 0) {
			g.map.set(Game.GREEN_HORZ, g.playerX - 1, g.playerY, 3);
			g.laser = Game.GREEN_HORZ;
			g.laserFacing = Game.FACING_WEST;
			g.laserX = g.playerX - 1;
			g.laserY = g.playerY;
		    }
		} else if (g.facing == Game.FACING_EAST) {
		    if (g.playerX < Game.MAP_SIZE - 1) {
			g.map.set(Game.GREEN_HORZ, g.playerX + 1, g.playerY, 3);
			g.laser = Game.GREEN_HORZ;
			g.laserFacing = Game.FACING_EAST;
			g.laserX = g.playerX + 1;
			g.laserY = g.playerY;
		    }
		}
	    }
	    boolean proceed = g.fixBounds(opx, opy);
	    if (proceed) {
		if (g.playerX == opx && g.playerY == opy && !spun) {
		    this.bump.play();
		} else {
		    g.map.set(Game.EMPTY, opx, opy, 1);
		    g.map.set(g.tank, g.playerX, g.playerY, 1);
		    g.draw();
		}
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    // Do nothing
	}
    }

    private class Animator extends Thread {
	public Animator() {
	    // Do nothing
	}

	@Override
	public void run() {
	    Game g = Game.this;
	    while (true) {
		for (int x = 0; x < Game.MAP_SIZE; x++) {
		    for (int y = 0; y < Game.MAP_SIZE; y++) {
			g.map.get(x, y, 0).animate();
			g.map.get(x, y, 1).animate();
			g.map.get(x, y, 2).animate();
			g.map.get(x, y, 3).animate();
		    }
		}
		g.draw();
		try {
		    Thread.sleep(100);
		} catch (InterruptedException e) {
		    // Ignore
		}
	    }
	}
    }

    private class LaserRunner extends Thread {
	public LaserRunner() {
	    // Do nothing
	}

	@Override
	public void run() {
	    Game g = Game.this;
	    while (true) {
		if (g.laser != null) {
		    g.removeKeyListener(g.eh);
		    int olx = g.laserX;
		    int oly = g.laserY;
		    if (g.laserFacing == Game.FACING_NORTH) {
			g.laserY--;
		    } else if (g.laserFacing == Game.FACING_SOUTH) {
			g.laserY++;
		    } else if (g.laserFacing == Game.FACING_WEST) {
			g.laserX--;
		    } else if (g.laserFacing == Game.FACING_EAST) {
			g.laserX++;
		    }
		    boolean laserAlive = g.fixLaserBounds();
		    g.map.set(Game.EMPTY, olx, oly, 3);
		    if (!laserAlive) {
			g.laser = null;
			g.addKeyListener(g.eh);
		    } else {
			g.map.set(g.laser, g.laserX, g.laserY, 3);
		    }
		    try {
			Thread.sleep(50);
		    } catch (InterruptedException e) {
			// Ignore
		    }
		} else {
		    try {
			Thread.sleep(1000);
		    } catch (InterruptedException e) {
			// Ignore
		    }
		}
	    }
	}
    }
}
