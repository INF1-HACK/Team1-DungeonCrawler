import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
public abstract class Sprite
{
	private Animation animation;
	protected Model model;
	protected double xPosition;
	protected double yPosition;
	protected boolean running;
	protected boolean paused;
	protected double width;
	protected double height;
	private double elevation;
	public Sprite(Model input_model,
			String input_path,
			int input_xPosition,
			int input_yPosition,
			double input_width,
			double input_height,
			double input_elevation)
	{
		animation = new Animation(input_path);
		model = input_model;
		xPosition = input_xPosition;
		yPosition = input_yPosition;
		running = true;
		width = input_width;
		height = input_height;
		elevation = input_elevation;
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
		return animation.getImage();
	}
	public double getX()
	{
		return xPosition;
	}
	public double getY()
	{
		return yPosition;
	}
	public double getElevation()
	{
		return elevation;
	}
	public double getWidth()
	{
		return width;
	}
	public double getHeight()
	{
		return height;
	}
	protected void stepAnimation()
	{
		animation.step();
	}
	private class Animation
	{
		private int i;
		private Image[] image;
		private final int steps = 10;
		public Animation(String input_path)
		{
			i = 0;
			image = new Image[new File(input_path).listFiles().length];
			for (int j = 0; j < image.length; j++)
			{
				try
				{
					image[j] = ImageIO.read(new File(input_path + "/" + "image" + j + ".png"));
				}
				catch (Exception e)
				{
					System.err.println("Sprite cannot find image: " + input_path + "/" + "image" + j + ".png");
				}
			}
		}
		public void step()
		{
			i = (i + 1) % (image.length * steps);
		}
		public Image getImage()
		{
			return image[i / steps];
		}
	}
}