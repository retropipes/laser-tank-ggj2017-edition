package net.worldwizard.lasertank.assets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.Icon;

public class GameImage extends BufferedImage implements Icon {
    // Constants
    private static final int DEFAULT_IMAGE_TYPE = BufferedImage.TYPE_INT_ARGB;
    // Fields
    private String name;

    // Constructor
    public GameImage(String newName) {
	super(32, 32, GameImage.DEFAULT_IMAGE_TYPE);
	this.name = newName;
    }

    public GameImage(String newName, BufferedImage bi) {
	super(bi.getWidth(), bi.getHeight(), GameImage.DEFAULT_IMAGE_TYPE);
	int w = bi.getWidth();
	int h = bi.getHeight();
	for (int x = 0; x < w; x++) {
	    for (int y = 0; y < h; y++) {
		int rgb = bi.getRGB(x, y);
		this.setRGB(x, y, rgb);
	    }
	}
	this.name = newName;
    }

    GameImage(GameImage... gic) {
	super(gic[0].getWidth(), gic[0].getHeight(), GameImage.DEFAULT_IMAGE_TYPE);
	int w = gic[0].getWidth();
	int h = gic[0].getHeight();
	for (int x = 0; x < w; x++) {
	    for (int y = 0; y < h; y++) {
		int rgb = gic[0].getRGB(x, y);
		this.setRGB(x, y, rgb);
	    }
	}
	this.name = gic[0].name;
	for (int i = 1; i < gic.length; i++) {
	    for (int x = 0; x < w; x++) {
		for (int y = 0; y < h; y++) {
		    int rgb = gic[i].getRGB(x, y);
		    Color c = new Color(rgb, true);
		    if (c.getAlpha() != 0) {
			this.setRGB(x, y, rgb);
		    }
		}
	    }
	    this.name = this.name + "_" + gic[i].name;
	}
    }

    static String generateCacheName(GameImage... gic) {
	String cacheName = gic[0].name;
	for (int i = 1; i < gic.length; i++) {
	    cacheName = cacheName + "_" + gic[i].name;
	}
	return cacheName;
    }

    public String getName() {
	return this.name;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
	g.drawImage(this, x, y, c);
    }

    @Override
    public int getIconWidth() {
	return this.getWidth();
    }

    @Override
    public int getIconHeight() {
	return this.getHeight();
    }
}
