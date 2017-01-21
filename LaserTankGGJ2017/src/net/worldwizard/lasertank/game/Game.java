package net.worldwizard.lasertank.game;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import net.worldwizard.lasertank.assets.GameImage;
import net.worldwizard.lasertank.assets.GameSound;
import net.worldwizard.lasertank.loaders.SoundLoader;
import net.worldwizard.lasertank.map.GameMap;
import net.worldwizard.lasertank.objects.Empty;
import net.worldwizard.lasertank.objects.GameObject;
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
    // Fields
    private EventHandler eh;
    GameMap map;
    JLabel[][] draw;
    int facing;
    int playerX, playerY;
    GameObject tank;
    GameSound dead, goal;

    public Game() {
	super("LaserTank");
	this.eh = new EventHandler();
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.facing = Game.FACING_NORTH;
	this.playerX = 0;
	this.playerY = 0;
	this.dead = SoundLoader.loadSound("die");
	this.goal = SoundLoader.loadSound("end_level");
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
    }

    void draw() {
	for (int x = 0; x < Game.MAP_SIZE; x++) {
	    for (int y = 0; y < Game.MAP_SIZE; y++) {
		JLabel jl = this.draw[y][x];
		GameImage gi0 = this.map.get(x, y, 0).getAppearance();
		GameImage gi1 = this.map.get(x, y, 1).getAppearance();
		GameImage gi = new GameImage(gi0, gi1);
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

    private class EventHandler implements KeyListener {
	private GameSound move, turn, bump;

	public EventHandler() {
	    this.move = SoundLoader.loadSound("move");
	    this.turn = SoundLoader.loadSound("turn");
	    this.bump = SoundLoader.loadSound("bump_head");
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
}
