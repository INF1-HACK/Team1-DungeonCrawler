import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite implements Runnable{

	private double x;
	private double y;
	private Image image;
	private double speed = 0.3;
	private boolean goingUp = false;
	private boolean goingDown = false;
	private boolean goingLeft = false;
	private boolean goingRight = false;

	/* Welcome to sprite!
	   At the moment sprite is doing the job of player.
	   If it is your job to make player a child of sprite
	   so everything is nice and oop then feel free to do
	   so without breaking things (mainly model). It's
	   not my job so that's why I haven't done this. :)
	 */

	public Sprite(int startX, int startY, String image) {
		this.x = startX;
		this.y = startY;

		try {
			this.image = ImageIO.read(new File("dungeonCrawlerFiles/images/sprites/" + image+".png"));
		} catch (IOException e) {
			System.err.println("Cannot find sprite image; " + image + ".png");
			this.image = null;
		}
	}

	@Override
	public void run() {
		while (true){
			if (goingUp && !goingDown && !goingLeft && !goingRight) {
				y -= speed;
			}
			else if (goingUp && !goingDown && !goingLeft && goingRight){
				y -= speed*Math.sin(45);
				x += speed*Math.cos(45);
			}
			else if (!goingUp && !goingDown && !goingLeft && goingRight){
				x += speed;
			}
			else if (!goingUp && goingDown && !goingLeft && goingRight){
				y += speed*Math.sin(45);
				x += speed*Math.cos(45);
			}
			else if (!goingUp && goingDown && !goingLeft && !goingRight){
				y += speed;
			}
			else if (!goingUp && goingDown && goingLeft && !goingRight){
				y += speed*Math.sin(45);
				x -= speed*Math.cos(45);
			}
			else if (!goingUp && !goingDown && goingLeft && !goingRight){
				x -= speed;
			}
			else if (goingUp && !goingDown && goingLeft && !goingRight){
				y -= speed*Math.sin(45);
				x -= speed*Math.cos(45);
			}
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				//expected
			}
		}
	}

	public double getX() {return x;}

	public double getY() {return y;}

	public Image getImage() {return image;}

	public void setUp(boolean up) {this.goingUp = up;}

	public void setDown(boolean down) {this.goingDown = down;}

	public void setLeft(boolean left) {this.goingLeft = left;}

	public void setRight(boolean right) {this.goingRight = right;}
}