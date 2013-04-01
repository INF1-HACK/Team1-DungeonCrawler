import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
public class Level implements Runnable
{
	private LinkedList<LinkedList<Tile>> tiles = new LinkedList<LinkedList<Tile>>();
	private int width;
	private int height;
	private int startDimention = 0;
	private Model model;

	public Level(int startDim, Model m)
	{
		this.model = m;
		this.startDimention = startDim;
		width = 10;
		height = startDim;
		LinkedList<Tile> temp = new LinkedList<Tile>();
		for (int c = 0; c < width; c++)
		{
			for (int r = 0; r < height; r++)
			{
				if (c % 2 == r % 2)
				{
					temp.add(new Tile("white", true));
				}
				else
				{
					temp.add(new Tile("black", true));
				}
			}
			tiles.add(temp);
			temp = new LinkedList<Tile>();
		}
		//to add walls
		for (int c = 0; c < width - 1; c++)
		{
			setImage(c, 0, "empty");
			setPassable(c, 0, false);
			setImage(c, height - 1, "empty");
			setPassable(c, height - 1, false);
		}
		for (int i = 1; i < height - 1; i++)
		{
			setImage(0, i, "empty");
			setPassable(0, i, false);
		}

		setImage(0, 0, "empty");
		setPassable(0, 0, false);

		setImage(width - 1, 0, "empty");
		setPassable(width - 1, 0, false);

		setImage(0, 1, "empty");
		setPassable(0, 1, false);
		
		setImage(width - 1, height - 1, "empty");
		setPassable(width - 1, height - 1, false);
		
		newSection();

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

	public void startThread(){
		new Thread(this).start();
	}

	private void newSection() {
		System.out.println("new section");
		System.out.println(tiles.size());
		
		int foo;
		boolean[][] values = new boolean[startDimention][startDimention];
		for (int c = 1; c < startDimention; c++) {
			for (int r = 1; r < startDimention; r++) {
				foo = (int)(Math.random() * 10);
				
				if (!values[c-1][r-1]) {foo--;}
				if (!values[c][r-1]) {foo--;}
				if (!values[c-1][r]) {foo--;}
				if (foo<0) {foo=0;}
				
				if (foo != 0) {
					values[c][r] = true;
				}
			}
		}

		for (int r = 0; r < values.length; r++) {
			values[0][r] = true;			
		}
		
		int srt = tiles.size();

		//temp values to checker level
		int row = 1;
		int colomb = 1;

		LinkedList<Tile> temp = new LinkedList<Tile>();
		temp = new LinkedList<Tile>();

		for (int c = srt; c < width+startDimention; c++) {
			temp = new LinkedList<Tile>();
			for (int r = 0; r < height; r++) {
				temp.add(new Tile(imageName(colomb, row), true));
				row *= -1;
			}
			tiles.add(temp);
			temp = new LinkedList<Tile>();;
			colomb *= -1;
		}

		for (int c = 0; c < values.length; c++) {
			for (int r = 0; r < values.length; r++) {
				if (!values[c][r]) {
					tiles.get(c+srt).get(r).setImage("empty");
					tiles.get(c+srt).get(r).setPassable(false);
				}
			}
		}
		
//		To add walls to top and bottom of new section
		for (int c = srt; c < width+startDimention; c++) {
			tiles.get(c).get(0).setImage("empty");
			tiles.get(c).get(0).setPassable(false);
			tiles.get(c).get(tiles.get(c).size()-1).setImage("empty");
			tiles.get(c).get(tiles.get(c).size()-1).setPassable(false);
		}

		width = tiles.size();
	}

	//temp function for checkering level
	private String imageName(int colomb, int row){
		if (row*colomb == 1){return "white";}
		else {return "black";}
	}

	public Image getTileImage(int x, int y)
	{
		if (x >= 0 && x < tiles.size() && y >= 0 && y < tiles.get(0).size())
		{
			if (tiles.get(x).get(y) != null)
			{
				return tiles.get(x).get(y).getImage();
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	public boolean getTilePassable(int x, int y)
	{
		if (x >= 0 && x < tiles.size() && y >= 0 && y < tiles.get(0).size())
		{
			if (tiles.get(x).get(y) != null)
			{
				return tiles.get(x).get(y).getPassable();
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public void setPassable(int x, int y, boolean pass){
		tiles.get(x).get(y).setPassable(pass);
	}

	public void setImage(int x, int y, String imageName) {
		tiles.get(x).get(y).setImage(imageName);
	}

	private class Tile
	{
		private Image image;
		private boolean passable = true;
		public Tile(String input_path, boolean input_passable)
		{
			try
			{
				this.image = ImageIO.read(new File("dungeonCrawlerFiles/images/tiles/" + input_path + ".png"));
			}
			catch (IOException e)
			{
				System.err.println("Tile cannot find image; " + input_path + ".png");
			}
			passable = input_passable;
		}
		public Image getImage()
		{
			return image;
		}
		public boolean getPassable()
		{
			return passable;
		}
		public void setPassable(boolean pass){
			this.passable = pass;
		}
		public void setImage(String imageName) {
			try {
				this.image = ImageIO.read(new File("dungeonCrawlerFiles/images/tiles/" + imageName + ".png"));
			} catch (IOException e) {
				System.err.println("Tile cannot find image; " + imageName + ".png");
			}
		}
	}
}