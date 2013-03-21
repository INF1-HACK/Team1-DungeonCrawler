import java.awt.Image;
import java.util.LinkedList;

public class Level implements Runnable{

	private Model model;
	private int startDimention = 50;
	private LinkedList<LinkedList<Tile>> tiles = new LinkedList<LinkedList<Tile>>();
	private int totalHeight;
	private int totalWidth;

	/* Level knows how much level is visible so view cannot
	 * try to display more level than actually exists.*/
	private int visibleHeight = 10;
	private int visibleWidth = 20;

	public Level(Model model) {
		this.model = model;

		totalHeight = startDimention;
		totalWidth = startDimention;
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
			temp = new LinkedList<Tile>();
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
		}
		tiles.get(0).get(1).setImage("wallSouthAndWestFacing");
		tiles.get(0).get(0).setImage("wallTop");
		tiles.get(totalWidth-1).get(0).setImage("wallTop");
		tiles.get(0).get(totalHeight-1).setImage("wallBehind");
		tiles.get(totalWidth-1).get(totalHeight-1).setImage("wallBehind");
	}

	@Override
	public void run(){
		int endOfLevel = 0;
		while (true) {
			if ((model.getPlayerX()>endOfLevel) && ((model.getPlayerX())%startDimention)>(startDimention/2)) {
				endOfLevel = (int)(model.getPlayerX()) - ((int)(model.getPlayerX())%startDimention) + (startDimention);
				newSection();
			}
			else{//sleep
				try {Thread.sleep(100);} 
				catch (Exception e) {/*expected*/}
			}
		}
	}

	public void startThread(){new Thread(this).start();}

	private void newSection() {
		//tiles.get(x).get(y)
		System.out.println("new section");
		System.out.println(tiles.size());
		
		int srt = tiles.size();
		
		//temp values to checker level
		int row = 1;
		int colomb = 1;
		
		LinkedList<Tile> temp = new LinkedList<Tile>();
		temp = new LinkedList<Tile>();
		
		for (int c = srt; c < totalWidth+startDimention; c++) {
			temp = new LinkedList<Tile>();
			for (int r = 0; r < totalHeight; r++) {
				temp.add(new Tile(imageName(colomb, row), true));
				row *= -1;
			}
			tiles.add(temp);
			temp = new LinkedList<Tile>();;
			colomb *= -1;
		}
		
		for (int c = srt; c < totalWidth+startDimention; c++) {
			tiles.get(c).get(0).setImage("wallTop");
			tiles.get(c).get(0).setPassable(false);
			tiles.get(c).get(1).setImage("wallBottom");
			tiles.get(c).get(1).setPassable(false);
			tiles.get(c).get(tiles.get(c).size()-1).setImage("wallBehind");
			tiles.get(c).get(tiles.get(c).size()-1).setPassable(false);
		}
		
		totalWidth = tiles.size();
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
