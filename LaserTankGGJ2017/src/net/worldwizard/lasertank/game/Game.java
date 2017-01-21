package net.worldwizard.lasertank.game;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import net.worldwizard.lasertank.assets.GameImage;
import net.worldwizard.lasertank.map.GameMap;

public class Game extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int MAP_SIZE = 16;
    // Fields
    private GameMap map;
    private JLabel[] draw;

    public Game() {
	super("LaserTank");
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.map = new GameMap();
	this.map.fill();
	this.draw = new JLabel[Game.MAP_SIZE * Game.MAP_SIZE];
	this.getContentPane().setLayout(new GridLayout(Game.MAP_SIZE, Game.MAP_SIZE));
	for (int x = 0; x < Game.MAP_SIZE; x++) {
	    for (int y = 0; y < Game.MAP_SIZE; y++) {
		JLabel jl = new JLabel();
		GameImage gi0 = this.map.get(x, y, 0).getAppearance();
		GameImage gi1 = this.map.get(x, y, 1).getAppearance();
		GameImage gi2 = this.map.get(x, y, 2).getAppearance();
		GameImage gi3 = this.map.get(x, y, 3).getAppearance();
		GameImage gi = new GameImage(gi0, gi1, gi2, gi3);
		jl.setIcon(gi);
		this.draw[x * Game.MAP_SIZE + y] = jl;
		this.getContentPane().add(jl);
	    }
	}
    }
    
    public void startGame() {
	this.setVisible(true);
    }
}
