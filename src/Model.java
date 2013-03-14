import java.awt.Image;
public class Model
{
	private final int rows = 20;
	private final int columns = 30;
	private Level level;
	private Player player;
	private Frame frame;
	private View view;
	private boolean paused;
	public Model()
	{
		level = new Level(this, rows, columns);
		view = new View(this);
		frame = new Frame(this, view);
		player = new Player(this, "player", 2, 3);
		player.run();
		paused = false;
	}
	public Image[][] getLevelVisibleTiles()
	{
		return level.getVisibleTiles();
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
	public boolean getLevelTilePassable(int x, int y)
	{
		return level.getTilePassable(x, y);
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
			paused = false;
		}
		else
		{
			player.unpause();
			view.unpause();
			paused = true;
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
