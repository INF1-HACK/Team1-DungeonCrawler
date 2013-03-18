import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
public abstract class Sprite
{
	private Image image;
	protected Model model;
	protected float xPosition;
	protected float yPosition;
	protected boolean running;
	protected boolean paused;
	
	public Sprite(Model input_model, String input_image, int input_xPosition, int input_yPosition)
	{
		try {
			this.image = ImageIO.read(new File("dungeonCrawlerFiles/images/sprites/" + input_image + ".png"));
		} 
		catch (Exception e) {
			System.err.println("Sprite cannot find image; " + input_image + ".png");
		}
		this.model = input_model;
		xPosition = input_xPosition;
		yPosition = input_yPosition;
		running = true;
	}

	public void stopRunning()
	{
		running = false;
	}
	public void pause()
	{
		paused = true;
	}
	public void unpause()
	{
		paused = false;
	}
	public Image getImage()
	{
		return image;
	}
	public float getX()
	{
		return xPosition;
	}
	public float getY()
	{
		return yPosition;
	}
}
