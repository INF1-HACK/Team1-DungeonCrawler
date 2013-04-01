import java.awt.Image;
public class Model
{
	private final int startDimention = 50;
	private final String path = "dungeonCrawlerFiles/images/";
	private Player player;
	private Level level;
	private View view;
	private Frame frame;
	private boolean paused;
	private boolean god;
	public Model()
	{
		player = new Player(this, path + "sprites", 2, 3, 1, 0.5, 1.5);
		System.out.println("Created player");
		level = new Level(startDimention, this);
		System.out.println("Created level");
		view = new View(this);
		System.out.println("Created View");
		frame = new Frame(this, view);
		System.out.println("Created Frame");
		player.startThread();
		System.out.println("Player Running");
		level.startThread();
		System.out.println("Level Running");
		paused = false;
		System.out.println("Paused = False");
		god = false;
		System.err.println("God = False");
	}
	public Image getTileImage(int x, int y)
	{
		return level.getTileImage(x, y);
	}
	public boolean getTilePassable(int x, int y)
	{
		return level.getTilePassable(x, y);
	}
	public double getPlayerX()
	{
		return player.getX();
	}
	public double getPlayerY()
	{
		return player.getY();
	}
	public Image getPlayerImage()
	{
		return player.getImage();
	}
	public double getPlayerElevation()
	{
		return player.getElevation();
	}
	public double getPlayerWidth()
	{
		return player.getWidth();
	}
	public double getPlayerHeight()
	{
		return player.getHeight();
	}
	public void upPressed()
	{
		player.startMovingUp();
	}
	public void downPressed()
	{
		player.startMovingDown();
	}
	public void leftPressed()
	{
		player.startMovingLeft();
	}
	public void rightPressed()
	{
		player.startMovingRight();
	}
	public void upReleased()
	{
		player.stopMovingUp();
	}
	public void downReleased()
	{
		player.stopMovingDown();
	}
	public void leftReleased()
	{
		player.stopMovingLeft();
	}
	public void rightReleased()
	{
		player.stopMovingRight();
	}
	public void pPressed()
	{
		if (!paused)
		{
			player.pause();
			view.pause();
			paused = true;
		}
		else
		{
			player.unpause();
			view.unpause();
			paused = false;
		}
	}
	public void gPressed()
	{
		if (!god)
		{
			god = true;
			view.godModeOn();
		}
		else
		{
			god = false;
			view.godModeOff();
		}
	}
	public void close()
	{
		player.stopRunning();
		frame.dispose();
	}
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		Model m = new Model();
	}
}