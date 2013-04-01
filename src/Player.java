public class Player extends Sprite implements Runnable
{
	private final double speed = 0.2;
	private final int sleepTime = 20;
	private boolean[] direction;
	private double xDiagonal = Math.sqrt((speed * speed) / 2);
	private double yDiagonal = Math.sqrt((speed * speed) / 2);
	private double xSpeed = speed;
	private double ySpeed = speed;
	public Player (Model input_model, 
			String input_image, 
			int input_xPosition, 
			int input_yPosition, 
			double input_width, 
			double input_height, 
			double input_elevation)
	{
		super(input_model, 
				input_image, 
				input_xPosition, 
				input_yPosition, 
				input_width, 
				input_height, 
				input_elevation);
		
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
				stepAnimation();
				if (direction[0] && !direction[1])
				{
					if (direction[2] && !direction[3])
					{
						MoveUp(yDiagonal);
						MoveLeft(xDiagonal);
					}
					else if (direction[3] && !direction[2])
					{
						MoveUp(yDiagonal);
						MoveRight(xDiagonal);
					}
					else
					{
						MoveUp(ySpeed);
					}
				}
				else if (direction[1] && !direction[0])
				{
					if (direction[2] && !direction[3])
					{
						MoveDown(yDiagonal);
						MoveLeft(xDiagonal);
					}
					else if (direction[3] && !direction[2])
					{
						MoveDown(yDiagonal);
						MoveRight(xDiagonal);
					}
					else
					{
						MoveDown(ySpeed);
					}
				}
				else
				{
					if (direction[2] && !direction[3])
					{
						MoveLeft(xSpeed);
					}
					if (direction[3] && !direction[2])
					{
						MoveRight(xSpeed);
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
	
	public void startThread(){
		new Thread(this).start();
	}
	
	private void MoveUp(double input_speed)
	{
		if (model.getTilePassable((int) xPosition, (int) (yPosition - input_speed)))
		{
			yPosition -= input_speed;
		}
		else
		{
			yPosition = Math.floor(yPosition);
		}
	}
	private void MoveDown(double input_speed)
	{
		if (model.getTilePassable((int) xPosition, (int) (yPosition + height + input_speed)))
		{
			yPosition += input_speed;
		}
		else
		{
			yPosition = Math.floor(yPosition + height + input_speed) - height;
		}
	}
	private void MoveLeft(double input_speed)
	{
		if (model.getTilePassable((int) (xPosition - input_speed), (int) yPosition))
		{
			xPosition -= input_speed;
		}
		else
		{
			xPosition = Math.floor(xPosition);
		}
	}
	private void MoveRight(double input_speed)
	{
		if (model.getTilePassable((int) (xPosition + width + input_speed), (int) yPosition))
		{
			xPosition += input_speed;
		}
		else
		{
			xPosition = Math.floor(xPosition + width + input_speed) - width;
		}
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