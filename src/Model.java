import java.awt.Image;

public class Model {
	
	private Level level;
	/*player should be a Player not a Sprite
	  Player should be a child of Sprite 
	  I, Sam, have hashed this as it wasn't important
	  to what i was doing, feel free to fix this :)*/
	private Sprite player;
	
	public Model() {
		this.level = new Level(this, 20, 30);
		this.player = new Sprite(1,2,"player");
		@SuppressWarnings("unused")
		Frame frame = new Frame(this);
		
		new Thread(player).start();
	}
	
	public Image[][] getLevelVisibleTiles(){
		return level.getVisibleTiles();
	}
	
	public double getPlayerX(){return player.getX();}
	
	public double getPlayerY(){return player.getY();}
	
	public Image getPlayerImage() {return player.getImage();}
	
	public void setPlayerUp(boolean up){player.setUp(up);}
	public void setPlayerDown(boolean down){player.setDown(down);}
	public void setPlayerLeft(boolean left){player.setLeft(left);}
	public void setPlayerRight(boolean right){player.setRight(right);}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Model model = new Model();
	}
}