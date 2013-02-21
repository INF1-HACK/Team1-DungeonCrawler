import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {

	private Image image;

	public Tile(String imageName) {
		setImage(imageName);
	}
	
	private void setImage(String imageName){
		try {
			this.image = ImageIO.read(new File("dungeonCrawlerFiles/images/tiles/"+imageName+".png"));
		} 
		catch (IOException e) {
			System.err.println("Cannot find image; " + imageName);
		}
	}
	
	public Image getImage(){return this.image;}
}