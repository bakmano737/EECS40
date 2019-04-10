package jn.pacman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/** Ghost class handles the framework for the individual ghosts.
 *  It contains fields for the ghost position and whether or not he is alive.
 *  It has methods for moving the ghost around based on the player's position */
public abstract class Ghost 
{
	// Position GridPiece
	protected GridPiece tile;
	
	// Movement Directions
	private int direction;
	
	// Constant values for directions
	protected static final int LEFT = 1;
	protected static final int RIGHT = 2;
	protected static final int UP = 3;
	protected static final int DOWN = 4;	
	
	// Rectangle to be retrived from the grid
	protected Rect rect;
	
	// Ghost bitmap field, unique for each ghost:
	private Bitmap bmp;
	//same for each ghost in special mode, hence it's "global":
	private static Bitmap scaredGhost;
	
	// Speed field in pixels
	protected static int speed;
	
	// Enemy pacman player to track
	protected Player player;
	
	// Boolean field for whether or not the ghost is attacking
	private boolean attack;
	
	// Boolean field to be used when the ghost gets stuck
	private boolean stuck;
	protected boolean outOfGame;
	protected int timeOut;
	
	//Position in Grid:
	private int x;
	private int y;
	/** Generic constructor - set each field to a default value */
	public Ghost(Player player, int s)
	{
		direction = UP;
		speed = s;
		rect = new Rect();
		this.setRect(tile.getRectangle());
		attack = true;
		bmp = null;
		this.setPlayer(player);
		setStuck(false);
	}
	
	public Ghost(Player p, int x, int y, int s)
	{
		direction = UP;
		tile = PacmanGrid.getGridBoard()[x][y];
		this.x = x;
		this.y = y;
		rect = new Rect();
		this.setRect(tile.getRectangle());
		player = p;
		stuck = false;
		bmp = null;
		outOfGame = false;
		timeOut = 100;
		speed = s;
	}
	/** 
	 *  translate - moves the ghost to the next grid space based on pacman's position.
	 *  We want each ghost to behave differently so the implementation of this method
	 *  should be different for each ghost class that extends this class 
	 */
	public void translate()
	{
		if(tile == player.getTile())
		{
			if(Pacman.isPacmanVicious()) 
			{
				int x = 2;
				int y = 8;
				//Check if any of the trapped spots are already occupied:
				if (PacmanGrid.getGridBoard()[x][y].getBitmap() != PacmanGrid.getWall()) x++;
				if (PacmanGrid.getGridBoard()[x][y].getBitmap() != PacmanGrid.getWall()) x=16;
				if (PacmanGrid.getGridBoard()[x][y].getBitmap() != PacmanGrid.getWall()) x=17;
				tile = PacmanGrid.getGridBoard()[x][y];
				rect.set(tile.getLcoord(), tile.getUcoord(), tile.getRcoord(), tile.getDcoord());
				tile.setBitmap(bmp);
				outOfGame = true;
			}
																	
			else 
			{
				int newNumLives = Pacman.getLives();
				newNumLives--;
				Pacman.setLives(newNumLives);
				PacmanView.restartGame();
			}
		}
		
		else if(outOfGame)
		{
			timeOut--;
			if(timeOut <= 0)
			{
				timeOut = 100;
				outOfGame = false;
				tile.setBitmap(PacmanGrid.getWall());
				tile = PacmanGrid.getGridBoard()[x][y];
				rect.set(tile.getLcoord(), tile.getUcoord(), tile.getRcoord(), tile.getDcoord());
			}
		}
	}
	
	/** 
	 *  draw - Draw the ghost bitmap to the rectangle of the grid piece that
	 *  corresponds to this ghosts position on the grid	
	 */
	public void draw(Canvas c)
	{
		if(Pacman.isPacmanVicious()) c.drawBitmap(scaredGhost, null, rect, null);
		else c.drawBitmap(bmp, null, rect, null);
	}

	/** 
	 * @return the speed
	 */
	public static int getSpeed() { return speed; }

	/** 
	 * @param speed the speed to set
	 */
	public static void setSpeed(int s) { speed = s; }

	/**
	 * @return the attack 
	 */
	public boolean isAttack() { return attack; }

	/**
	 * @param attack the attack to set
	 */
	public void setAttack(boolean attack) { this.attack = attack; }

	/**
	 * @return the rect
	 */
	public Rect getRect() { return rect; }

	/**
	 * @param rect the rect to set
	 */
	public void setRect(Rect rect) { this.rect = rect; }

	/**
	 * @return the left
	 */
	public static int getLeft() { return LEFT; }

	/**
	 * @return the right
	 */
	public static int getRight() { return RIGHT; }

	/**
	 * @return the up
	 */
	public static int getUp() { return UP; }

	/**
	 * @return the down
	 */
	public static int getDown() { return DOWN; }
	
	/**
	 * @retrun the bitmap
	 */
	public Bitmap getBmp() { return bmp; }
	
	/**
	 * @param bmp the bitmap to set
	 */
	public void setBmp(Bitmap bmp) { this.bmp = bmp; }

	/**
	 * @return the player
	 */
	public Player getPlayer() { return player; }

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) { this.player = player; }

	/**
	 * @return the tile
	 */
	public GridPiece getTile() { return tile; }

	/**
	 * @param tile the tile to set
	 */
	public void setTile(GridPiece tile) { this.tile = tile; }

	/**
	 * @return the stuck
	 */
	public boolean isStuck() { return stuck; }

	/**
	 * @param stuck the stuck to set
	 */
	public void setStuck(boolean stuck) { this.stuck = stuck; }

	/**
	 * @return the direction
	 */
	public int getDirection() { return direction; }

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(int direction) { this.direction = direction; }
	
	public static void setScaredGhostImg (Bitmap b) { scaredGhost = b; 	  }

}
