//package com.boredInteractive.humdrum;

import java.awt.Image;
import java.util.LinkedList;

public class Level {

	private Model model;
	private LinkedList<LinkedList<Tile>> tiles = new LinkedList<LinkedList<Tile>>();
	private int totalHeight;
	private int totalWidth;

	/* Level knows how much level is visible so view cannot
	 * try to display more level than actually exists.
	 */
	private int visibleHeight = 10;
	private int visibleWidth = 20;

	public Level(Model model, int startHeight, int startWidth) {
		this.model = model;

		totalHeight = startHeight;
		totalWidth = startWidth;
		LinkedList<Tile> temp = new LinkedList<Tile>();

		/*
		IMPORTANT
		To stop the level being smaller than what is visible if that is the case then that
		is appropriated here
		 */
		if (totalHeight<visibleHeight) {visibleHeight=totalHeight;}
		if (totalWidth<visibleWidth) {visibleWidth=totalWidth;}

		//temp values to checker level
		int row = 1;
		int colomb = 1;

		for (int c = 0; c < totalWidth; c++) {
			for (int r = 0; r < totalHeight; r++) {
				temp.add(new Tile(imageName(colomb, row), true));
				row *= -1;
			}
			tiles.add(temp);
			temp = new LinkedList<Tile>();;
			colomb *= -1;
		}
		
		//temp values to make a wall
		for (int i = 0; i < totalWidth; i++) {
			tiles.get(i).get(0).setPassable(false);
			tiles.get(i).get(0).setImage("wallTop");
			tiles.get(i).get(1).setPassable(false);
			tiles.get(i).get(1).setImage("wallBottom");
			tiles.get(i).get(totalHeight-1).setPassable(false);
			tiles.get(i).get(totalHeight-1).setImage("wallBehind");
		}
		for (int i = 0; i < totalHeight; i++) {
			tiles.get(0).get(i).setPassable(false);
			tiles.get(0).get(i).setImage("wallEastFaceing");
			tiles.get(totalWidth-1).get(i).setPassable(false);
			tiles.get(totalWidth-1).get(i).setImage("wallWestFaceing");
		}
		tiles.get(0).get(1).setImage("wallSouthAndWestFacing");
		tiles.get(this.totalWidth-1).get(1).setImage("wallSouthAndEastFacing");
		tiles.get(0).get(0).setImage("wallTop");
		tiles.get(totalWidth-1).get(0).setImage("wallTop");
		tiles.get(0).get(totalHeight-1).setImage("wallBehind");
		tiles.get(totalWidth-1).get(totalHeight-1).setImage("wallBehind");
	}

	//temp function for checkering level
	private String imageName(int colomb, int row){
		if (row*colomb == 1){return "white";}
		else {return "black";}
	}

	public Image[][] getVisibleTiles() {
		Image[][] visible = new Image[visibleWidth][visibleHeight];
		int playerTileX = (int)model.getPlayerX();
		int playerTileY = (int)model.getPlayerY();
		
		for (int c = (playerTileX-(visibleWidth/2)); c < ((playerTileX-(visibleWidth/2))+visibleWidth); c++) {
			for (int r = (playerTileY-(visibleHeight/2)); r < ((playerTileY-(visibleHeight/2))+visibleHeight); r++) {
				if (c<0 || c>=totalWidth || r<0 || r>=totalHeight){
					visible[c-(playerTileX-(visibleWidth/2))][r-(playerTileY-(visibleHeight/2))] = null;
				}
				else {
					visible[c-(playerTileX-(visibleWidth/2))][r-(playerTileY-(visibleHeight/2))] = tiles.get(c).get(r).getImage();
				}
			}
		}
		return visible;
	}
	
	public boolean getTilePassable (int x, int y){return tiles.get(x).get(y).getPassable();}
	
}