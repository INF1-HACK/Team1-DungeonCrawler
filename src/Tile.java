//package com.boredInteractive.humdrum;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {

	private Image image;
	private boolean passable = true;

	public Tile(String imageName, boolean passable) {
		this.passable = passable;
		setImage(imageName);
	}

	public void setImage(String imageName){
		try {
			this.image = ImageIO.read(new File("dungeonCrawlerFiles/images/tiles/"+imageName+".png"));
		} 
		catch (IOException e) {
			System.err.println("Tile cannot find image; " + imageName + ".png");
		}
	}
	
	public void setPassable(boolean p){this.passable = p;}

	public Image getImage(){return this.image;}
	
	public boolean getPassable(){return this.passable;}
}