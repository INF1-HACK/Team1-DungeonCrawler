public class Player extends Sprite implements Runnable
{
	private final double speed = 0.3;
	private final int sleepTime = 20;
	private boolean[] direction;
	private double xDiagonal = Math.sqrt((speed * speed) / 2);
	private double yDiagonal = Math.sqrt((speed * speed) / 2);
	private double xSpeed = speed;
	private double ySpeed = speed;

	public Player(Model input_model, String input_image, int input_xPosition, int input_yPosition)
	{
		super(input_model, input_image, input_xPosition, input_yPosition);
		direction = new boolean[4];
		direction[0] /* Up */= false;
		direction[1] /* Down */= false;
		direction[2] /* Left */= false;
		direction[3] /* Right */= false;
	}
	@Override
	public void run()
	{
		while (running)
		{
			if (!paused)
			{
				if (direction[0] && !direction[1])
				{
					if (direction[2] && !direction[3])
					{
						yPosition -= yDiagonal;
						xPosition -= xDiagonal;
					}
					else if (direction[3] && !direction[2])
					{
						yPosition -= yDiagonal;
						xPosition += xDiagonal;
					}
					else
					{
						yPosition -= ySpeed;
					}
				}
				else if (direction[1] && !direction[0])
				{
					if (direction[2] && !direction[3])
					{
						yPosition += yDiagonal;
						xPosition -= xDiagonal;
					}
					else if (direction[3] && !direction[2])
					{
						yPosition += yDiagonal;
						xPosition += xDiagonal;
					}
					else
					{
						yPosition += ySpeed;
					}
				}
				else
				{
					if (direction[2] && !direction[3])
					{
						xPosition -= xSpeed;
					}
					if (direction[3] && !direction[2])
					{
						xPosition += xSpeed;
					}
				}
				try
				{
					Thread.sleep(sleepTime);
				}
				catch (Exception e)
				{
				}
			}
		}
	}
	public void startThread()
	{
		new Thread(this).start();
	}
	public void startMovingUp()
	{
		direction[0] = true;
	}
	public void startMovingDown()
	{
		direction[1] = true;
	}
	public void startMovingLeft()
	{
		direction[2] = true;
	}
	public void startMovingRight()
	{
		direction[3] = true;
	}
	public void stopMovingUp()
	{
		direction[0] = false;
	}
	public void stopMovingDown()
	{
		direction[1] = false;
	}
	public void stopMovingLeft()
	{
		direction[2] = false;
	}
	public void stopMovingRight()
	{
		direction[3] = false;
	}
}