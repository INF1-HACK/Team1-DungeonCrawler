

public class NPC {

	private Stats stats;
	//Sprite enemy = new Sprite(10, 15, "player.png" );
	private Model target;

	
	public NPC(float x, float y, int level)
	{
		stats = new Stats(1);
		target = null;

		
	}
	
	public void idle()
	{
	if (target == null)
		search();
	
		else 
		{
			chase();
			if (LineOfSight.sightLine(this, target))
				attack();
			
		}
	if (stats.getCurrentHealth() <= 0)
		die();
		
		}
	
	
	public void chase()
	{
		
	}
	
	public void search()
	{
		
	}
	
	public void attack()
	{
		
	}
	

	

	public void die()
	{
		
	}


}
