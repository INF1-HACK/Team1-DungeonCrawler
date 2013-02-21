import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel{

	private Model model;

	public View(Model model) {
		this.model = model;
		this.setBackground(Color.blue);
	}

	@Override
	public void paintComponent(Graphics g) {
		drawTiles(g);
		drawPlayer(g);
		drawBorder(g);
		repaint();
	}
	
	private void drawBorder(Graphics g){
		Image[][] images = model.getLevelVisibleTiles();
		//player is 1 tile wide and 2 tiles tall
		int s = size(images.length, images[0].length);
		int x1 = ((this.getWidth()/2)-(images.length*s)/2);
		int y1 = ((this.getHeight()/2)-(images[0].length*s)/2);
		int x2 = x1 + images.length*s;
		int y2 = y1 + images[0].length*s;
		g.setColor(Color.blue);
		g.fillRect(0, 0, this.getWidth(), y1);
		g.fillRect(0, 0, x1, this.getHeight());
		g.fillRect(0, y2-s, this.getWidth(), this.getHeight()-y2+s);
		g.fillRect(x2-s, 0, this.getWidth()-x2+s, this.getHeight());
	}
	
	private void drawPlayer(Graphics g){
		Image[][] images = model.getLevelVisibleTiles();
		//player is 1 tile wide and 2 tiles tall
		int s = size(images.length, images[0].length);
		g.drawImage(model.getPlayerImage(), 
				(int)(this.getWidth()/2), 
				(int)(this.getHeight()/2), 
				s, 2*s, null);
	}

	private void drawTiles(Graphics g){
		Image[][] images = model.getLevelVisibleTiles();
		//size of tile;
		int s = size(images.length, images[0].length);
		//off set
		int dx = (int)((model.getPlayerX()%1)*s);
		int dy = (int)((model.getPlayerY()%1)*s);

		for (int c = 0; c < images.length; c++) {
			for (int r = 0; r < images[0].length; r++) {
				int x = ((this.getWidth()/2)-(images.length*s)/2)+(c*s)-dx;
				int y = ((this.getHeight()/2)-(images[0].length*s)/2)+(r*s)-dy;
				
				if (images[c][r]!=null) {
					g.drawImage(images[c][r], x, y, s, s, null);
				}
				else {
					g.fillRect(x, y, s, s);
				}
			}
		}
	}

	private int size(int numberOfTilesInX, int numberOfTilesInY){
		if (this.getHeight() < this.getWidth()) {
			if (numberOfTilesInY < numberOfTilesInX) {
				return this.getHeight()/numberOfTilesInX;
			}
			else {
				return this.getHeight()/numberOfTilesInY;
			}
		}
		else {
			if (numberOfTilesInY < numberOfTilesInX) {
				return this.getWidth()/numberOfTilesInX;
			}
			else {
				return this.getWidth()/numberOfTilesInY;
			}
		}
	}

}