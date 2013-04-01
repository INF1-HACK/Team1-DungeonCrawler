import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
public class View extends JPanel
{
	private final int visibleWidth = 20;
	private final int visibleHeight = 10;
	private static final long serialVersionUID = 1L;
	private Model model;
	private double xPosition = 0;
	private double yPosition = 0;
	private boolean paused = false;
	private boolean god = false;
	public View(Model input_model)
	{
		model = input_model;
		this.setBackground(new Color(50, 0, 50));
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (!paused)
		{
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			SetPosition();
			int s = scale();
			drawTiles(g, s);
			drawPlayer(g, s);
		}
		else
		{
			g.setColor(new Color(50, 0, 50));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		repaint();
	}
	private void SetPosition()
	{
		xPosition = model.getPlayerX() - (visibleWidth / 2);
		yPosition = model.getPlayerY() - (visibleHeight / 2);
	}
	private void drawTiles(Graphics g, int s)
	{
		for (int i = -1; i < visibleWidth + 1; i++)
		{
			int x = (int) (((int) xPosition + i - xPosition) * s);
			for (int j = -1; j < visibleHeight + 1; j++)
			{
				int y = (int) (((int) yPosition + j - yPosition) * s);
				if (model.getTileImage((int) xPosition + i, (int) yPosition + j) != null)
				{
					g.drawImage(model.getTileImage((int) xPosition + i, (int) yPosition + j), x, y, s, s, null);
				}
				else
				{
					g.setColor(new Color(255, 255, 255));
					g.fillRect(x, y, s, s);
				}
				if (god)
				{
					if (model.getTilePassable((int) xPosition + i, (int) yPosition + j))
					{
						g.setColor(new Color(0, 50, 0, 150));
						g.fillRect(x, y, s, s);
					}
					else
					{
						g.setColor(new Color(50, 0, 50, 150));
						g.fillRect(x, y, s, s);
					}
				}
			}
		}
	}
	private void drawPlayer(Graphics g, int s)
	{
		g.drawImage(model.getPlayerImage(), (int) ((visibleWidth / 2) * s), (int) (((visibleHeight / 2) - model.getPlayerElevation()) * s), (int) (model.getPlayerWidth() * s), (int) ((model.getPlayerHeight() + model.getPlayerElevation()) * s), null);
		if (god)
		{
			g.setColor(new Color(50, 0, 50, 150));
			g.fillRect((int) ((visibleWidth / 2) * s), (int) ((visibleHeight / 2) * s), (int) (model.getPlayerWidth() * s), (int) (model.getPlayerHeight() * s));
			g.setColor(new Color(0, 50, 0, 150));
			g.fillRect((int) ((visibleWidth / 2) * s), (int) (((visibleHeight / 2) - model.getPlayerElevation()) * s), (int) (model.getPlayerWidth() * s), (int) (model.getPlayerElevation() * s));
		}
	}
	private int scale()
	{
		if (this.getWidth() / visibleWidth >= this.getHeight() / visibleHeight)
		{
			return this.getHeight() / visibleHeight;
		}
		else
		{
			return this.getWidth() / visibleWidth;
		}
	}
	public void pause()
	{
		paused = true;
	}
	public void unpause()
	{
		paused = false;
	}
	public void godModeOn()
	{
		god = true;
	}
	public void godModeOff()
	{
		god = false;
	}
}