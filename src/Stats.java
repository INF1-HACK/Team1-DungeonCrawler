public class Stats {

	private int level;
	private int health;
	 
	public Stats (int level)
	{
	this.level = level;	
	}
	{
	   
		level = 1;
		health = 100;
		
	}
	
	
	public int getLevel(){
	
		return level;	
	}

	public int getMaxHealth() 
	{
		return level * 100;
	}
	public int getCurrentHealth() 
	{
		return health;
	}
	public int currentStrength()
	{
	return getLevel() * 25;	
	}

}	



